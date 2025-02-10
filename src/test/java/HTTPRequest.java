/*
given()
content type, set cookies, add auth, add, param, set headers, info etc...

when()
get,post,put,delete

then()
validate status code, extract response, extract headers cookies & response body.....

 */


import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPRequest {

    int id;

    @Test(priority = 1)
    void getUsers(){
        given()

                .when()
                    .get("https://reqres.in/api/users?page=2")

                .then()
                    .statusCode(200)
                    .body("page",equalTo(2)) //validate the data in the body
                    .log().all(); //log all the body in the console


    }

    @Test(priority = 2)
    void createUser(){
        HashMap hashMap = new HashMap();
        hashMap.put("name","pavan");
        hashMap.put("job","trainer");

        id =given()
                .contentType("application/json")
                .body(hashMap)
                     .when()
                     .post("https://reqres.in/api/users")
                     .jsonPath().getInt("id"); //get the id in the body that is being returned after you make a post request

    }

    @Test(priority = 3,dependsOnMethods ={"createUser"} )
        void updateUser(){
            HashMap hashMap = new HashMap();
            hashMap.put("name","helena");
            hashMap.put("job","QA engineer");
            given()
                    .contentType("application/json")
                    .body(hashMap)
                    .when()
                    .put("https://reqres.in/api/users/"+id)
                    .then()
                    .statusCode(200)
                    .log().all();

        }
    @Test(priority = 4, dependsOnMethods = ("createUser"))
    void deleteUser(){
        given()
                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204);
    }
}
