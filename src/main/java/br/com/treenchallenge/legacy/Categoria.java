package br.com.treenchallenge.legacy;

public abstract class Categoria {

    protected String nome;
    protected String descricao;

    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public abstract int calcularPontos(double quantidade);

    public int calcularXP(double quantidade) {
        return calcularPontos(quantidade) * 2;
    }

    public int calcularTokens(double quantidade) {
        return calcularPontos(quantidade) / 10;
    }

    public String exibirInfo() {
        return "[" + nome + "] " + descricao;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
