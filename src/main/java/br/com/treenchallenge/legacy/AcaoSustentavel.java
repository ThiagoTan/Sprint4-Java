package br.com.treenchallenge.legacy;

public class AcaoSustentavel implements Pontuavel {

    private String    descricao;
    private double    quantidade;
    private String    unidade;
    private String    data;
    private Categoria categoria;
    private int       pontos;
    private int       xpGerado;      // XP que esta acao gerou
    private int       tokensGerados; // TreenTokens que esta acao gerou

    public AcaoSustentavel(String descricao, double quantidade, String unidade,
                           String data, Categoria categoria) {
        this.descricao     = descricao;
        this.quantidade    = quantidade;
        this.unidade       = unidade;
        this.data          = data;
        this.categoria     = categoria;
        this.pontos        = categoria.calcularPontos(quantidade);
        this.xpGerado      = categoria.calcularXP(quantidade);
        this.tokensGerados = categoria.calcularTokens(quantidade);
    }

    public String exibirResumo() {
        return "[" + data + "] " + categoria.getNome() + " - "
                + descricao + " | " + quantidade + " " + unidade
                + " | +" + pontos + " pts"
                + " | +" + xpGerado + " XP"
                + " | +" + tokensGerados + " TKN";
    }

    @Override
    public int getPontuacaoTotal() {
        return pontos;
    }

    @Override
    public void adicionarPontos(int pontosExtras) {
        this.pontos += pontosExtras;
    }

    @Override
    public String getNivel() {
        if (pontos >= 100) {
            return "Impacto Alto";
        } else if (pontos >= 50) {
            return "Impacto Medio";
        } else {
            return "Impacto Baixo";
        }
    }


    public String getDescricao()  { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getQuantidade() { return quantidade; }
    public void setQuantidade(double quantidade) {
        this.quantidade    = quantidade;
        this.pontos        = categoria.calcularPontos(quantidade);
        this.xpGerado      = categoria.calcularXP(quantidade);
        this.tokensGerados = categoria.calcularTokens(quantidade);
    }

    public String getUnidade()    { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }

    public String getData()       { return data; }
    public void setData(String data) { this.data = data; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) {
        this.categoria     = categoria;
        this.pontos        = categoria.calcularPontos(this.quantidade);
        this.xpGerado      = categoria.calcularXP(this.quantidade);
        this.tokensGerados = categoria.calcularTokens(this.quantidade);
    }

    public int getPontos()        { return pontos; }
    public int getXpGerado()      { return xpGerado; }
    public int getTokensGerados() { return tokensGerados; }
}
