import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DiffWaysToCreatePostRequestBody {

    //@Test
    void testPostingUsingHashMap(){
        //String courseArray[]={"C","C++"}; //we want to add an array as part of our data

        HashMap hashMap = new HashMap();
        hashMap.put("name","delasi");
        hashMap.put("job","leader");


       // hashMap.put("courses",courseArray);

//        given()
//                .contentType("application/json")
//                .body(hashMap)
//
//                .when()
//                .post("https://httpbin.org/post")
//                .then()
//                .statusCode(200)
//                .body("name",equalTo("delasi"))
//                .body("location",equalTo("France"))
//                .body("phone",equalTo("123456"))
//                .body("courses[0]",equalTo("C"))
//                .body("courses[1]",equalTo("C++"))
//                .header("Content-Type","application/json; charset=utf-8")
//                .log().all();
//
//
//    }
        given()
                .contentType("application/json")
                .body(hashMap)

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("delasi"))
                .body("job",equalTo("leader"))

                .log().all();


    }

    //@Test()
    void postRequestBodyUsingOrgJsonLibrary(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","mohammed");
        jsonObject.put("job","tester");

        given()
                .contentType("application/json")
                .body(jsonObject.toString()) //we cannot pass the jsonobject data directly inot the body, we will have to convert it into a string first

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("mohammed"))
                .body("job",equalTo("tester"))
                .log().all()    ;


    }


    //@Test()
    void postRequestBodyUsingPOJOClass(){
       Pojo_PostRequest data = new Pojo_PostRequest();
       data.setName("Henry");
       data.setJob("Backend");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("Henry"))
                .body("job",equalTo("Backend"))
                .log().all()    ;


    }

    @Test()
    void postRequestBodyUsinExternalFile() throws FileNotFoundException {
        //we need to read the file
        File file = new File("C:\\Users\\FaithAyehMensah\\Desktop\\RestAssuredProject\\src\\test\\java\\body.json"); //get to the file itself
        FileReader fileReader =new FileReader(file); //read the data from the file after getting it
        JSONTokener jsonTokener = new JSONTokener(fileReader); //get the json format of the file
        JSONObject data = new JSONObject(jsonTokener); //extract the data in a json format


        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("metusala"))
                .body("job",equalTo("gang leader"))
                .log().all()    ;


    }
}
