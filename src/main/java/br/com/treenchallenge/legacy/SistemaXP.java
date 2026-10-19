package br.com.treenchallenge.legacy;

public class SistemaXP {

    private int      xpTotal;
    private NivelXP  nivelAtual;
    private int      totalLevelUps;

    private NivelXP[] niveis;
    private int       indiceNivelAtual;

    public SistemaXP() {
        this.xpTotal           = 0;
        this.totalLevelUps     = 0;
        this.indiceNivelAtual  = 0;

        this.niveis = new NivelXP[] {
            new NivelSemente(),
            new NivelBroto(),
            new NivelArvore(),
            new NivelFloresta(),
            new NivelBiosfera()
        };

        this.nivelAtual = niveis[0]; // começa no nivel Semente
    }

    public int adicionarXP(int quantidade, CarteiraTokens carteira, String nomeUsuario) {
        if (quantidade <= 0) return 0;

        int indiceAntes = indiceNivelAtual;
        xpTotal += quantidade;

        System.out.println("  [XP] +" + quantidade + " XP | Total: " + xpTotal + " XP");

        int indiceNovo = calcularIndiceNivel(xpTotal);

        int bonusTotal = 0;

        for (int i = indiceAntes + 1; i <= indiceNovo; i++) {
            bonusTotal += niveis[i].getBonusTokensAoSubir();
            totalLevelUps++;
            exibirMensagemLevelUp(nomeUsuario, niveis[i]);
        }

        indiceNivelAtual = indiceNovo;
        nivelAtual       = niveis[indiceNovo];

        if (bonusTotal > 0 && carteira != null) {
            carteira.creditar(bonusTotal,
                    "Bonus de level-up -> " + nivelAtual.getNome());
        }

        return bonusTotal;
    }

    private int calcularIndiceNivel(int xp) {
        int resultado = 0;
        for (int i = 0; i < niveis.length; i++) {
            if (xp >= niveis[i].getXpMinimo()) {
                resultado = i;
            }
        }
        return resultado;
    }


    private void exibirMensagemLevelUp(String nomeUsuario, NivelXP novoNivel) {
        System.out.println("\n");
        System.out.println("  *** LEVEL UP! *** LEVEL UP! *** LEVEL UP! ***");
        System.out.println("  " + nomeUsuario + " subiu para o nivel: "
                + novoNivel.getIcone() + " " + novoNivel.getNome().toUpperCase());
        System.out.println("  \"" + novoNivel.getMensagem() + "\"");
        System.out.println("  Bonus: +" + novoNivel.getBonusTokensAoSubir() + " TreenTokens!");
        System.out.println("  *********************************************\n");
    }

    public void exibirProgresso() {
        System.out.println("\n  --- Progresso XP ---");
        System.out.println("  Nivel: " + nivelAtual.getIcone() + " " + nivelAtual.getNome());
        System.out.println("  XP total: " + xpTotal);

        boolean temProximo = indiceNivelAtual + 1 < niveis.length;

        if (temProximo) {
            NivelXP proximo          = niveis[indiceNivelAtual + 1];
            int xpNoNivelAtual       = xpTotal - nivelAtual.getXpMinimo();
            int xpNecessarioNivel    = proximo.getXpMinimo() - nivelAtual.getXpMinimo();
            int xpParaProximo        = proximo.getXpMinimo() - xpTotal;

            int blocos = (int)((double) xpNoNivelAtual / xpNecessarioNivel * 20);
            StringBuilder barra = new StringBuilder("  [");
            for (int i = 0; i < 20; i++) {
                barra.append(i < blocos ? "#" : ".");
            }
            barra.append("] ");
            barra.append(xpNoNivelAtual).append("/").append(xpNecessarioNivel).append(" XP");

            System.out.println(barra.toString());
            System.out.println("  Faltam " + xpParaProximo + " XP para "
                    + proximo.getIcone() + " " + proximo.getNome()
                    + " (+" + proximo.getBonusTokensAoSubir() + " TKN bonus)");
        } else {
            System.out.println("  [####################] NIVEL MAXIMO ATINGIDO!");
        }

        System.out.println("  --------------------");
    }


    public int     getXpTotal()          {
        return xpTotal;
    }
    public NivelXP getNivelAtual()       {
        return nivelAtual;
    }
    public int     getTotalLevelUps()    {
        return totalLevelUps;
    }

    public NivelXP[] getTodosNiveis()    {
        return niveis;
    }
}
