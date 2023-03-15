package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends HerOkuAppBaseUrl {
    /*
         Given
           1)  https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                }
        When
         I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01() {
        //Set the URL
        spec.pathParam("first","todos");

        //Set the expected data = Payload
        /*
        "userId": 55,
        "title": "Tidy your room",
        "completed": false,
         */
        Map<String,Object>expectedData = new HashMap<>();
        expectedData.put("userId",55.0);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);

        // Send the repuest and the response
        Response response=given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).post("/first");
        response.prettyPrint();

        // Do Assertion
        Map<String,Object> actualData=response.as(HashMap.class); //de-serialization = json dan javaya cevirir.
        System.out.println( "actualData"+ actualData );

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
    }
}
