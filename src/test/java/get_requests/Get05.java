package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
/*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a GET request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Sally" and last name is "Chen"
            (Data içerisinde firstname değeri "Sally", lastname değeri "Chen" olan biri olmalı)
     */
    //https://restful-booker.herokuapp.com/booking?firstname=Sally&lastname=Brown
    //               base url             /parametre/ çağırılacak datalar

    @Test
    public void get05() {
         //           i)    Set the URL
        spec.
                pathParam("first","booking").
                queryParams("firstname","Sally",
                        "lastname","Brown");

        //           ii)   Set the expected data    -- beklenen data

        //           iii)  Send the request and get the response --request i gönder response al
     Response response= given().spec(spec).when().get("/{first}");
      response.prettyPrint();

        //           iv)   Do assertion --
    response.then().statusCode(200); //Status code is 200

// 	  		Among the data there should be someone whose firstname is "Sally" and last name is "Chen"
        assertTrue(response.asString().contains("bookingid"));
    }
}
