package PathAndQueryParams;


import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesAndHeaders {
    //we declaring a varable and storing the cookie inside it, now the response will be of type response
    Response response;

    @Test
    void testCookies(){
       response = given()
               .when()
               .get("https://www.google.com/");
             /*  //extract only a single cookie from the response
        String cookieValue =response.getCookie("AEC");
        System.out.println(cookieValue);
        /*
              */
        //getting double or all cookies
        Map<String,String> cookies_values= response.getCookies();
        System.out.println(cookies_values.keySet()); //returns all cookies information or name,

        //loop through the map and store the first cookie in a variable called k

        for(String k: cookies_values.keySet()){
            String myCookie_value = response.getCookie(k);
            System.out.println(k+ "      "+ myCookie_value);
        }



    }
}
