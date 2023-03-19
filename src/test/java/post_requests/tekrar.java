package post_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class tekrar extends GoRestBaseUrl {
    @Test
    public void odev1() {
        spec.pathParams("first","users");

        Response response=given().spec(spec).get("first");
        response.prettyPrint();

        response.then().statusCode(200).body("meta.pagination.limit",equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data",hasSize(10),"data.status",hasItem("active"),
                "data.name", hasItems("Uttam Varman", "Agniprava Verma Esq.", "Devasree Pandey" ));

        JsonPath jsonPath=response.jsonPath();
        List<String>genders=jsonPath.getList("data.gender");
        int kadınsayisi=0;
        for (String w:genders){
            if (w.equals("female")){
                kadınsayisi++;
            }
        }
        assertTrue(kadınsayisi<=genders.size()-kadınsayisi);

        List<String>kadın=jsonPath.getList("data.findAll{it.gender=='female'.gender");
        List<String>erkek=jsonPath.getList("data.findAll{it.gender=='male'.gender");

        assertTrue(kadın.size()<=erkek.size()-kadın.size());
    }
}
