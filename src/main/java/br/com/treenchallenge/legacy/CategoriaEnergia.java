package br.com.treenchallenge.legacy;

public class CategoriaEnergia extends Categoria {

    private double pontosPorKwh;
    private double bonusSolar;

    public CategoriaEnergia() {
        super("Energia", "Acoes de reducao e economia de energia eletrica");
        this.pontosPorKwh = 5.0;
        this.bonusSolar   = 1.5;
    }

    @Override
    public int calcularPontos(double quantidade) {
        if (quantidade <= 0) return 0;
        double pontos = quantidade * pontosPorKwh;
        if (quantidade > 100) pontos *= bonusSolar;
        return (int) pontos;
    }

    @Override
    public int calcularXP(double quantidade) {
        int xpBase = calcularPontos(quantidade) * 2;
        // Bonus de XP extra para quem economiza mais de 100 kWh
        if (quantidade > 100) {
            xpBase += 200;
        }
        return xpBase;
    }

    @Override
    public int calcularTokens(double quantidade) {
        return calcularPontos(quantidade) / 12;
    }

    @Override
    public String exibirInfo() {
        return super.exibirInfo() + " | " + pontosPorKwh + " pts/kWh";
    }

    public double getPontosPorKwh() { return pontosPorKwh; }
    public void setPontosPorKwh(double v) { this.pontosPorKwh = v; }
    public double getBonusSolar() { return bonusSolar; }
    public void setBonusSolar(double v) { this.bonusSolar = v; }
}
