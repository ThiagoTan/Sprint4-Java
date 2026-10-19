package br.com.treenchallenge.legacy;

public class NivelSemente extends NivelXP {

    public NivelSemente() {
        super(
            "Semente",
            "[*]",
            0,
            0,
            "Voce esta comecando sua jornada sustentavel!"
        );
    }

    @Override
    public String exibirInfo() {
        return super.exibirInfo() + " | Nivel inicial de todos os usuarios";
    }
}
