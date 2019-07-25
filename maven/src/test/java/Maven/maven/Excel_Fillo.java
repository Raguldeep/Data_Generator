/*package Maven.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel_Fillo {
	
	static String EXCEL_FILE_LOCATION = "D:/Ragul/TestData_add.xls";
	
	public static void LoadProperties() throws IOException {
		FileReader reader = new FileReader(EXCEL_FILE_LOCATION);
		Properties p = new Properties();
		p.load(reader);
	}
	
	
	public static void createMasterSheet() {
		
		//Create New Sheet based on Config Condition
		if (p.getProperty("CreateMaster_Sheet").equals("true")) {
	 		WritableWorkbook myFirstWbook = null;
			myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
			System.out.println("Master Sheet Created");
		}
		
	}

	
	
	public static void main(String[] args) throws Exception {
		
		Map<String,String> props = readPropertyFiles();
		printPropertiesInConsole(props);
		
	
			LoadProperties();


	
	
	

				
	//Read the Properties files 
	public static Map<String,String> readPropertyFiles() throws Exception {
        Map<String,String> propertyMap = new HashMap();
        
		FileReader reader = new FileReader(EXCEL_FILE_LOCATION);
		Properties p = new Properties();
		p.load(reader);
        
		Enumeration keys = p.propertyNames();
        while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            propertyMap.put(key,p.getProperty(key));
            for(String key1:p.keySet()) {
                System.out.println(key1+":"+props.get(key1));
            }
        }	
        
        reader.close();
        return propertyMap;
	}
		
    private static void printPropertiesInConsole(Map<String,String> props) {
     
        
		//Create New Sheet based on Config Condition
		if (p.getProperty("CreateMaster_Sheet").equals("true")) {
	 		WritableWorkbook myFirstWbook = null;
			myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
			System.out.println("Master Sheet Created");
		}
			
    }
		HashMap<String, String> map=new HashMap<String, String>();
		Set<Object> keys = p.keySet();
		
		for(Object k:keys){
			String key =(String) k;
			String value = p.getProperty(key);
			System.out.println("print");
		}
		

		// 1. Create an Excel file

		try {

			myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

			// create an Excel sheet
			WritableSheet excelSheet = myFirstWbook.createSheet("Sheet1", 0);

			// add something into the Excel sheet
			Label label = new Label(0, 0, "SL.No");
			excelSheet.addCell(label);

			Number number = new Number(0, 1, 1);
			excelSheet.addCell(number);

			label = new Label(1, 0, "FirstName");
			excelSheet.addCell(label);

			label = new Label(2, 0, "LastName");
			excelSheet.addCell(label);

			number = new Number(0, 2, 2);
			excelSheet.addCell(number);

			label = new Label(1, 1, "Rag");
			excelSheet.addCell(label);

			myFirstWbook.write();
			myFirstWbook.close();

			// FileOutputStream fos = new FileOutputStream(file);

			Connection conn = fillo.getConnection("D:/Ragul/TestData_add.xls");
			String strQuery = "INSERT INTO Sheet1(FirstName,LastName) VALUES('null','null')";

			// String strQuery = "CREATE TABLE Sheet1(firstname)";

			conn.executeUpdate(strQuery);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
}



*/