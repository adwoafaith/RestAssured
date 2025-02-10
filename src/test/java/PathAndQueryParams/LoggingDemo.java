package PathAndQueryParams;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingDemo {

    @Test
    void testLogs(){
        given()
                .when()
                .get("https://reqres.in/api/users?user=2")
                .then()
//                .log().body();//prints only body
//                .log().cookies(); //prints only cookies
                .log().headers();
    }
}
