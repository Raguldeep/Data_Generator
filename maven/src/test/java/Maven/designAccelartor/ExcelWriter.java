package Maven.designAccelartor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BusinessMethods.Login_Page;

public class ExcelWriter {

	static int newFlag = 0;
	static XSSFWorkbook wwb;
	static XSSFSheet sheet;
	static File filepath;

	public static WebDriver driver;
	
	Login_Page log = new Login_Page();
		
	public static void generatelocators(String tag, WebElement element) throws Exception {

		StringBuilder placeHolder = new StringBuilder();
		StringBuilder id = new StringBuilder();
		StringBuilder name = new StringBuilder();
		StringBuilder classname = new StringBuilder();
		StringBuilder fieldvalue = new StringBuilder();

		placeHolder.append(element.getAttribute("placeholder"));
		id.append(element.getAttribute("id"));
		name.append(element.getAttribute("name"));
		classname.append(element.getAttribute("class"));
		fieldvalue.append(element.getAttribute("value"));
		System.out.println("Entered fetching Select objects");
		String xpath = xpathGenerator(element, tag);
		String FieldText = xpath.substring(xpath.indexOf("~$")+2);
		xpath = xpath.substring(0, xpath.indexOf("~$"));
		if (tag.equals("button")) {
			FieldText = fieldvalue.toString();
		}

		dataUpdateWorkbook(FieldText, tag, id.toString(), name.toString(), classname.toString(), "", "", "", "", "", xpath);
	}

	public static void designAccelator() throws Exception {

		filepath = new File("");
		if (!filepath.exists() || newFlag == 0) {
			CreateWorkbook();
			newFlag = 1;
		}

		headerUpdateWorkbook();

		List<WebElement> linkElements3 = driver.findElements(By.tagName("select"));
		for (int i = 0; i < linkElements3.size(); i++) {
			System.out.println(i + "select size");
			generatelocators("select", linkElements3.get(i));
		}

		List<WebElement> linkElements5 = driver.findElements(By.tagName("button"));
		for (int i = 0; i < linkElements5.size(); i++) {
			System.out.println(i + "button size");
			generatelocators("Button", linkElements5.get(i));
		}

		StringBuilder id = null;

		List<WebElement> linkElements = driver.findElements(By.tagName("input"));
		for (int i = 0; i < linkElements.size(); i++) {

			String sAttribute = linkElements.get(i).getAttribute("type");
			String elementValue = linkElements.get(i).getAttribute("value");
			if (elementValue.equals("select")) {
				System.out.println("*************************Input Type Select*********************************");
				generatelocators("select", linkElements.get(i));
			} else {
				switch (sAttribute) {
				case "text":
					System.out.println("*************************Input Type Select*********************************");
					generatelocators("EditBox", linkElements.get(i));
					break;

				case "button":
					System.out.println("*************************Input Type Button*********************************");
					generatelocators("Button", linkElements.get(i));
					break;

				case "radio":
					System.out.println("*************************Input Type Radio*********************************");
					generatelocators("Radio", linkElements.get(i));
					break;

				case "checkbox":
					System.out.println("*************************Input Type CheckBox*********************************");
					generatelocators("checkbox", linkElements.get(i));
					break;

				default:
					break;
				}
			}
		}
	}

	// Create a new Workbook

	public static void CreateWorkbook() {
				try {
					System.out.println("Create a Workbook");
					FileOutputStream outStream = new FileOutputStream(filepath);
					wwb =  new XSSFWorkbook();
					sheet = wwb.createSheet("");
					wwb.write(outStream);
					outStream.close();
				} catch (Exception e) {
					e.printStackTrace(); 
				}
	}
			
