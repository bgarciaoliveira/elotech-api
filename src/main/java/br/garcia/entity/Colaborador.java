package br.garcia.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    @NotNull
    @Size(min=3, max=30)
    private String nome;

    @Column(name = "email")
    @NotNull
    @Size(min=5, max=60)
    private String email;

    @Column(name = "cpf", unique=true)
    @NotNull
    private String cpf;

    @Column(name = "senha")
    @NotNull
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
