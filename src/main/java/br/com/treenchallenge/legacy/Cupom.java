package br.com.treenchallenge.legacy;

public class Cupom {

    private String codigo;
    private String descricao;
    private String parceiro;
    private int    custoTokens;
    private double percentualDesconto;
    private String validade;
    private boolean resgatado;

    public Cupom(String codigo, String descricao, String parceiro,
                 int custoTokens, double percentualDesconto, String validade) {
        this.codigo              = codigo;
        this.descricao           = descricao;
        this.parceiro            = parceiro;
        this.custoTokens         = custoTokens;
        this.percentualDesconto  = percentualDesconto;
        this.validade            = validade;
        this.resgatado           = false;
    }

    public String exibirNoCatalogo() {
        return String.format("%-8s | %-30s | %-18s | %3d tokens | %.0f%% desconto | val: %s",
            codigo, descricao, parceiro, custoTokens, percentualDesconto, validade);
    }

    public String exibirResgatado() {
        return "\n========================================\n"
             + "  CUPOM RESGATADO COM SUCESSO!\n"
             + "========================================\n"
             + "  Parceiro  : " + parceiro + "\n"
             + "  Oferta    : " + descricao + "\n"
             + "  Desconto  : " + percentualDesconto + "%\n"
             + "  Codigo    : >>> " + codigo + " <<<\n"
             + "  Validade  : " + validade + "\n"
             + "========================================";
    }

    public String getCodigo()                  { return codigo; }
    public String getDescricao()               { return descricao; }
    public String getParceiro()                { return parceiro; }
    public int    getCustoTokens()             { return custoTokens; }
    public double getPercentualDesconto()      { return percentualDesconto; }
    public String getValidade()                { return validade; }
    public boolean isResgatado()               { return resgatado; }
    public void setResgatado(boolean resgatado){ this.resgatado = resgatado; }
}
