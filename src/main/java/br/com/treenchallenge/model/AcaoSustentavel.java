package br.com.treenchallenge.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.LocalDate;
@Entity @Table(name="acoes_sustentaveis")
public class AcaoSustentavel {

@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id; @NotBlank @Size(max=180) @Column(nullable=false,length=180)
    private String descricao;

@Positive @Column(nullable=false) private Double quantidade; @NotBlank @Size(max=30) @Column(nullable=false,length=30)
    private String unidade;

@NotNull @Column(nullable=false)
private LocalDate data; @NotBlank @Pattern(regexp="ENERGIA|RECICLAGEM",message="Categoria deve ser ENERGIA ou RECICLAGEM") @Column(nullable=false,length=20)
    private String categoria;

@Column(nullable=false)
private Integer pontos=0; @Column(nullable=false) private Integer xpGerado=0; @Column(nullable=false)
    private Integer tokensGerados=0;

@ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="usuario_id",nullable=false)
private Usuario usuario;

public AcaoSustentavel(){}
public Long getId(){return id;}
    public void setId(Long v){id=v;}
    public String getDescricao()
    {
        return descricao;
    }
    public void setDescricao(String v)
    {
        descricao=v;
    }
    public Double getQuantidade()
    {
        return quantidade;
    } public void setQuantidade(Double v)
    {
        quantidade=v;
    } public String getUnidade()
    {
    return unidade;
    } public void setUnidade(String v)
    {
        unidade=v;
    } public LocalDate getData()
    {
        return data;
    } public void setData(LocalDate v)
    {
    data=v;
    } public String getCategoria()
    {return categoria;
    } public void setCategoria(String v)
    {
    categoria=v;
    } public Integer getPontos()
    {
    return pontos;} public void setPontos(Integer v)
    {
        pontos=v;
    } public Integer getXpGerado()
    {
    return xpGerado;
    } public void setXpGerado(Integer v)
    {
        xpGerado=v;
    } public Integer getTokensGerados(){
    return tokensGerados;
} public void setTokensGerados(Integer v){
    tokensGerados=v;
} public Usuario getUsuario(){
    return usuario;
} public void setUsuario(Usuario v){
    usuario=v;
}
}
