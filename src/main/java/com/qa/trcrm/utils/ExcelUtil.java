package com.qa.trcrm.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	
	static Workbook book;
	static Sheet sheet;
	
	public static void main(String[] args) {
		getTestData("contacts");
	}
	
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null ;
		try {
			FileInputStream fis=new FileInputStream(AppConstants.TEST_SHEET_DATA);
			book=WorkbookFactory.create(fis);
			sheet=book.getSheet(sheetName);
			
			 data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
		
	}
}
