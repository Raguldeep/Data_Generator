package Maven.maven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadProperties {
	
	public static Map<String, String> main(String[] args) throws IOException {
		
		Map<String,String> propertyMap = new HashMap();
		
		FileInputStream file = new FileInputStream("D:\\Ragul\\Project\\maven\\config\\Config_Parameter_file.properties");
		Properties prop = new Properties();
		prop.load(file);
		
		
		Enumeration keys = prop.propertyNames();
		System.out.println("finish");

        while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            propertyMap.put(key,prop.getProperty(key));
        }
         
        file.close();
        return propertyMap;
	}
}
