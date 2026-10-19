package br.com.treenchallenge.legacy;

public class CarteiraTokens {

    private int    saldo;
    private int    totalGanhoHistorico;
    private int    totalGasto;
    private Cupom[] cuponsResgatados;
    private int    totalCuponsResgatados;

    public CarteiraTokens() {
        this.saldo                 = 0;
        this.totalGanhoHistorico   = 0;
        this.totalGasto            = 0;
        this.cuponsResgatados      = new Cupom[20];
        this.totalCuponsResgatados = 0;
    }


    public void creditar(int quantidade, String motivo) {
        if (quantidade <= 0) return;
        this.saldo               += quantidade;
        this.totalGanhoHistorico += quantidade;
        System.out.println("  [+] +" + quantidade + " TreenTokens -> " + motivo
                + " | Saldo: " + saldo + " TKN");
    }


    public boolean debitar(int quantidade) {
        if (quantidade > saldo) return false;
        this.saldo      -= quantidade;
        this.totalGasto += quantidade;
        return true;
    }

    public void registrarCupomResgatado(Cupom cupom) {
        if (totalCuponsResgatados < cuponsResgatados.length) {
            cuponsResgatados[totalCuponsResgatados] = cupom;
            totalCuponsResgatados++;
        }
    }

    public void exibirExtrato() {
        System.out.println("\n========= CARTEIRA TREENTOKEN =========");
        System.out.println("  Saldo atual   : " + saldo       + " TKN");
        System.out.println("  Total ganho   : " + totalGanhoHistorico + " TKN");
        System.out.println("  Total gasto   : " + totalGasto  + " TKN");
        System.out.println("  Cupons res.   : " + totalCuponsResgatados);

        if (totalCuponsResgatados > 0) {
            System.out.println("\n  Cupons resgatados:");
            for (int i = 0; i < totalCuponsResgatados; i++) {
                System.out.println("  " + (i + 1) + ". ["
                        + cuponsResgatados[i].getCodigo() + "] "
                        + cuponsResgatados[i].getDescricao()
                        + " - " + cuponsResgatados[i].getParceiro());
            }
        }
        System.out.println("=======================================");
    }

    public int getSaldo()                  { return saldo; }
    public int getTotalGanhoHistorico()    { return totalGanhoHistorico; }
    public int getTotalGasto()             { return totalGasto; }
    public int getTotalCuponsResgatados()  { return totalCuponsResgatados; }
    public Cupom[] getCuponsResgatados()   { return cuponsResgatados; }
}
