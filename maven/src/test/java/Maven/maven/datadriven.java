/*package Maven.maven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Workbook;

public class datadriven {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("D:\\Ragul\\Sheets\\DataSheet.xlsx");
		 XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 XSSFSheet sheet = workbook.getSheet("TestData");
	     Row row = sheet.createRow(1);
		 Cell cell = row.createCell(1);
		 cell.setCellType(Cell.CELL_TYPE_STRING);
		 cell.setCellValue("SAMPLE.com");
		 FileOutputStream fos = new FileOutputStream("D:\\Ragul\\Sheets\\DataSheet.xlsx");
		 workbook.write(fos);
		 fos.close();
		 System.out.println("END OF WRITING DATA IN EXCEL");
		 }
		}*/