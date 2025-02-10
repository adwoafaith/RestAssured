package PathAndQueryParams;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndParams {

    @Test
    void testQueryAndPathParameters(){
        //url
        //https://reqres.in/api/users?delay=3


        given()
                .pathParam("userPath","users")
                .queryParam("page",2)
                .queryParam("id",3)
                .when()
                .get("https://reqres.in/api/{userPath}")// we don't add the queryParams here because they are not variables, but for the pathParam we gave it our our variable name
                .then()
                .statusCode(200)
                .log().all();
    }
}
