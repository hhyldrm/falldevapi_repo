package post_http_request_method;

import base_urls.jsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post02  extends jsonPlaceHolderBaseUrl {
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
    public void post02(){
        //Set the url
        spec.pathParam("first", "todos");
        //Set expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        //create data using pojo
        Todo todo = new Todo(55, 201, "Tidy your room",false );
        Response response = given().spec(spec).contentType(ContentType.JSON).body(todo).when().post("/{first}");
        response.then().assertThat().statusCode(201);
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals("The data Does Not Match!",expectedData.get("userId") , actualData.get("userId"));
        assertEquals("The data Does Not Match!",expectedData.get("title") , actualData.get("title"));
        assertEquals("The data Does Not Match!",expectedData.get("completed") , actualData.get("completed"));
    }
}
