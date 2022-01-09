package data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderData {




    public static Map<String, Object> expectedDataSetup(){
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        return expectedData;
    }
    public static Map<String, Object> expectedDataSetup(int userId,String title, boolean isCompleted){
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", isCompleted);
        return expectedData;
    }
}