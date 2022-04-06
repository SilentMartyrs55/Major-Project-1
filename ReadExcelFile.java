// **********************************************************************************
// Title: Major Project Part 1
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: ReadExcelFile.java
// Description: This file reads data from an excel spreadsheet and returns it as a 2 dimensional string array
// **********************************************************************************
import java.io.*;
import java.util.*;
import java.io.File;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile{
         
   public static String[][] Read(){

   //Create instance variables
	String[][] dataTable = null;
	File file = new File("C:\\Users\\Chris\\Desktop\\CrimeDatabaseTool\\Test.xlsx");
   
   //Try loop that allows for exception handling
	try {
   
      //Create input stream and designate starting point for collecting data
		FileInputStream xlfile = new FileInputStream(file);
		XSSFWorkbook xlwb = new XSSFWorkbook(xlfile);
		XSSFSheet xlSheet = xlwb.getSheetAt(0);

		// Create variables for the number of rows and columns
		int numRows = xlSheet.getLastRowNum() + 1;
		int numCols = xlSheet.getRow(0).getLastCellNum();
      
      //Tell the data table how many rows and columns it will need
		dataTable = new String[numRows][numCols];
      
      //Iterate through each row and cell of the file and store the contents in dataTable
		for (int i = 0; i < numRows; i++) {
			Row xlRow = xlSheet.getRow(i);
			for (int j = 0; j < numCols; j++) {
				Cell xlCell = xlRow.getCell(j);
				dataTable[i][j] = xlCell.toString();
			}
		}
	} 
   
   //Catch exceptions
   catch (IOException e) {
		System.out.println("ERROR FILE HANDLING " + e.toString());
	}
   
   //Return the filled data table
	return dataTable;
   }
   }