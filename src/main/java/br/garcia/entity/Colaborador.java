package br.garcia.entity;

import br.garcia.util.HashLibrary;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "client_id", strategy = "br.garcia.entity.generator.UUUIDGenerator")
    @GeneratedValue(generator = "client_id")
    private String id;

    @Column(name = "cpf", unique=true)
    @Size(min=11, max=11)
    @NotNull
    private String cpf;

    @Column(name = "email")
    @NotNull
    @Size(max=60)
    @Email
    private String email;

    @Column(name = "senha")
    @NotNull
    @Size(max=500)
    private String senha;

    @Column(name = "nome")
    @NotNull
    @Size(min=3, max=30)
    private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this.senha = HashLibrary.passwordHash(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