	public static void headerUpdateWorkbook() throws IOException {
				FileInputStream inputstreams = new FileInputStream(filepath);
				wwb =  new XSSFWorkbook(inputstreams);
				sheet = wwb.getSheetAt(0);
				int rowCount = sheet.getLastRowNum();
				System.out.println(rowCount);
				ArrayList<String> str = new ArrayList<String>(); 		
				
				if(rowCount < 1) {
					Row row = sheet.createRow(0);
					row.createCell(0).setCellValue("Field_Text");
					row.createCell(1).setCellValue("Field_Type");
					row.createCell(2).setCellValue("Attribute_ID");
					row.createCell(3).setCellValue("Attribute_Name");
					row.createCell(4).setCellValue("Attribute_InnerText");
					row.createCell(5).setCellValue("Attribute_Class");
					row.createCell(6).setCellValue("Attribute_Value");
					row.createCell(7).setCellValue("Attribute_Placeholder");
					row.createCell(8).setCellValue("Attribute_Title");
					row.createCell(9).setCellValue("Attribute_TextValue");
					row.createCell(10).setCellValue("Attribute_xpath");
					
				}
				
				inputstreams.close();
				FileOutputStream outStream = new FileOutputStream(filepath);
				wwb.write(outStream);
				outStream.close();
			}

	public static void dataUpdateWorkbook(String sf1, String sf2, String sf3, String sf4, String sf5, String sf6, String sf7, String sf8, String sf9, String sf10, String sf11) throws IOException {
				System.out.println("Sample");
				FileInputStream inputstreams = new FileInputStream(filepath);
				wwb = new XSSFWorkbook(inputstreams);
				sheet = wwb.getSheetAt(0);
				int rowCount = sheet.getLastRowNum();
				System.out.println(rowCount);
				if((sf1 == null || sf1.equals("") || sf1.equals("null")) && !(sf3.equals(""))) {
					sf1 = sf3;
				} else if((sf1 == null || sf1.equals("") || sf1.equals("null")) && !(sf4.equals(""))) {
					sf1 = sf4;
				}
				if (!sf11.contains("//")) {
					sf11 = "";
				}
				
				Row row = sheet.createRow(rowCount+1);
				System.out.println(sf1);
				sf1 = sf1.replace(":", "").trim();
				sf1 = sf1.replace("*", "").trim();
				row.createCell(0).setCellValue(sf1.replaceAll(" ", "_"));
				row.createCell(1).setCellValue(sf2);
				row.createCell(1).setCellValue(sf3);
				row.createCell(1).setCellValue(sf4);
				row.createCell(1).setCellValue(sf5);
				row.createCell(1).setCellValue(sf6);
				row.createCell(1).setCellValue(sf7);
				
		inputstreams.close();
		FileOutputStream outStream = new FileOutputStream(filepath);
		wwb.write(outStream);
		outStream.close();
		}
			
	public static String generateXPathBuilder(String xpathValue, String xpathType, String newXpath) {
				StringBuffer attribute = new StringBuffer();
				StringBuffer label = new StringBuffer();
				StringBuffer value = new StringBuffer();
				StringBuffer fieldText = new StringBuffer();
				StringBuffer xValue = new StringBuffer();		
				attribute.append(getValue(xpathValue, "Attribute"));
				attribute.append(getValue(xpathValue, "Label"));
				attribute.append(getValue(xpathValue, "value"));				
				xValue.append(generateXpath(attribute.toString(), label.toString(), value.toString()));
				newXpath = xValue +"/preceding-sibling::" +newXpath;
				System.out.println(newXpath);
				return newXpath;			
				}
					
