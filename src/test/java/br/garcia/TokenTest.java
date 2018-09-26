package br.garcia;

import br.garcia.configuration.Props;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
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
        Response response = RestAssured.given()
            .contentType("application/json")
            .get("colaboradores/me");

        Assert.assertEquals(400, response.statusCode());
    }

    @Test
    public void sendRequestWithInvalidToken(){
        Response response = RestAssured.given()
                .contentType("application/json")
                .header("token", "invalid")
                .get("colaboradores/me");

        JSONObject jsonResponse = new JSONObject(response.getBody().print());

        Assert.assertEquals(400, response.statusCode());

        Assert.assertEquals("INVALID_TOKEN", jsonResponse.getString("info"));
    }
}
