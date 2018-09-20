package br.garcia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.developer.Serialization;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tarefa")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})



public class Tarefa {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "client_id", strategy = "br.garcia.entity.generator.UUUIDGenerator")
    @GeneratedValue(generator = "client_id")
    private String id;

    @Column(name = "codigo")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "titulo")
    @NotNull
    @Size(max = 100)
    private String titulo;

    @Column(name = "descricao")
    @Size(max = 500)
    private String descricao;

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TarefaStatus status;

    @ManyToOne
    @JoinColumn(name="colaborador_id")
    private Colaborador colaborador;

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

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public TarefaStatus getStatus() {
        return status;
    }

    public void setStatus(TarefaStatus status) {
        this.status = status;
    }

}
