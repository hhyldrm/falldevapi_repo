package get_http_request_method;

import base_urls.restApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;

public class Get10 extends restApiBaseUrl {

    /*
         When
             I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/7
         Then
             HTTP Status Code should be 200
         And
             Content Type should be JSON
         And
            Make sure Rhona Davidson earns more than Herrod Chandler
           {
            "id": 7,
            "employee_name": "Herrod Chandler",
            "employee_salary": 137500,
            "employee_age": 59,
            "profile_image": ""
        },
        {
            "id": 8,
            "employee_name": "Rhona Davidson",
            "employee_salary": 327900,
            "employee_age": 55,
            "profile_image": ""
        },
     */

    @Test
    public void get10(){
        //set the Url
        spec.pathParams("first","api","second","v1","third","employee","final",7);

        //set the expected data

        //Send the request and Get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        response.prettyPrint();

        //do assertion for status code and content type
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath json1 = response.jsonPath();

        int salaryOfHerrod = json1.getInt("data.employee_salary");
        System.out.println(salaryOfHerrod);






        //set the Url
        spec.pathParams("first","api","second","v1","third","employee","final",8);

        //set the expected data

        //Send the request and Get the response
        Response response2 = given().spec(spec).when().get("/{first}/{second}/{third}/{final}");
        response2.prettyPrint();

        JsonPath json2 = response2.jsonPath();

        int salaryOfRhona = json2.getInt("data.employee_salary");
        System.out.println(salaryOfRhona);

        assertTrue("The salary expectation does not match",salaryOfHerrod<salaryOfRhona);

    }


}
