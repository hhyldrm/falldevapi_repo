package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class jsonPlaceHolderBaseUrl {
    //We will use ReSpec for storing base url into it
    protected RequestSpecification spec;

    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
}
