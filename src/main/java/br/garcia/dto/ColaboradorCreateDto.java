package br.garcia.dto;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class ColaboradorCreateDto {

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=11, max=11)
    @Pattern(regexp="/^(([0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2})|([0-9]{11}))$/ \n")
    private String cpf;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=60)
    @Pattern(regexp="/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/\n")
    private String email;

    @NotNull
    @NotEmpty
    @Size(max=500)
    private String senha;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
