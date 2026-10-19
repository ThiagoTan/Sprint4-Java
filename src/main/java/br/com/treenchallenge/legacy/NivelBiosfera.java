package br.com.treenchallenge.legacy;

public class NivelBiosfera extends NivelXP {

    public NivelBiosfera() {
        super(
            "Biosfera",
            "[*****]",
            10000,
            80,
            "Voce atingiu o nivel maximo - um guardiao do planeta!"
        );
    }

    @Override
    public String exibirInfo() {
        return super.exibirInfo() + " | NIVEL MAXIMO - Guardiao do Planeta";
    }
}
