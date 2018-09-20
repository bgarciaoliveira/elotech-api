package br.garcia.dto;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class ColaboradorAuthDto {
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
