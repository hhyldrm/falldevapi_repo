package get_http_request_method;

import base_urls.HerokuappBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class Get02 extends HerokuappBaseUrl {

    /*
    Given https://restful-booker.herokuapp.com/booking/1001

    When user sends a GET request to the url

    Then HTTP status code should be 404

    And   response body contains "Not Found"

    And status line should be HTTP/1.1 404 Not Found

    And body does not contain "techproed"

    And Server is "Cowboy"
            */

    //String url =  https://restful-booker.herokuapp.com/booking/1001
    @Test
    public void get02() {
    //Set Url
        spec.pathParams("first","booking","second",1001);

        //Set the expected date
        //Send the request and Get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do the assertions and validate

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //When you use JUnit assertion, you can leave a message at the beginning
        //When you use assertion with TestNg annotations, then you can leave the message at the end

        assertTrue("The data expected does not match",response.asString().contains("Not Found"));
        System.out.println("==================");
        System.out.println(response.asString().contains("techproed"));
        assertFalse(response.asString().contains("techproed"));
        System.out.println(response.getHeader("Server"));
        assertEquals(response.getHeader("Server"),"Cowboy");


    }
}