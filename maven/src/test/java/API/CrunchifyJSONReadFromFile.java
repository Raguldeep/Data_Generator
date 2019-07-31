package API;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CrunchifyJSONReadFromFile {

	public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
        try {
    		
            Object obj = parser.parse(new FileReader("C:/Users/krishnap/Pictures/filej.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            String success = (String) jsonObject.get("success");
            String code = (String) jsonObject.get("code");
            String message = (String) jsonObject.get("message");
            String totalCount = (String) jsonObject.get("totalCount");
            String pageCount = (String) jsonObject.get("pageCount");
            String perPage = (String) jsonObject.get("perPage");
            String rateLimit = (String) jsonObject.get("rateLimit");
           
            //JSONArray companyList = (JSONArray) jsonObject.get("Company List");
 
            System.out.println("Name: " + success);
            System.out.println("code: " + code);
            System.out.println("message: " + message);
            System.out.println("totalCount: " + totalCount);
            System.out.println("pageCount: " + pageCount);
            System.out.println("perPage: " + perPage);
            System.out.println("rateLimit: " + rateLimit);
            //System.out.println("\nComapany List:");
            //Iterator<String> iterator = companyList.iterator();
/*            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
 
     */   } catch (Exception e) {
            e.printStackTrace();
        }
    }
}