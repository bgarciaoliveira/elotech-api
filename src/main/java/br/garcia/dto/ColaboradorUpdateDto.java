package br.garcia.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ColaboradorUpdateDto {

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=11, max=11)
    private String cpf;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=60)
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=40)
    private String nome;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
