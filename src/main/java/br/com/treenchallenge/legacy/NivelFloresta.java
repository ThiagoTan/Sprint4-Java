package br.com.treenchallenge.legacy;

public class NivelFloresta extends NivelXP {

    public NivelFloresta() {
        super(
            "Floresta",
            "[****]",
            4000,
            35,
            "Seu impacto e sentido por toda a comunidade!"
        );
    }

    @Override
    public String exibirInfo() {
        return super.exibirInfo() + " | Lideranca ambiental reconhecida";
    }
}
