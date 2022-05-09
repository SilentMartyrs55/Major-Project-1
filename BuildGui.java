// **********************************************************************************
// Title: Major Project Part 3
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: BuildGui.java
// Description: This file creates the GUI for my program
// **********************************************************************************
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*; 
import java.net.MalformedURLException;
import java.net.*;
import java.io.*;
import java.util.Arrays;
import javax.swing.table.*;

public class BuildGui extends Search{
                
   //Methods to fill the JTable with the contents of the desired excel file             
   public static String[][] input(){
            String[][] output = ReadExcelFile.Read();
            return output;
           }
   public static String[][] input2(){
            String[][] output1 = ReadExcelFile2.Read();
            return output1;
            }
            
            String[][] countyData = ReadExcelFile.Read();
            String[][] munData = ReadExcelFile2.Read();
            String searchTerm;

            
        
      public BuildGui() throws Exception{
   
      //Create the frame
      JFrame frame = new JFrame("Maryland Crime Database Tool");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
      //Create the welcome tab and put in the picture
      URL img = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Greetings_from_Maryland_%28NBY_593%29.jpg/1280px-Greetings_from_Maryland_%28NBY_593%29.jpg") ;
      ImageIcon icon = new ImageIcon(img);
      JLabel label1 = new JLabel(icon);
      JPanel welcomeTab = new JPanel();
      welcomeTab.add(label1);
      
      //Create the import tab and functionality to import the excel file
      JPanel importTab = new JPanel(new BorderLayout(0,3));
      JButton importButton = new JButton("Import from excel file");
      importButton.setSize(100,100);
      importTab.add(importButton, BorderLayout.NORTH);
      importButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
               //importedData.addRow(input());         
            }
         });
    
      //Create and populate the importedData table 
      String columns[] = {"Jurisdiction", "Year", "Population", "Murder", "Rape", "Robbery", "Agg. Assault", "B&E", "Larceny Theft"};
      JTable importedData = new JTable(input(), columns);
      JScrollPane scrollPane = new JScrollPane(importedData);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      importTab.add(scrollPane, BorderLayout.CENTER);
      
      JPanel regionViewTab = new JPanel();
      
      //Create regionType1
      JTable importedData2 = new JTable(input(), columns);
      JScrollPane regionType1 = new JScrollPane(importedData2);
      regionType1.setPreferredSize(new Dimension(1000, 1000));   
      
      //Create regionType2
      String columns2[] = {"Juriscition", "County", "Year", "Population", "Murder", "Rape", "Robbery", "Agg. Assault", "B&E", "Larceny Theft", "M/V Theft"};
      JTable importedData3 = new JTable(input2(), columns2);
      JScrollPane regionType2 = new JScrollPane(importedData3);
      regionType2.setPreferredSize(new Dimension(1000, 1000)); 
      
      
      //Create sub tabs for region pane and add their pictures
      JTabbedPane subTabs = new JTabbedPane();
      URL statePic = new URL("https://t4.ftcdn.net/jpg/01/66/60/97/360_F_166609757_6H0cHO6QSKB7OdPf2kLeGIOaVEoABpz8.jpg");
      ImageIcon statewidePic = new ImageIcon(statePic);
      JLabel statewideLabel = new JLabel(statewidePic);
      subTabs.addTab("Statewide View", statewideLabel);
      
      URL countyPic = new URL("https://msa.maryland.gov/msa/mdmanual/36loc/images/02maps/seatc.jpg");
      ImageIcon countyViewPic = new ImageIcon(countyPic);
      JLabel countyViewLabel = new JLabel(countyViewPic);
      subTabs.addTab("County View", countyViewLabel);
      
      URL mPic = new URL("https://i.etsystatic.com/21348027/r/il/d45c6a/3036919394/il_570xN.3036919394_org6.jpg");
      ImageIcon mViewPic = new ImageIcon(mPic);
      JLabel mViewLabel = new JLabel(mViewPic);;
      subTabs.addTab("Municipality View", mViewLabel);
      
      //Combo box for region view tab
      JLabel regionLabel = new JLabel("Select your table type:");
      String[] regionsString = {"Statewide", "County", "Municipality",};
      JComboBox regions = new JComboBox(regionsString);
      
      //Create searchTable
      //JTable searchData = new JTable(Search.linearCountySearch, columns);
      //JScrollPane regionType3 = new JScrollPane(searchData);
      //regionType3.setPreferredSize(new Dimension(1000, 1000));   
      
      //Search bar for region view tab
      JLabel searchLabel = new JLabel("Search by County or Municipality: ");
      JTextField searchBox = new JTextField(20);
      JButton go = new JButton("GO");
      go.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event) {
               String searchTerm = searchBox.getText();
               if (Arrays.asList(counties).contains(searchTerm))
                  //searchData.add(Search.linearCountySearch(countyData, searchTerm));
                  //regionViewTab.add(regionType3);
                  Search.linearCountySearch(countyData, searchTerm);
               if (Arrays.asList(municipalities).contains(searchTerm))
                  Search.linearMunSearch(munData, searchTerm);
            }
         });
      
      
      
      //ActionListener for region selector
      regions.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         JComboBox regions = (JComboBox)e.getSource();
         String region = (String)regions.getSelectedItem();
         
         if (region == "Statewide")
            System.out.println("Statewide");
         if (region == "County")
            regionViewTab.add(regionType1);
         if (region == "Municipality")
            regionViewTab.add(regionType2);
      }
      });
      
      //Add resetButton to clear table and allow for a different one to populate upon selection
      JButton resetButton = new JButton("Reset");
      resetButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      
         regionViewTab.remove(regionType1);
         regionViewTab.remove(regionType2);
         regionViewTab.repaint();
      }
      });
      
      
      //Add the label, comboBox and resetButton to the region view pane
      regionViewTab.add(regionLabel);
      regionViewTab.add(regions);
      regionViewTab.add(resetButton);
      regionViewTab.add(searchLabel);
      regionViewTab.add(searchBox);
      regionViewTab.add(go);
      
      //Create blank JTable for Process Data Tab
      String[] columnsAdd = {"County", "Crime", "Total"};
      int numRows = 15;
      DefaultTableModel model = new DefaultTableModel(numRows, columnsAdd.length);
      model.setColumnIdentifiers(columnsAdd);
      JTable processDataTable = new JTable(model);
      
      //Create the processData tab
      JPanel processDataTab = new JPanel();
      JLabel label2 = new JLabel("Enter the County: ");
      processDataTab.add(label2);
      JTextField countyField = new JTextField(20);
      processDataTab.add(countyField);
      JLabel label3 = new JLabel("Enter the Crime Type: ");
      processDataTab.add(label3);
      JTextField crimeField = new JTextField(20);
      processDataTab.add(crimeField);
      JButton go2 = new JButton("GO");
      processDataTab.add(go2);
      JButton reset = new JButton("Reset");
      processDataTab.add(reset);
      //processDataTab.add(processDataTable, BorderLayout.SOUTH);
      reset.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
            processDataTab.add(processDataTable);


      }
      });
      
      
      //Create the tabbed pane and add the cards
      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.addTab("Welcome to the Maryland Crime Database Tool", welcomeTab);
      tabbedPane.addTab("Import", importTab);
      tabbedPane.addTab("Region View", regionViewTab);
      tabbedPane.addTab("Process Data", processDataTab);
      
      //Create the frame and show
      frame.getContentPane().add(BorderLayout.CENTER, tabbedPane);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      }
}