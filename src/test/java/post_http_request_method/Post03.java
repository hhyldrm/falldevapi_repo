package post_http_request_method;



import base_urls.jsonPlaceHolderBaseUrl;
import data.JsonPlaceHolderData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class Post03 extends jsonPlaceHolderBaseUrl {
   /*
        When
	  		I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
	  		with the request body {
								    "userId": 55,
								    "title": "Tidy your room",
								    "completed": false
								   }
		Then
			Status code is 201
			And response body is like {
									    "userId": 55,
									    "title": "Tidy your room",
									    "completed": false,
									    "id": 201
									  }
     */

    @Test
    public void post03(){

        // Set the url

        spec.pathParam("first","todos");

        // Set the expected data

        Map<String, Object> expectedData =  new JsonPlaceHolderData().expectedDataSetup(55,"Tidy your room",false);

        // Send the post request and get response

        Response response = given().spec(spec).auth().basic("admin","1234")
                            .contentType(ContentType.JSON)
                            .body(expectedData).when()
                            .post("/{first}");

        response.prettyPrint();



        //Do assertion validation
        Map<String,Object> actualData = response.as(HashMap.class);

        response.then().assertThat().statusCode(201)
                .body("userId",equalTo(expectedData.get("userId")))
                .body("title",equalTo(expectedData.get("title")))
                .body("completed",equalTo(expectedData.get("completed")));


        assertEquals("The expected data not matched",expectedData.get("userId"),actualData.get("userId"));
        assertEquals("The expected data not matched",expectedData.get("title"),actualData.get("title"));
        assertEquals("The expected data not matched",expectedData.get("completed"),actualData.get("completed"));




    }



}