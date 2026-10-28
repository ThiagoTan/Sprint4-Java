package br.com.treenchallenge.legacy;

public class RankingSemanal {
    private String semanaReferencia;
    private Usuario[] usuarios;
    private int totalUsuarios;
    public RankingSemanal(String semanaReferencia) {
        this.semanaReferencia = semanaReferencia;
        this.usuarios = new Usuario[10];
        this.totalUsuarios = 0;
    }
    public void adicionarUsuario(Usuario usuario) {
        if (totalUsuarios < usuarios.length) {
            usuarios[totalUsuarios] = usuario;
            totalUsuarios++;
            System.out.println(usuario.getNome() + " adicionado ao ranking.");
        }
        else {
            System.out.println("Ranking cheio! Nao foi possivel adicionar " + usuario.getNome());
        }
    }
    public void ordenarPorPontuacao() {
        for (int i = 0; i < totalUsuarios - 1; i++) {
            for (int j = 0; j < totalUsuarios - i - 1; j++) {
                if (usuarios[j].getPontuacaoTotal() < usuarios[j + 1].getPontuacaoTotal()) {
                    Usuario temp = usuarios[j];
                    usuarios[j] = usuarios[j + 1];
                    usuarios[j + 1] = temp;
                }
            }
        }
    }
    public void exibirRanking() {
        ordenarPorPontuacao();
        System.out.println("\n====== RANKING SEMANAL - " + semanaReferencia + " ======");
        if (totalUsuarios == 0) {
            System.out.println("Nenhum usuario no ranking.");
            return;
        }
        for (int i = 0; i < totalUsuarios; i++) {
            Usuario u = usuarios[i];
            String medalha = getMedalha(i + 1);
            System.out.println(medalha + " " + (i + 1) + "o | "
            + u.getNome() + " | "
            + u.getPontuacaoTotal() + " pts | "
            + u.getNivel() + " | "
            + "XP:" + u.getSistemaXP().getXpTotal() + " "
            + u.getSistemaXP().getNivelAtual().getIcone() + " "
            + u.getSistemaXP().getNivelAtual().getNome() + " | "
            + u.getCarteira().getSaldo() + " TKN");
        }
        System.out.println("=========================================");
        System.out.println("Participantes: " + totalUsuarios);
    }
    public static String getMedalha(int posicao) {
        switch (posicao) {
            case 1: return "[Ouro]";
            case 2: return "[Prata]";
            case 3: return "[Bronze]";
            default: return "[--]";
        }
    }
    public Usuario buscarUsuario(String nome) {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i].getNome().equalsIgnoreCase(nome)) {
                return usuarios[i];
            }
        }
        return null;
    }
    public void exibirEstatisticas() {
        if (totalUsuarios == 0) {
            System.out.println("Sem dados para estatisticas.");
            return;
        }
        int maior = 0;
        int menor = usuarios[0].getPontuacaoTotal();
        int soma = 0;
        int totalXP = 0;
        int totalTokens = 0;
        for (int i = 0; i < totalUsuarios; i++) {
            int pts = usuarios[i].getPontuacaoTotal();
            if (pts > maior) maior = pts;
            if (pts < menor) menor = pts;
            soma += pts;
            totalXP += usuarios[i].getSistemaXP().getXpTotal();
            totalTokens += usuarios[i].getCarteira().getSaldo();
        }
        double media = (double) soma / totalUsuarios;
        System.out.println("\n--- Estatisticas: " + semanaReferencia + " ---");
        System.out.println("Maior pontuacao   : " + maior + " pts");
        System.out.println("Menor pontuacao   : " + menor + " pts");
        System.out.println("Media do grupo    : " + media + " pts");
        System.out.println("Total XP do grupo : " + totalXP + " XP");
        System.out.println("Tokens em circulac: " + totalTokens + " TKN");
        System.out.println("-------------------------------------------");
    }
    public String getSemanaReferencia() {
        return semanaReferencia;
    }
    public void setSemanaReferencia(String s) {
        this.semanaReferencia = s;
    }
    public int getTotalUsuarios() {
        return totalUsuarios;
    }
}
