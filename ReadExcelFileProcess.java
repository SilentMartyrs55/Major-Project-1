// **********************************************************************************
// Title: Major Project Part 2
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: ReadExcelFileProcess.java
// Description: This file reads a specific cell in an excel spreadsheet and returns that value
// **********************************************************************************
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.*;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
public class ReadExcelFileProcess  
   {  
      public static void main(String[] args)   
      {  
         ReadExcelFileProcess rc = new ReadExcelFileProcess();
         //Read the value of 2nd row and 2nd column
         //Will update this to allow for the user to specify data ranges  
         double vOutput = rc.ReadCellData(2, 2);   
         System.out.println(vOutput);  
      }  
         //Method for reading a cell  
         public double ReadCellData(int vRow, int vColumn)  
      {  
         //Initialize variables
         double value = 0.0;            
         Workbook wb = null;
         
         //Try loop that will catch and handle exceptions while attempting to read the file          
         try  
      {  
         //Read data from a file and store it  
         FileInputStream fis = new FileInputStream("C:\\Users\\Chris\\Desktop\\CrimeDatabaseTool\\MunicipalityDataFormatted.xlsx");  
         wb = new XSSFWorkbook(fis);  
      }  
         catch(FileNotFoundException e)  
      {  
         e.printStackTrace();  
      }  
         catch(IOException e1)  
      {  
         e1.printStackTrace();  
      }  
         //Locate the cell, store its value and return it
         Sheet sheet = wb.getSheetAt(0);     
         Row row = sheet.getRow(vRow);   
         Cell cell = row.getCell(vColumn);   
         value = cell.getNumericCellValue();      
         return value;                 
      }  
      }  