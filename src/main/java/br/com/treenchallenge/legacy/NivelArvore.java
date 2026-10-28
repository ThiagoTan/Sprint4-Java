package br.com.treenchallenge.legacy;

public class NivelArvore extends NivelXP {
    public NivelArvore() {
        super(
        "Arvore", "[***]", 1500, 15, "Voce ja e uma referencia sustentavel!"
        );
    }
@Override
public String exibirInfo() {
        return super.exibirInfo() + " | Impacto visivel na comunidade local";
    }
}
