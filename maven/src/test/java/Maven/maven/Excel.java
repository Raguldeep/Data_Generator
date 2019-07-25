
package Maven.maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import jxl.write.WriteException;

public class Excel {

	static String Value = "";
	static String config_Path = "D:\\Git\\maven\\config\\Config_Parameter_file.properties";
	static String excelFileLocation = "D:/Sheets/MasterTestData.xlsx";
	static String result = "";
	static String resultss = "";
	static String FileOutputStream = "";

	static String[] splitColon;
	static String testcasename;

	public static void main(String[] args) throws IOException, FilloException, WriteException, InterruptedException {

	// LoadConfigPropFileMethod: Load the Configuration Property Files
		InputStream input = new FileInputStream(config_Path);
		Properties prop = new Properties();
		prop.load(input);
		prop.getProperty("NoofRecords");
		prop.getProperty("NoofRecords");

		System.out.println(prop.getProperty("NoofRecords"));
		System.out.println(prop.getProperty("NoofRecords"));
		int noofRows = Integer.parseInt(prop.getProperty("NoofRecords"));

		ArrayList<String> alFields = new ArrayList<String>();
		ArrayList<String> recordData = new ArrayList<String>();
		ArrayList<String> recordScen = new ArrayList<String>();

	// Store the Configuration Data's into ArrayList
		ArrayList<String> result = new ArrayList<String>();
		for (Entry<Object, Object> entry : prop.entrySet()) {
			if (((String) entry.getKey()).contains("-")) {
				// result.add((String) entry.getValue());
				resultss = resultss + entry + "," + "";
			}

		} // LoadConfigPropFileMethod:1

	// Create Master Sheet, Input Scenario ID and Column Name
		if (prop.getProperty("CreateMasterSheet").contains("true")) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Sheet");
			XSSFRow row;

			System.out.println("NoOfRows =" + noofRows);
			sheet.createRow(0).createCell(0).setCellValue("Scenario_ID");

			for (int i = 1; i <= noofRows; i++) {
				row = sheet.createRow(i);
				row.createCell(0).setCellValue("SC_" + i);
			}
			String header = resultss;
			// System.out.println(header);
			String[] array = header.split(",");
			

			System.out.println("NoOfColumns =" + array.length);
			for (int i = 0; i < array.length; i++) {
				row = sheet.getRow(0);
				row.createCell(i + 1).setCellValue(array[i].split("=")[0].split("-")[0]);
				// System.out.println(array[i]);
			}

			FileOutputStream file = new FileOutputStream(new File(excelFileLocation));
			workbook.write(file);

			HashMap<String, String> mastSheet = new HashMap<String, String>();
			HashMap<String, String> scenSheet = new HashMap<String, String>();
			HashMap<String, String> dataSheet = new HashMap<String, String>();
			HashMap<String, HashMap<String, String>> scenario_data = new HashMap<String, HashMap<String, String>>();

			Fillo fillo = new Fillo();

			Connection mast = fillo.getConnection(prop.getProperty("MasterSheetPath"));
			Connection data = fillo.getConnection(prop.getProperty("DataSourcePath"));
			Connection scen = fillo.getConnection(prop.getProperty("ScenarioSourcePath"));

			ReadData(array, mast, data, scen, alFields, recordData, dataSheet, scenario_data, recordScen);

			workbook.close();

			System.out.println("size OF DATA- " + dataSheet.size());
			System.out.println("size OF SCEN- " + scenSheet.size());

			for (String scenarioKey : scenario_data.keySet()) {
				String querybuilder = "";
				String query = null;
				for (String key : scenario_data.get(scenarioKey).keySet()) {
					if (!(key.equalsIgnoreCase("Scenario_ID"))) {
						// System.out.println( key );
						querybuilder = key + "='" + scenario_data.get(scenarioKey).get(key) + "'" + " , "
								+ querybuilder;
						// System.out.println();
					}
				}
				// System.out.println(querybuilder);
				String test = querybuilder.substring(0, querybuilder.length() - 2);
				query = "Update Sheet Set " + test + "where Scenario_ID='" + scenarioKey + "'";
				mast.executeUpdate(query);
				Thread.sleep(500);
			}
			data.close();
			scen.close();
			mast.close();
		}
	}

	public static void ReadData(String[] array, Connection mast, Connection data, Connection scen,
			ArrayList<String> alFields, ArrayList<String> recordData, HashMap<String, String> dataSheet,
			HashMap<String, HashMap<String, String>> scenario_data, ArrayList<String> recordScen) {
		try {
			// Read the type of data - STATIC or Dynamic..?
			// If STATIC pick data from data sheet
			for (int i = 0; i < array.length; i++) {
				if (!array[i].isEmpty()) {
					// System.out.println(array[i]);

					String[] spli = array[i].split("=");
					// System.out.println(spli[1]);
					String[] split = spli[1].split("#");
					// System.out.println(split[0]);
					// System.out.println(split[1]);

					splitColon = array[i].split("-");
					// System.out.println("Split Colon---------------->" + splitColon[0]);

					if (split[0].equalsIgnoreCase("static")) {

						// Write DataSource excel into Hasmap1
						Recordset recordsetdata;
						Recordset recordsetscen;

						String strQueryAll = "Select Scenario_ID from Sheet";
						Recordset recordsetmast = mast.executeQuery(strQueryAll);

						while (recordsetmast.next()) {
							ArrayList<String> colCollection = recordsetmast.getFieldNames();
							int Iter;
							int size = colCollection.size();
							for (Iter = 0; Iter <= (size - 1); Iter++) {
								String ColName = colCollection.get(Iter);
								testcasename = recordsetmast.getField(ColName);

								String strQuery2 = "Select * from Sheet Where Scenario_ID='" + testcasename.trim()
										+ "'";
								recordsetdata = data.executeQuery(strQuery2);

								String strQuery3 = "Select * from Sheet Where Scenario_ID='" + testcasename.trim()
										+ "'";
								recordsetscen = scen.executeQuery(strQuery3);

								// Data_Sheet
								try {
									alFields = recordsetdata.getFieldNames();

									while (recordsetdata.next()) {
										for (String str : alFields) {
											recordData.add(recordsetdata.getField(str));
											dataSheet.put(str, recordsetdata.getField(str));
										}
									}
									HashMap<String, String> temp = new HashMap<String, String>();
									temp = (HashMap<String, String>) dataSheet.clone();
									scenario_data.put(dataSheet.get("Scenario_ID"), temp);

								} catch (Exception e) {
									e.printStackTrace();
								}

								// Scenario_Sheet & Data_Sheet
								try {
									alFields = recordsetscen.getFieldNames();

									while (recordsetscen.next()) {
										for (String str : alFields) {
											recordScen.add(recordsetscen.getField(str));
											// scenSheet.put(str, recordsetscen.getField(str));
											dataSheet.put(str, recordsetscen.getField(str));
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								// dataSheet.putAll(scenSheet);
							}
						}
					} else if (split[0].equalsIgnoreCase("dynamic")) {
						System.out.println("dynamic");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
