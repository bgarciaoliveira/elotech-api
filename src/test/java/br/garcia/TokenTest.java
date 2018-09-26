package br.garcia;

import br.garcia.configuration.Props;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class TokenTest {

    @BeforeClass
    public static void setup(){
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/";
    }

    @Test
    public void sendRequestWithNoToken(){

    }

    @Test
    public void sendRequestWithInvalidToken(){

    }
}
