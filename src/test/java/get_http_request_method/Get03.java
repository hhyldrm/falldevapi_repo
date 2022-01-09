package get_http_request_method;

import base_urls.jsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends jsonPlaceHolderBaseUrl {

    /*
            Given
                https://jsonplaceholder.typicode.com/todos/23
            When
                User send GET Request to the URL
            Then
                HTTP Status Code should be 200
            And
                Response format should be "application/json"
            And
                "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
            And
                "completed" is false
            And
                "userId" is 2
         */
    @Test
    public void get03(){
        //Set the url
        spec.pathParams("first","todos","second",23);

        //Set the expected data
        //Send the request and the get responce
        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();
        //This is 1.way
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).body("userId",equalTo(2));

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                "completed",equalTo(false),"userId",equalTo(2));

    }


}
