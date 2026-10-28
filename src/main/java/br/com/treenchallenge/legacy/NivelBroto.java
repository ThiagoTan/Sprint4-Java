package br.com.treenchallenge.legacy;

public class NivelBroto extends NivelXP {
    public NivelBroto() {
        super(
        "Broto", "[**]", 500, 5, "Suas acoes estao criando raizes!"
        );
    }
@Override
public String exibirInfo() {
        return super.exibirInfo() + " | Primeiros habitos sustentaveis consolidados";
    }
}
