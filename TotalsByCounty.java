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
   
      public static searchInstance add(String searchCounty, String searchCrime){
      
      //Instance variables
      double total = 0;
      double year = 0;
      int i = 0;
      int j = 0;
      String[][] start = ReadExcelFile.Read();
      
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
      if (!Arrays.asList(Search.counties).contains(searchCounty))
      System.out.println(searchCounty + " is not in the list of counties in MD");
      
      //Total the select crime in the selected county
      while (i < 1103){
         if (start[i][0].equals(searchCounty + " County")){                 
                  total += (Double.parseDouble(start[i][j]));
               year++;
               i++;
         } else{
            i++;
         }     
      }
      return new searchInstance(total, year);
   }
      //Driver
      public static void main(String[] args){
      
      //Create the scanner and accept user input
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter a County: ");
      String searchCounty = sc.nextLine();
      System.out.print("Enter a Crime: ");
      String searchCrime = sc.nextLine();
      DecimalFormat df = new DecimalFormat("#,###");
      
      searchInstance search = add(searchCounty, searchCrime);
      
      //Display the results
      System.out.print(searchCounty + " County has had this many counts of " + searchCrime + ": ");
      System.out.print(df.format(search.total));
      System.out.print(" in the past " + (df.format(search.year)) + " years");   
   
   }
}


   
