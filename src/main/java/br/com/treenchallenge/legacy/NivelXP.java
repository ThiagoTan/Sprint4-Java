package br.com.treenchallenge.legacy;

public abstract class NivelXP {

    protected String nome;
    protected String icone;
    protected int    xpMinimo;
    protected int    bonusTokensAoSubir;
    protected String mensagem;

    public NivelXP(String nome, String icone, int xpMinimo,
                   int bonusTokensAoSubir, String mensagem) {
        this.nome               = nome;
        this.icone              = icone;
        this.xpMinimo           = xpMinimo;
        this.bonusTokensAoSubir = bonusTokensAoSubir;
        this.mensagem           = mensagem;
    }

    public String exibirInfo() {
        return icone + " " + nome
                + " | XP minimo: " + xpMinimo
                + " | Bonus ao subir: +" + bonusTokensAoSubir + " TKN";
    }


    public String getNome()               { return nome; }
    public void   setNome(String nome)    { this.nome = nome; }

    public String getIcone()              { return icone; }
    public void   setIcone(String icone)  { this.icone = icone; }

    public int    getXpMinimo()           { return xpMinimo; }
    public void   setXpMinimo(int v)      { this.xpMinimo = v; }

    public int    getBonusTokensAoSubir()       { return bonusTokensAoSubir; }
    public void   setBonusTokensAoSubir(int v)  { this.bonusTokensAoSubir = v; }

    public String getMensagem()           { return mensagem; }
    public void   setMensagem(String m)   { this.mensagem = m; }
}
