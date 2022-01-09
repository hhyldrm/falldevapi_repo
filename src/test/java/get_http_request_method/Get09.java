package get_http_request_method;
import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class Get09 extends HerokuappBaseUrl {
    /*
       When
           I send GET Request to https://restful-booker.herokuapp.com/booking/2
       Then
           Response body should be like that;
           {
               "firstname": "Sally",
               "lastname": "Wilson",
               "totalprice": 335,
               "depositpaid": false,
               "bookingdates": {
                   "checkin": "2015-11-23",
                   "checkout": "2019-08-15"
                }
           }
    */
    @Test
    public void get09(){
        //Set the url
        spec.pathParams("first", "booking", "second", 2);
        //Set the expected data
        Map<String, Object> expectedBookingDates = new HashMap<>();
        expectedBookingDates.put("checkin", "2017-12-29");
        expectedBookingDates.put("checkout", "2018-03-12");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Eric");
        expectedData.put("lastname", "Jackson");
        expectedData.put("totalprice",661 );
        expectedData.put("depositpaid", false);
        expectedData.put("bookingdates", expectedBookingDates);
        //Send The Get request and Get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData : "+actualData);
//
//
        assertEquals("The data does not match!", expectedData.get("firstname"),actualData.get("firstname") );
        assertEquals("The data does not match!", expectedData.get("lastname"),actualData.get("lastname") );
        assertEquals("The data does not match!", expectedData.get("totalprice"),actualData.get("totalprice") );
        assertEquals("The data does not match!", expectedData.get("depositpaid"),actualData.get("depositpaid") );
        assertEquals("The data does not match!", expectedBookingDates.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin") );
        assertEquals("The data does not match!", expectedBookingDates.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout") );
        assertEquals("The data does not match!", expectedBookingDates,actualData.get("bookingdates") );
    }
}