package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Odev1 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is
	  		 "Almedin" and lastname is "Alikadic"
     */

    @Test
    public void odev1() {
        //            i)    Set the URL
        spec.
                pathParam("first","booking").
                queryParams("firstname","Josh","lastname","Allen");


        //           ii)   Set the expected data    -- beklenen data

        //           iii)  Send the request and get the response --request i gönder response al
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //           iv)   Do assertion --
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("bookingid"));
    }
}
