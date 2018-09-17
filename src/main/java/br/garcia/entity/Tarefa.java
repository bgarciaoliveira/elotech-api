package br.garcia.entity;

import br.garcia.util.HashLibrary;
import br.garcia.util.UUUID;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


@Entity
@Table(name = "colaborador")
public class Tarefa {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "client_id", strategy = "br.garcia.entity.generator.UUUIDGenerator")
    @GeneratedValue(generator = "client_id")
    private String id;

    @Column(name = "codigo")
    @GeneratedValue
    private Long codigo;

    @Column(name = "titulo")
    @NotNull
    @Size(max = 100)
    private String titulo;

    @Column(name = "descricao")
    @NotNull
    @Size(max = 500)
    private String descricao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
