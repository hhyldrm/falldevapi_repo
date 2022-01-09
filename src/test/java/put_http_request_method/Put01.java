package put_http_request_method;

import base_urls.jsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Todo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Put01 extends jsonPlaceHolderBaseUrl {

     /*
         When
            I send PUT Request to the Url https://jsonplaceholder.typicode.com/todos/198
            with the PUT Request body like {
                                            "userId": 21,
                                            "title": "Wash the dishes",
                                            "completed": false
                                           }
         Then
           Status code is 200
           And response body is like   {
                                        "userId": 21,
                                        "title": "Wash the dishes",
                                        "completed": false,
                                        "
                                       }
     */

    @Test
    public void put01(){
        //Set the url
        spec.pathParams("first","todos","second",198);

        //set the expected data 1. way
        Map<String,Object>expectedData = new HashMap<>();
        expectedData.put("userId",21);
        expectedData.put("title","Wash the dishes");
        expectedData.put("completed",false);


        //set the expected data 2. way
        Todo todo = new Todo(21,198,"Wash the dishes",false);

        //Send the Put request and Get the reponse

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();



        //1. way of validation
        response.then().assertThat().statusCode(200).body("title", equalTo("Wash the dishes")).
                body("userId", equalTo(21)).
                body("completed",equalTo(false));
        //2. way of validation
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals("The data Does not match!", expectedData.get("userId"),actualData.get("userId"));
        assertEquals("The data Does not match!", expectedData.get("title"),actualData.get("title"));
        assertEquals("The data Does not match!", expectedData.get("completed"),actualData.get("completed"));
        //3. way of validation
        Todo actualTodo = response.as(Todo.class);
        assertEquals("The data Does not match!", todo.getUserId(), actualTodo.getUserId());
        assertEquals("The data Does not match!", todo.getTitle(), actualTodo.getTitle());
        assertEquals("The data Does not match!", todo.isCompleted(), actualTodo.isCompleted());
        //4. way of validation
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(todo.getUserId(),jsonPath.getInt("userId") );
        Assert.assertEquals(todo.getTitle(),jsonPath.getString("title") );
        Assert.assertEquals(todo.isCompleted(),jsonPath.getBoolean("completed") );
        Assert.assertEquals(todo.getUserId(),Integer.parseInt(jsonPath.getString("userId")) );
    }
}
