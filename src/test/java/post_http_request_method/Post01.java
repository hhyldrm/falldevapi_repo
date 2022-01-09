package post_http_request_method;
import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class Post01 extends HerokuappBaseUrl {
    /*
      When
          I send POST Request to the Url https://restful-booker.herokuapp.com/booking
          with the request body {
                                  "firstname": "Atabek",
                                  "lastname": "Akbalaev",
                                  "totalprice": 11111,
                                  "depositpaid": true,
                                  "bookingdates": {
                                      "checkin": "2022-09-09",
                                      "checkout": "2022-09-21"
                                   }
                                }
      Then
          Status code is 200
          And response body should be like {
                                              "bookingid": 11,
                                              "booking": {
                                                  "firstname": "Atabek",
                                                  "lastname": "Akbalaev",
                                                  "totalprice": 11111,
                                                  "depositpaid": true,
                                                  "bookingdates": {
                                                      "checkin": "2022-09-09",
                                                      "checkout": "2022-09-21"
                                                  }
                                              }
                                           }
   */
    @Test
    public void post01(){
        //Set the url
        spec.pathParam("first", "booking");
        //Set the expected data
        Map<String, Object> expectedBookingDates = new HashMap<>();
        expectedBookingDates.put("checkin", "2022-09-09");
        expectedBookingDates.put("checkout", "2022-09-21");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Atabek");
        expectedData.put("lastname", "Akbalaev");
        expectedData.put("totalprice", 11111);
        expectedData.put("depositpaid",true );
        expectedData.put("bookingdates", expectedBookingDates);
        //Send the Post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.then().assertThat().statusCode(200);
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);
        assertEquals("The data does not Match!", expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals("The data does not Match!", expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals("The data does not Match!", expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals("The data does not Match!", expectedData.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals("The data does not Match!", expectedData.get("bookingdates"), ((Map)actualData.get("booking")).get("bookingdates"));
    }
}
