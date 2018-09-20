package br.garcia.dto;

import javax.validation.constraints.*;

public class ColaboradorAuthDto {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max=60)
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
