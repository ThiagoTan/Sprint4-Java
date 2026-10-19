package br.com.treenchallenge.legacy;


public class Usuario implements Pontuavel {

    private String nome;
    private String email;
    private String cidade;
    private int    pontuacaoTotal;

    private AcaoSustentavel[] acoes;
    private int               totalAcoes;

    private SistemaXP      sistemaXP;
    private CarteiraTokens carteira;

    public Usuario(String nome, String email, String cidade) {
        this.nome          = nome;
        this.email         = email;
        this.cidade        = cidade;
        this.pontuacaoTotal = 0;
        this.acoes         = new AcaoSustentavel[10];
        this.totalAcoes    = 0;
        this.sistemaXP     = new SistemaXP();
        this.carteira      = new CarteiraTokens();
    }

    public void registrarAcao(AcaoSustentavel acao) {
        if (totalAcoes >= acoes.length) {
            System.out.println("Limite de acoes atingido para " + nome);
            return;
        }

        acoes[totalAcoes] = acao;
        totalAcoes++;
        pontuacaoTotal += acao.getPontos();

        System.out.println("\nAcao registrada: " + acao.exibirResumo());

        carteira.creditar(acao.getTokensGerados(),
                acao.getCategoria().getNome() + ": " + acao.getDescricao());

        sistemaXP.adicionarXP(acao.getXpGerado(), carteira, nome);
    }

    public void exibirHistorico() {
        System.out.println("\n--- Historico de " + nome + " ---");

        if (totalAcoes == 0) {
            System.out.println("Nenhuma acao registrada.");
            return;
        }

        for (int i = 0; i < totalAcoes; i++) {
            System.out.println((i + 1) + ". " + acoes[i].exibirResumo()
                    + " | " + acoes[i].getNivel());
        }

        System.out.println("Total pontos: " + pontuacaoTotal
                + " pts | Nivel: " + getNivel());
        System.out.println("-----------------------------------");
    }

    public int contarAcoesPorCategoria(String nomeCategoria) {
        int contador = 0;
        for (int i = 0; i < totalAcoes; i++) {
            if (acoes[i].getCategoria().getNome().equals(nomeCategoria)) {
                contador++;
            }
        }
        return contador;
    }

    public void exibirPerfil() {
        System.out.println("\n========== Perfil ==========");
        System.out.println("Nome    : " + nome);
        System.out.println("Email   : " + email);
        System.out.println("Cidade  : " + cidade);
        System.out.println("Pontos  : " + pontuacaoTotal + " pts");
        System.out.println("Ranking : " + getNivel());
        System.out.println("Acoes   : " + totalAcoes);
        System.out.println("----------------------------");
        System.out.println("XP Total: " + sistemaXP.getXpTotal() + " XP");
        System.out.println("Nivel XP: " + sistemaXP.getNivelAtual().getIcone()
                + " " + sistemaXP.getNivelAtual().getNome());
        System.out.println("Tokens  : " + carteira.getSaldo() + " TKN");
        System.out.println("============================");
        sistemaXP.exibirProgresso();
    }

    @Override
    public int getPontuacaoTotal() { return pontuacaoTotal; }

    @Override
    public void adicionarPontos(int pontos) { this.pontuacaoTotal += pontos; }

    @Override
    public String getNivel() {
        if (pontuacaoTotal >= 1500) return "Platina";
        if (pontuacaoTotal >= 700)  return "Ouro";
        if (pontuacaoTotal >= 300)  return "Prata";
        if (pontuacaoTotal >= 100)  return "Bronze";
        return "Iniciante";
    }


    public String          getNome()       { return nome; }
    public void            setNome(String nome) { this.nome = nome; }
    public String          getEmail()      { return email; }
    public void            setEmail(String email) { this.email = email; }
    public String          getCidade()     { return cidade; }
    public void            setCidade(String cidade) { this.cidade = cidade; }
    public int             getTotalAcoes() { return totalAcoes; }
    public SistemaXP       getSistemaXP()  { return sistemaXP; }
    public CarteiraTokens  getCarteira()   { return carteira; }
}
