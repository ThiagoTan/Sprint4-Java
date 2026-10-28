package br.com.treenchallenge.service;

import br.com.treenchallenge.model.*;
import br.com.treenchallenge.repository.*;
import br.com.treenchallenge.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class AcaoSustentavelService {
    private final AcaoSustentavelRepository acoes;
    private final UsuarioRepository usuarios;
    public AcaoSustentavelService(AcaoSustentavelRepository a, UsuarioRepository u) {
        acoes=a;
        usuarios=u;
    }
    public List<AcaoSustentavel> listar() {
        return acoes.findAll();
    }
    public List<AcaoSustentavel> porUsuario(Long id) {
        if(!usuarios.existsById(id))throw new RecursoNaoEncontradoException("Usuário "+id+" não encontrado.");
        return acoes.findByUsuarioId(id);
    }
    public AcaoSustentavel buscar(Long id) {
        return acoes.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Ação "+id+" não encontrada."));
    }
    public AcaoSustentavel criar(AcaoSustentavel a, Long uid) {
        Usuario u=usuarios.findById(uid).orElseThrow(()->new RecursoNaoEncontradoException("Usuário "+uid+" não encontrado."));
        calcular(a);
        a.setUsuario(u);
        a.setId(null);
        AcaoSustentavel s=acoes.save(a);
        u.setPontuacaoTotal(u.getPontuacaoTotal()+s.getPontos());
        u.setXpTotal(u.getXpTotal()+s.getXpGerado());
        u.setSaldoTokens(u.getSaldoTokens()+s.getTokensGerados());
        usuarios.save(u);
        return s;
    }
    public AcaoSustentavel atualizar(Long id, AcaoSustentavel d, Long uid) {
        AcaoSustentavel a=buscar(id);
        Usuario u=usuarios.findById(uid).orElseThrow(()->new RecursoNaoEncontradoException("Usuário "+uid+" não encontrado."));
        Usuario anterior=a.getUsuario();
        anterior.setPontuacaoTotal(Math.max(0, anterior.getPontuacaoTotal()-a.getPontos()));
        anterior.setXpTotal(Math.max(0, anterior.getXpTotal()-a.getXpGerado()));
        anterior.setSaldoTokens(Math.max(0, anterior.getSaldoTokens()-a.getTokensGerados()));
        usuarios.save(anterior);
        a.setDescricao(d.getDescricao());
        a.setQuantidade(d.getQuantidade());
        a.setUnidade(d.getUnidade());
        a.setData(d.getData());
        a.setCategoria(d.getCategoria());
        a.setUsuario(u);
        calcular(a);
        AcaoSustentavel salva=acoes.save(a);
        u.setPontuacaoTotal(u.getPontuacaoTotal()+salva.getPontos());
        u.setXpTotal(u.getXpTotal()+salva.getXpGerado());
        u.setSaldoTokens(u.getSaldoTokens()+salva.getTokensGerados());
        usuarios.save(u);
        return salva;
    }
    public void excluir(Long id) {
        AcaoSustentavel a=buscar(id);
        Usuario u=a.getUsuario();
        u.setPontuacaoTotal(Math.max(0, u.getPontuacaoTotal()-a.getPontos()));
        u.setXpTotal(Math.max(0, u.getXpTotal()-a.getXpGerado()));
        u.setSaldoTokens(Math.max(0, u.getSaldoTokens()-a.getTokensGerados()));
        usuarios.save(u);
        acoes.delete(a);
    }
    private void calcular(AcaoSustentavel a) {
        double q=a.getQuantidade()==null?0:a.getQuantidade();
        if(q<=0)throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        String c=a.getCategoria().toUpperCase();
        int p;
        if(c.equals("ENERGIA")) {
            p=(int)(q*5*(q>100?1.5:1));
            a.setXpGerado(p*2+(q>100?200:0));
            a.setTokensGerados(p/12);
        }
        else if(c.equals("RECICLAGEM")) {
            p=(int)(q*10);
            a.setXpGerado((int)(p*2.5));
            a.setTokensGerados(p/8);
        }
        else throw new IllegalArgumentException("Categoria deve ser ENERGIA ou RECICLAGEM.");
        a.setPontos(p);
    }
}
