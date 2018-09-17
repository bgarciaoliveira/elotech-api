package br.garcia.entity;

import br.garcia.util.HashLibrary;
import br.garcia.util.UUUID;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "client_id", strategy = "br.garcia.entity.generator.UUUIDGenerator")
    @GeneratedValue(generator = "client_id")
    private String id;

    @Column(name = "cpf", unique=true)
    @NotNull
    private String cpf;

    @Column(name = "email")
    @NotNull
    @Size(min=5, max=60)
    private String email;

    @Column(name = "senha")
    @NotNull
    private String senha;

    @Column(name = "nome")
    @NotNull
    @Size(min=3, max=30)
    private String nome;

    @Column(name = "tarefas")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colaborador", targetEntity = Tarefa.class, fetch = FetchType.LAZY)
    private List<Tarefa> tarefas;

    public String getId() {
        return id;
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

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
