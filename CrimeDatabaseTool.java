// **********************************************************************************
// Title: Major Project Part 1
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: CrimeDatabaseTool.java
// Description: This file contains the main method and runs the GU
// **********************************************************************************
import javax.swing.SwingUtilities;

public class CrimeDatabaseTool{

   public static void main(String[] args){
      
         SwingUtilities.invokeLater(
            new Runnable() {
               public void run() {
               try{
                  new BuildGui();
               } catch (Exception e){
                  System.out.println(e);
               }
               }
            });
      }
   }
