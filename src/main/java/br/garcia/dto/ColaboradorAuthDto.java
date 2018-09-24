package br.garcia.dto;


import br.garcia.annotation.Email;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ColaboradorAuthDto {
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(max=500)
    private String senha;

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
}