	public static String xpathGenerator(WebElement linkElements, String type) {
						
				// TODO Auto-generated method stub
				int identifiedFlag = 0;
				String newXpath = null, tagName;
				tagName = linkElements.getTagName();
				String[] strFlow = {"Following", "Preceding", "Parent", "Parent_sibling", "Parent_sib_child", "Grand_Parent", 
									"Grand_Parent_sibling", "Grand_ParentFollow_sibling"};
				tagName = linkElements.getTagName();
				if(tagName.equals("input")) {
					newXpath = tagName+"[@type='" + type +"']";
				}else {
					newXpath = tagName;
				}
				String xPathValue;
					String attribute, label, value = null, fieldText = null;
					List<WebElement> preceding, Parent_Sibling, Grand_Parent_Sibling;
					WebElement Parents = null, Grand_Parents = null;
					int iCount = 0;
					do {
						System.out.println("###########################################################");
						System.out.println(strFlow[iCount]);
						switch (strFlow[iCount]) {
						
						case "Following":
							if(!(type.equals("select"))) {
							preceding = linkElements.findElements(By.xpath("following-sibling"));
							System.out.println(preceding.size());
							if (preceding.size() > 0) {
								System.out.println("Entering into preceding");
								xPathValue = findElementsTag(preceding);
								if(xPathValue != null) {
									newXpath = generateXPathBuilder(xPathValue, "/preceding-sibling::", newXpath);
									identifiedFlag = 1;
								}
							}
						}
						break;
						
						case "preceding":
							preceding = linkElements.findElements(By.xpath("preceding-sibling::"));
							if(preceding.size() > 0) {
								System.out.println("Entering into preceding");
								xPathValue = findElementsTag(preceding);
								if(xPathValue != null) {
									newXpath = generateXPathBuilder(xPathValue, "/following-sibling::", newXpath);
									identifiedFlag = 1;
								}
							}
							break;

						case "Parent":
							preceding = linkElements.findElements(By.xpath("parent::"));
							tagName = Parents.getTagName();
							System.out.println("Entering into parents " + tagName);
							xPathValue = findParentTag(Parents, type);
							if (xPathValue != null) {
								newXpath = generateXPathBuilder(xPathValue, "/", newXpath);
								identifiedFlag = 1;
								System.out.println(newXpath);
							} else {
								newXpath = tagName + "/" + newXpath;
							}
							break;
							
						case "Parent_sibling":
							Parent_Sibling = Parents.findElements(By.xpath("preceding-sibling::"));
							if(Parent_Sibling.size() > 0) {
								System.out.println("Entering into Parent preceding");
							xPathValue = findElementsTag(Parent_Sibling);
							System.out.println(xPathValue);
							if (xPathValue != null) {
								newXpath = generateXPathBuilder(xPathValue, "/following-sibling::", newXpath);
								identifiedFlag = 1;
								System.out.println(newXpath);
							} 
							}
							break;
							
						case "Grand_Parent":
							Grand_Parents = Parents.findElement(By.xpath("parent::"));
							tagName = Grand_Parents.getTagName();
							System.out.println("Entering into Grand Parents " + tagName);
							xPathValue = findParentTag(Grand_Parents, type);
							if (xPathValue != null) {
								newXpath = generateXPathBuilder(xPathValue, "/", newXpath);
								identifiedFlag = 1;
								System.out.println(newXpath);
							}else {
								newXpath = tagName + "/" + newXpath;
							}
							break;
							
						case "Grand_Parent_sibling":
							Grand_Parent_Sibling = Grand_Parents.findElements(By.xpath("preceding-sibling::*[1]"));
							if(Grand_Parent_Sibling.size() > 0) {
								System.out.println("Entering into Grand Parent preceding");
								xPathValue = findElementsTag(Grand_Parent_Sibling);
								System.out.println(xPathValue);
								if(xPathValue != null) {
									newXpath = generateXPathBuilder(xPathValue, "following-sibling::", newXpath);
									identifiedFlag = 1;
									System.out.println(newXpath);
								} else {
									List<WebElement> child = Grand_Parent_Sibling.get(0).findElements(By.xpath("descendant::"));
									System.out.println(child.size());
									if(child.size() > 0) {
										System.out.println("Entering into Descendant");
										xPathValue = FindChildElementTag(child);
										System.out.println(xPathValue);
										if (xPathValue != null) {
											newXpath = generateXPathBuilder(xPathValue, "/../following-sibling::", newXpath);
											System.out.println(newXpath);
											identifiedFlag = 1;
										}
									}
								}
							}
							break;
							
						case "Grand_ParentFollow_sibling":
							Grand_Parent_Sibling = Grand_Parents.findElements(By.xpath("following-sibling::*"));
							if(Grand_Parent_Sibling.size() > 0) {
								System.out.println("Entering into Grand Parent preceding");
								xPathValue = findElementsTag(Grand_Parent_Sibling);
								System.out.println(xPathValue);
								if(xPathValue != null) {
									newXpath = generateXPathBuilder(xPathValue, "/preceding-sibling::", newXpath);
									identifiedFlag = 1;
									System.out.println(newXpath);
								} 
							}
							break;
						default:
							break;
						} 
						iCount++;
						
				} while (identifiedFlag == 0 & iCount < strFlow.length);
					if(fieldText != null) {
						if(fieldText.indexOf("\n") > 0) {
							fieldText = fieldText.substring(0, fieldText.indexOf("\n"));
						} else {
							fieldText = fieldText.substring(0, 25);
						}
					}
			
			return newXpath + "~$" + fieldText;
}

