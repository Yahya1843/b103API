package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class tekrar extends HerOkuAppBaseUrl {
     /*
     Given
         https://restful-booker.herokuapp.com/booking/2170
     When
         I send GET Request to the url
     Then
         Response body should be like that;
          {
           "firstname": "John",
           "lastname": "Smith",
           "totalprice": 111,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2018-01-01",
               "checkout": "2019-01-01"
           },
           "additionalneeds": "Breakfast"
           }
  */

    @Test
    public void tekrar () {
        //Set the URL
        spec.pathParams("first","booking","second",2170);

        //Set the expected data
        HerOkuAppTestData obje=new HerOkuAppTestData();
        Map<String,String>bookingdatesMap=obje.bookingdatesMapMethod("2018-01-01","2019-01-01");

        Map<String,Object>expectedData=obje.expectedDataMethod("John","Smith",111,true,bookingdatesMap,"Breakfast");

        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response =// İsteği gönderin ve yanıtı alın
        Response response=given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),(actualData.get("totalprice")));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));




    }
}
