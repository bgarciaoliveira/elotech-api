package br.garcia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.developer.Serialization;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tarefa")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Tarefa {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "client_id", strategy = "br.garcia.entity.generator.UUUIDGenerator")
    @GeneratedValue(generator = "client_id")
    @NotNull
    @NotEmpty
    @NotBlank
    private String id;

    @NotNull
    private Long codigo;

    @Column(name = "titulo")
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    private String titulo;

    @Column(name = "descricao")
    @Size(max = 500)
    @NotNull
    private String descricao;

    @Column(name = "status")
    @NotNull
    @Min(1)
    @Max(5)
    private int status;

    @Column(name = "colaborador_id")
    @NotNull
    @NotEmpty
    @Size(max = 500)
    private String colaboradorId;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(String colaboradorId) {
        this.colaboradorId = colaboradorId;
    }
}
