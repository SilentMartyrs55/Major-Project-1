// **********************************************************************************
// Title: Major Project Part 3
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: TotalsByCounty.java
// Description: This file totals crimes by type and county and displays over how many years they occured
// **********************************************************************************

import java.util.*;
import java.util.LinkedList;
import java.text.DecimalFormat;

import java.util.Scanner;
      
      
   final class searchInstance{
      public double total;
      public double year;
      
      public searchInstance(double total, double year){
      this.total = total;
      this.year = year;
   }
   }
   


public class TotalsByCounty{

      
      public String[][] countyData;
      public String[][] munData;
   
      public static String[] addCounty(String search, String searchCrime){
    
      //Instance variables
      double total = 0;
      double year = 0;
      int i = 0;
      int j = 0;
      String[][] start = ReadExcelFile.getCountyData();
      
      //Determine what column of the excel file to read based off user's input
      if(searchCrime.equals("Murder"))
         j = 3;
      if(searchCrime.equals("Rape"))
         j = 4;
      if(searchCrime.equals("Robbery"))
         j = 5;
      if(searchCrime.equals("Aggrivated Assault"))
         j = 6;
      if(searchCrime.equals("Breaking and Entering"))
         j = 7;
      if(searchCrime.equals("Larceny"))
         j = 8;
      if(searchCrime.equals("Motor Vehicle Theft"))
         j = 9;
      
      //Make sure the user's input in a valid county in MD
      if (!Arrays.asList(Search.counties).contains(search))
      System.out.println(search + " is not in the list of counties in MD");
      
      //Total the select crime in the selected county
      while (i < 1103){
         if (start[i][0].equals(search + " County")){                 
                  total += (Double.parseDouble(start[i][j]));
               year++;
               i++;
         } else{
            i++;
         }     
      }
      String strTotal = String.valueOf(total);
      String strYear = String.valueOf(year);
      String[] result = {search, searchCrime, strTotal, strYear};
      return result;
   }
   
   public static String[] addMun(String search, String searchCrime){
      
      //Instance variables
      double total = 0;
      double year = 0;
      int i = 0;
      int j = 0;
      String[][] start = ReadExcelFile.getMunData();
      
      //Determine what column of the excel file to read based off user's input
      if(searchCrime.equals("Murder"))
         j = 4;
      if(searchCrime.equals("Rape"))
         j = 5;
      if(searchCrime.equals("Robbery"))
         j = 6;
      if(searchCrime.equals("Aggrivated Assault"))
         j = 7;
      if(searchCrime.equals("Breaking and Entering"))
         j = 8;
      if(searchCrime.equals("Larceny"))
         j = 9;
      if(searchCrime.equals("Motor Vehicle Theft"))
         j = 10;
      
      //Make sure the user's input in a valid county in MD
      if (!Arrays.asList(Search.municipalities).contains(search))
      System.out.println(search + " is not in the list of counties in MD");
      
      //Total the select crime in the selected county
      while (i < 4284){
         if (start[i][0].equals(search)){                 
                  total += (Double.parseDouble(start[i][j]));
               year++;
               i++;
         } else{
            i++;
         }     
      }
      String strTotal = String.valueOf(total);
      String strYear = String.valueOf(year);
      String[] result = {search, searchCrime, strTotal, strYear};
      return result;
   }
   
   
   }