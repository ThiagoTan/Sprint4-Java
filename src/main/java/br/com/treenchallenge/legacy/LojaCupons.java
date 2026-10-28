package br.com.treenchallenge.legacy;

public class LojaCupons {
    private Cupom[] catalogo;
    private int totalCupons;
    public LojaCupons() {
        this.catalogo = new Cupom[30];
        this.totalCupons = 0;
        inicializarCatalogo();
    }
    private void inicializarCatalogo() {
        adicionarAoCatalogo(new Cupom(
        "NAT10", "10% off em cosmeticos naturais", "Natura", 10, 10.0, "30/06/2026"));
        adicionarAoCatalogo(new Cupom(
        "BOTICA15", "15% off na linha verde", "O Boticario Green", 18, 15.0, "30/06/2026"));
        adicionarAoCatalogo(new Cupom(
        "GRND20", "20% off em produtos organicos", "Mundo Verde", 25, 20.0, "31/07/2026"));
        adicionarAoCatalogo(new Cupom(
        "OFOOD10", "10% off em restaurantes eco", "iFood Eco", 12, 10.0, "31/05/2026"));
        adicionarAoCatalogo(new Cupom(
        "ORGP15", "15% em produtos organicos online", "OrganicasShop", 20, 15.0, "30/06/2026"));
        // --- Casa & Energia ---
        adicionarAoCatalogo(new Cupom(
        "SOLAR25", "25% off em equipamentos solares", "Intelbras Solar", 40, 25.0, "31/08/2026"));
        adicionarAoCatalogo(new Cupom(
        "LED20OFF", "20% off em iluminacao LED", "Philips Lighting", 30, 20.0, "31/07/2026"));
        adicionarAoCatalogo(new Cupom(
        "LEROY15", "15% off em produtos sustentaveis", "Leroy Merlin Eco", 22, 15.0, "30/06/2026"));
        // --- Moda Sustentavel ---
        adicionarAoCatalogo(new Cupom(
        "FARM10", "10% off na colecao eco", "Farm Bio", 14, 10.0, "30/06/2026"));
        adicionarAoCatalogo(new Cupom(
        "RENNER12", "12% off em linha sustentavel", "Renner Eco", 15, 12.0, "31/07/2026"));
        // --- Mobilidade ---
        adicionarAoCatalogo(new Cupom(
        "CPTM20", "5% off em transporte público", "CPTM", 30, 5.0, "31/08/2026"));
        adicionarAoCatalogo(new Cupom(
        "EV15OFF", "15% off em carros eletricos", "BYD Brasil", 50, 15.0, "31/12/2026"));
        adicionarAoCatalogo(new Cupom(
        "EDU10", "10% off em cursos de sustentabilidade", "Coursera Green", 8, 10.0, "31/08/2026"));
        adicionarAoCatalogo(new Cupom(
        "LIVRO5", "5% off em livros sobre meio ambiente", "Livraria Cultura", 5, 5.0, "30/06/2026"));
    }
    private void adicionarAoCatalogo(Cupom cupom) {
        if (totalCupons < catalogo.length) {
            catalogo[totalCupons] = cupom;
            totalCupons++;
        }
    }
    public void exibirCatalogo() {
        System.out.println("\n============================================================");
        System.out.println("         LOJA ECORANK - TROQUE SEUS TREEN TOKENS!");
        System.out.println("============================================================");
        System.out.printf("%-4s | %-8s | %-30s | %-18s | %s%n", "#", "Codigo", "Descricao", "Parceiro", "Custo");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < totalCupons; i++) {
            System.out.printf("[%2d] %s%n", i + 1, catalogo[i].exibirNoCatalogo());
        }
        System.out.println("============================================================");
    }
    public boolean resgatarCupom(int indiceCatalogo, CarteiraTokens carteira, String nomeUsuario) {
        int indice = indiceCatalogo - 1;
        // converte para 0-based
        if (indice < 0 || indice >= totalCupons) {
            System.out.println("Cupom invalido. Escolha um numero entre 1 e " + totalCupons);
            return false;
        }
        Cupom cupom = catalogo[indice];
        if (carteira.getSaldo() < cupom.getCustoTokens()) {
            System.out.println("Saldo insuficiente! Voce tem " + carteira.getSaldo()
            + " TKN, mas este cupom custa " + cupom.getCustoTokens() + " TKN.");
            return false;
        }
        carteira.debitar(cupom.getCustoTokens());
        cupom.setResgatado(true);
        Cupom copiaResgatada = new Cupom(
        cupom.getCodigo(), cupom.getDescricao(), cupom.getParceiro(), cupom.getCustoTokens(), cupom.getPercentualDesconto(), cupom.getValidade()
        );
        copiaResgatada.setResgatado(true);
        carteira.registrarCupomResgatado(copiaResgatada);
        System.out.println(copiaResgatada.exibirResgatado());
        System.out.println("Saldo restante: " + carteira.getSaldo() + " TKN");
        return true;
    }
    public void exibirCuponsAcessiveis(int saldoTokens) {
        System.out.println("\n  Cupons acessiveis com " + saldoTokens + " TKN:");
        boolean achou = false;
        for (int i = 0; i < totalCupons; i++) {
            if (catalogo[i].getCustoTokens() <= saldoTokens) {
                System.out.println("  [" + (i + 1) + "] " + catalogo[i].exibirNoCatalogo());
                achou = true;
            }
        }
        if (achou) {
            System.out.println("  Nenhum cupom acessivel ainda. Continue acumulando tokens!");
        }
    }
    public int getTotalCupons() {
        return totalCupons;
    }
    public Cupom getCupom(int indice) {
        return (indice >= 0 && indice < totalCupons) ? catalogo[indice] : null;
    }
}
