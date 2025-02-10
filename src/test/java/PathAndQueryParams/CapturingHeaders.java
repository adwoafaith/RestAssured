package PathAndQueryParams;


import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CapturingHeaders {
    Response response;

    //@Test
    //this method is checking the existing headers
    void testHeader(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Encoding","gzip")
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .log().headers();
    }

   // @Test
    //this method will get a header into a variable
    void getSingleHeader(){
        response = given()
                .when()
                .get("https://www.google.com/");

        //get single header information
        String headerVAlue = response.getHeader("Content-Type");
        System.out.println(headerVAlue);

    }

    @Test
        //this method will get a header into a variable
    void getAllHeaders(){
        response = given()
                .when()
                .get("https://www.google.com/");

        //get single header information
        Headers myheaders = response.getHeaders();

        //get all the headers, this doesn't use a hasmap..

        for(Header hd: myheaders){
            System.out.println(hd.getName() + "   "+ hd.getValue());
        }



    }
}
