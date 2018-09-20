package br.garcia.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ColaboradorUpdateSenhaDto {

    @NotNull
    @NotEmpty
    @Size(max=500)
    private String senhaAntiga;

    @NotNull
    @NotEmpty
    @Size(max=500)
    private String senhaNova;

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }
}
