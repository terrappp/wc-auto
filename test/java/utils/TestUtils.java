package utils;

import java.util.Hashtable;

import base.TestBase;

public class TestUtils extends TestBase {
	
	public static Object[][] getData(String sheetName) {		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table = null;
		
		for (int rowNum=2; rowNum<=rows; rowNum++) {
			
			table = new Hashtable<String, String>();
			
			for (int colNum=0; colNum<cols; colNum++) {
//				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
			}
			data[rowNum-2][0] = table;
		}
		
		return data;
	}

}
