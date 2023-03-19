package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03_Pojo extends JsonPlaceHolderBaseUrl {
     /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",    // url e bu body i yollayarak bir post request yapmamız isteniyor
            "completed": false             // karşıdan gelen datanın alttaki body olması gerekiyor
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room", //
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post03() {
        //Set the URL
        spec.pathParam("first", "todos");

        // url e bu body i yollayarak bir post request yapmamız isteniyor
        // karşıdan gelen datanın alttaki body olması gerekiyor

        //Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55, "Tidy your room", false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do Assertion  // burada kaşıdan gelenler ile gönderilenler karılaştırılır
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode()); // ilk önce status code karşılaştırılır
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());

    }
}