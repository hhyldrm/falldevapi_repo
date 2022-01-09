package get_http_request_method;

import base_urls.restApiBaseUrl;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.Employee;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Get11 extends restApiBaseUrl {
    /*
     When
       I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
      Status code should be 200
      Use Gson and ObjectMapper
      make sure you have 24 records for data
 */
    @Test
    public void get11()throws Exception{
        //Set the url
        spec.pathParams("first", "api", "second", "v1", "third", "employees");
        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        //Validate the status code
        response.then().assertThat().statusCode(200);
        ObjectMapper obj = new ObjectMapper(); //This is a converter from Java to Json, Json to Java
        Employee employees = obj.readValue(response.asString(),Employee.class);
        for (int i = 0; i < employees.getData().size(); i++) {
            System.out.println("The person "+i+" is "+ employees.getData().get(i).getEmployee_name());
        }
        assertTrue("The data size does not Match!", employees.getData().size() == 24);
    }
    @Test
    public void get11D(){

        //Set the url
        spec.pathParams("first", "api", "second", "v1", "third", "employees");
        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        System.out.println("===========");
        //Do assertion
        Gson gson = new Gson();
        Employee employees = gson.fromJson(response.asString(),Employee.class);
        System.out.println("The employees data size: " + employees.getData().size());

        assertTrue("The data does not match!", employees.getData().size()==24);




    }
}