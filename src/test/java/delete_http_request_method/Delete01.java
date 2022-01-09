package delete_http_request_method;

import base_urls.jsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete01 extends jsonPlaceHolderBaseUrl {
    /*
        When
            I send DELETE Request to the Url https://jsonplaceholder.typicode.com/todos/198
        Then
            Status code is 200
            And Response body is {}
    */
    @Test
    public void delete01(){
        //Set the Url
        spec.pathParams("first","todos","second",198);

        //Set the expected Data
        Map<String,Object> expectedData=new  HashMap<>();

        //Send the Delete request and GEt the response
        Response response = given().spec(spec).when().delete("/{first}/{second}");


        Map<String,Object>actualData=response.as(HashMap.class);

        response.then().assertThat().statusCode(200);

        response.prettyPrint();

        assertEquals("The data does not match!",expectedData,actualData);

    }
}
