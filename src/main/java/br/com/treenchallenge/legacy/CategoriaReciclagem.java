package br.com.treenchallenge.legacy;

class CategoriaReciclagem extends Categoria {

    private double pontosPorKg;

    public CategoriaReciclagem() {
        super("Reciclagem", "Acoes de coleta e reciclagem de materiais");
        this.pontosPorKg = 10.0;
    }

    @Override
    public int calcularPontos(double quantidade) {
        if (quantidade <= 0) return 0;
        return (int)(quantidade * pontosPorKg);
    }


    @Override
    public int calcularXP(double quantidade) {
        return (int)(calcularPontos(quantidade) * 2.5);
    }

    @Override
    public int calcularTokens(double quantidade) {
        return calcularPontos(quantidade) / 8;
    }

    @Override
    public String exibirInfo() {
        return super.exibirInfo() + " | " + pontosPorKg + " pts/kg";
    }

    public double getPontosPorKg() { return pontosPorKg; }
    public void setPontosPorKg(double pontosPorKg) { this.pontosPorKg = pontosPorKg; }
}
