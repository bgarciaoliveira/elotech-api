package br.garcia.dto;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class TarefaUpdateDto {

    @NotNull
    private String id;

    @NotNull
    @Size(max = 100)
    private String titulo;

    @Size(max = 500)
    private String descricao;

    @NotNull
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
