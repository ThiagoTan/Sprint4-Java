package br.com.treenchallenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
@Entity
@Table(name="usuarios", uniqueConstraints=@UniqueConstraint (name="uk_usuario_email", columnNames="email"))
public class Usuario {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
@NotBlank
@Size(max=100) @Column(nullable=false, length=100) private String nome;
@NotBlank
@Email @Size(max=150) @Column(nullable=false, unique=true, length=150) private String email;
@NotBlank
@Size(max=100) @Column(nullable=false, length=100) private String cidade;
@Column(nullable=false)
private Integer pontuacaoTotal=0;
@Column(nullable=false)
private Integer xpTotal=0;
@Column(nullable=false)
private Integer saldoTokens=0;
@Column(nullable=false)
private LocalDateTime criadoEm=LocalDateTime.now();
    public Usuario() {
    }
    public Usuario(String n, String e, String c) {
        nome=n;
        email=e;
        cidade=c;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long v) {
        id=v;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String v) {
        nome=v;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String v) {
        email=v;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String v) {
        cidade=v;
    }
    public Integer getPontuacaoTotal() {
        return pontuacaoTotal;
    }
    public void setPontuacaoTotal(Integer v) {
        pontuacaoTotal=v;
    }
    public Integer getXpTotal() {
        return xpTotal;
    }
    public void setXpTotal(Integer v) {
        xpTotal=v;
    }
    public Integer getSaldoTokens() {
        return saldoTokens;
    }
    public void setSaldoTokens(Integer v) {
        saldoTokens=v;
    }
    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
    public void setCriadoEm(LocalDateTime v) {
        criadoEm=v;
    }
@Transient
public String getNivel() {
        if(pontuacaoTotal>=1500)return "Platina";
        if(pontuacaoTotal>=700)return "Ouro";
        if(pontuacaoTotal>=300)return "Prata";
        if(pontuacaoTotal>=100)return "Bronze";
        return "Iniciante";
    }
}
