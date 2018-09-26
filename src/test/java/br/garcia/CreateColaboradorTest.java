package br.garcia;

import br.garcia.dto.ColaboradorCreateDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateColaboradorTest {

    @BeforeClass
    public static void setup(){
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/";
    }

    @Test
    public void tryCreateWithInvalidEmail(){
        ColaboradorCreateDto colaborador = new ColaboradorCreateDto();
        colaborador.setEmail("bruno@invalid");
        colaborador.setNome("Bruno Garcia");
        colaborador.setSenha("bruno");
        colaborador.setCpf("60663809070");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(colaborador)
                .when().post("colaboradores/");

        Assert.assertEquals(400, response.statusCode());
    }

    @Test
    public void tryCreateWithInvalidCpf(){
        ColaboradorCreateDto colaborador = new ColaboradorCreateDto();
        colaborador.setEmail("bruno@hotmail.com");
        colaborador.setNome("Bruno Garcia");
        colaborador.setSenha("bruno");
        colaborador.setCpf("4125487965");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(colaborador)
                .when().post("colaboradores/");

        Assert.assertEquals(400, response.statusCode());
    }
}
