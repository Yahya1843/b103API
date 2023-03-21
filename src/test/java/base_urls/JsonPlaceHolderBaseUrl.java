package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {



    protected RequestSpecification spec;

    @Before
    public void setUp() throws Exception {

        spec = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com").
                build();

    }


        //RequestSpecBuilder().
// setContentType(ContentType.JSON).
// setAccept(ContentType.JSON).
// setBaseUri("https://jsonplaceholder.typicode.com").
// build();

}