	public static String FindChildElementTag(List<WebElement> element) {
		String tagName = null;
		System.out.println(element.get(0).getTagName());
		System.out.println(element.get(0).getText());
		for (int j = 0; j < element.size(); j++) {
			System.out.println(element.get(j).getTagName());
			if (element.get(j).getAttribute("for") != null) {
				tagName = "FOR" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getAttribute("for") + "~$"
						+ element.get(j).getText();
				break;
			} else if (!element.get(j).getText().equals("")) {
				System.out.println(element.get(j).getTagName());
				tagName = "Text-decendent" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getText() + "~$"
						+ element.get(j).getText();
				break;
			}
		}
		return tagName;
	}

	public static String findElementsTag(List<WebElement> element) {
		String tagName = null;
		System.out.println(element.get(0).getTagName());
		for (int j = 0; j < element.size(); j++) {
			System.out.println(element.get(j).getTagName());
			if (element.get(j).getAttribute("for") != null) {
				tagName = "FOR" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getAttribute("for") + "~$"
						+ element.get(j).getText();
				break;
			} else if (!element.get(j).getText().equals("")) {
				System.out.println(element.get(j).getTagName());
				List<WebElement> childElements = element.get(j).findElements(By.xpath("descendant::*"));
				if (childElements.size() > 0) {

				} else {
					tagName = "Text-decent" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getText()
							+ "~$";
				}
				break;
			}
		}
		return tagName;
	}

	public static String findParentTag(WebElement element, String type) {
		String tagName = null;
		System.out.println(element.getTagName() + " - " + element.getText());
		if (element.getAttribute("for") != null) {
			tagName = "FOR" + "~#" + element.getTagName() + "~*" + element.getAttribute("for") + "~$";
			System.out.println(element.getText());
		} else if (!element.getText().equals("") && !(type.equals("select"))) {
			System.out.println(element.findElement(By.xpath("descendant::*")).getTagName());

			List<WebElement> childElements = element.findElements(By.xpath("descendant::*"));
			if (childElements.size() > 0) {
				for (int i = 0; i < childElements.size(); i++) {
					if (childElements.get(i).getText() != null & !(childElements.get(i).getText().equals(""))) {
						tagName = "Text-decent" + "~#" + childElements.get(i).getTagName() + "~*"
								+ childElements.get(i).getText() + "~$";
						break;
					}
				}
			} else {
				tagName = "Text" + "~#" + element.getTagName() + "~*" + element.getText();
			}
		}
		return tagName;
	}

	public static String generateXpath(String attribute, String label, String value) {
		String xpathValue = null;
		value = value.replace("*", "");
		switch (attribute) {
		case "FOR":
			xpathValue = "//" + label + "[@for='" + value + "']";
			break;
		case "Text":
			if (value.length() > 30) {
				if (value.indexOf("\n") > 0) {
					value = value.substring(0, value.indexOf("\n"));
				} else {
					value = value.substring(0, 30);
				}
				xpathValue = "//" + label + "[contains(text(), '" + value + "')]/..";
			} else {
				xpathValue = "//" + label + "[text()= '" + value + "')]/..";
			}
			break;

		case "Text-decendent":
			if (value.length() > 30) {
				if (value.indexOf("\n") > 0) {
					value = value.substring(0, value.indexOf("\n"));
				} else {
					value = value.substring(0, 30);
				}
				xpathValue = "//" + label + "[contains(text(), '" + value + "')]/";
			} else {
				xpathValue = "//" + label + "[text(), '" + value + "')]/";
			}
			break;
		default:
			break;
		}
		return xpathValue;
	}

	public static String getValue(String value, String valueType) {
		String tempValue = null;
		switch (valueType) {
		case "Attribute":
			tempValue = value.substring(0, value.indexOf("~#"));
			break;

		case "Label":
			tempValue = value.substring(value.indexOf("~#") + 2, value.indexOf("~*"));
			break;

		case "Value":
			tempValue = value.substring(value.indexOf("~*") + 2, value.indexOf("~$"));
			break;

		case "Field Text":
			tempValue = value.substring(value.indexOf("~$") + 2);

		default:
			break;
		}
		return tempValue;
	}
	
}
