package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Kannan Ahluwalia", "The Hon. Tara Chaturvedi" and "Damayanti Dubashi" are among the users
        And
            The female users are less than or equals to male users
            (Kadın kullanıcı sayısı erkek kullanıcı sayısından küçük yada eşit olamlı)
     */

    @Test
    public void get11() {
        //        i)   Set the URL
        spec.pathParams("first","users");

//        ii)  Set the expected data

//        iii) Send the request and get the response
        Response response=given().spec(spec).get("first");
        response.prettyPrint();

//        iv)  Do assertion = postman egirip terminalindeki datalara göre yazılıyor
        response.
                then().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data",hasSize(10),
                        "data.status",hasItem("active"),
                        "data.name", hasItems("Uttam Varman", "Agniprava Verma Esq.", "Devasree Pandey" ));
                                                // bu isimleri postman terminalden aldık

        //Kadın ve erkek sayılarını karşılaştıralım:
        //1. Yol:
        JsonPath jsonPath = response.jsonPath();
        List<String> genders = jsonPath.getList("data.gender");
        System.out.println("genders = " + genders);
        int kadinSayisi = 0;
        for (String w : genders) {
            if (w.equals("female")) {
                kadinSayisi++;
            }
        }
        System.out.println("kadinSayisi = " + kadinSayisi);
        assertTrue(kadinSayisi <= genders.size() - kadinSayisi);

        //2. Yol: Kadın ve erkek sayılarını Groovy kullanarak karşılaştıralım:
        List<String> kadinKullaniciList = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println("kadinKullaniciList = " + kadinKullaniciList);
        List<String> erkekKullaniciList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println("erkekKullaniciList = " + erkekKullaniciList);

        assertTrue(kadinKullaniciList.size() <= erkekKullaniciList.size());

    }
}
