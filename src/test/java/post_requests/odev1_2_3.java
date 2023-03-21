package post_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;
import pojos.RegresPojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class odev1_2_3 extends ReqresBaseUrl {
    //1)

  /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
              (Tüm pantone_value değerlerini yazdırınız)
            3)Print all ids greater than 3 on the console
              (3'ten büyük id'leri yazdırınız)
              Assert that there are 3 ids greater than 3
              (3'ten büyük 3 adet id olduğunu doğrulayınız)
            4)Print all names whose ids are less than 3 on the console
              (id'si 3'ten küçük isimleri yazdırınız)
              Assert that the number of names whose ids are less than 3 is 2
              (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)
    */

    @Test
    public void odev01() {
        // Set the url
        spec.pathParam("first","unknown");

        //        ii)  Set the expected data

        //        iii) Send the request and get the response
        Response response=given().spec(spec).get("first");
        response.prettyPrint();

        //        iv)  Do assertion
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data",hasSize(6));

        //        1)Status code is 200
        response.then().assertThat().statusCode(200);
        //        2)Print all pantone_values
        JsonPath jsonPath= response.jsonPath();
        List<String> pantone_value = jsonPath.getList("data.pantone_value");
        System.out.println("pantone_value = " + pantone_value);

      //  List<String> id = jsonPath.getList("data.id");
       // System.out.println("id = " + id);

        //        3)Print all ids greater than 3 on the console
        List<Integer> id_üctenBüyük = jsonPath.getList("data.findAll{it.id>3}.id");//"data.findAll{it.gender=='female'}.name" sadece isimleri yazdırı
        System.out.println("id_üctenBüyük = " + id_üctenBüyük);

//        Assert that there are 3 ids greater than 3
        Assert.assertEquals(3, id_üctenBüyük.size());

        //        4)Print all names whose ids are less than 3 on the console
        List<String> id_ikidenKücük = jsonPath.getList("data.findAll{it.id<3}.name");//"data.findAll{it.gender=='female'}.name" sadece isimleri yazdırı
        System.out.println("id_ikidenKücük = " + id_ikidenKücük);

//        Assert that the number of names whose ids are less than 3 is 2
        Assert.assertEquals(2,id_ikidenKücük.size());

    }
//2)

  /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */

    @Test
    public void odev02() {
        //Set the URL
        spec.pathParam("first", "users");

        //Set the expected data
        RegresPojo expectedData = new RegresPojo( "morpheus", "leader");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do Assertion  // burada kaşıdan gelenler ile gönderilenler karılaştırılır
        RegresPojo actualData = response.as(RegresPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode()); // ilk önce status code karşılaştırılır
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());



    }

    /* 2. yol
    @Test
    public void post01(){
        spec.pathParam("first", "users");
        ReqresTestData reqresTestData = new ReqresTestData();//Map oluşturacak method oluşturunuz
        Map<String,String > expectedData = reqresTestData.reqresUsersSetUp("morpheus","leader");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String,String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(expectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(expectedData.get("job"),actualData.get("job"));
     */
//3)

/*
   "https://petstore.swagger.io/" dökümanını kullanarak 'user' oluşturacak bir otomasyon testi yazınız
    Not: Test Case'i gherkin language ile yazınız.
*/
    /*
    iven
            1) https://petstore.swagger.io/v2/user
            2) {
                  "username": "JohnDoe",
                  "firstName": "John",
                  "lastName": "Doe",
                  "email": "john@doe.com",
                  "password": "1234",
                  "phone": "1234",
                  "userStatus": 123
                }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "code": 200,
                                                "type": "unknown",
                                                "message": "6874988058"
                                             }
     */

    @Test
    public void post01() {
        spec.pathParam("first", "user");
        Map<String, Object> expectedData = new HashMap<>();//Pojo class ile de payload oluşturabilirsiniz
        expectedData.put("username", "JohnDoe");
        expectedData.put("firstName", "John");
        expectedData.put("lastName", "Doe");
        expectedData.put("email", "john@doe.com");
        expectedData.put("password", "1234");
        expectedData.put("phone", "1234");
        expectedData.put("userStatus", 123);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String ,Object> actualData =response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(200,actualData.get("code"));
        assertEquals("unknown",actualData.get("type"));
    }


}
