// **********************************************************************************
// Title: Major Project Part 4
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
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;


public class BuildGui extends Search{
                            
      //Instance Variables
      public static String[][] countyData = ReadExcelFile.getCountyData();
      public static String[][] munData = ReadExcelFile.getMunData();
      String searchTerm;
      String[] searchResults;
      public String search;
      public String searchCrime; 
        
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
      
      //Create Region View Tab and Column labels 
      String columns[] = {"Jurisdiction", "Year", "Population", "Murder", "Rape", "Robbery", "Agg. Assault", "B&E", "Larceny Theft"};
      JPanel regionViewTab = new JPanel();
      regionViewTab.setLayout(new BoxLayout(regionViewTab, BoxLayout.Y_AXIS));
      
      //Create regionType1
      JTable importedData2 = new JTable(countyData, columns);
      JScrollPane regionType1 = new JScrollPane(importedData2);
      
      //Create regionType2
      String columns2[] = {"Juriscition", "County", "Year", "Population", "Murder", "Rape", "Robbery", "Agg. Assault", "B&E", "Larceny Theft", "M/V Theft"};
      JTable importedData3 = new JTable(munData, columns2);
      JScrollPane regionType2 = new JScrollPane(importedData3);
      
      //Combo box for region view tab
      JLabel regionLabel = new JLabel("Select your table type:");
      String[] regionsString = {"County", "Municipality",};
      JComboBox regions = new JComboBox(regionsString);
      regions.setMaximumSize(new Dimension(100,200));
      regions.setAlignmentX(Component.TOP_ALIGNMENT);
      regionViewTab.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.RED));
      regions.setForeground(Color.BLUE);
      regions.setFont(new Font("Arial", Font.BOLD, 14));
                 
      //ActionListener for region selector
      regions.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
         JComboBox<String> combo = (JComboBox<String>) e.getSource();
         String region = (String)combo.getSelectedItem();
         
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
         regionViewTab.revalidate();
         regionViewTab.repaint();
      }
      });
      
      //Add the label, comboBox and resetButton to the region view panel
      JPanel northBorder = new JPanel();
      northBorder.add(regionLabel, BorderLayout.NORTH);
      northBorder.add(regions, BorderLayout.NORTH);
      northBorder.add(resetButton, BorderLayout.NORTH);
      regionViewTab.add(northBorder);

      
      //Create blank JTable for Process Data Tab
      DefaultTableModel model = new DefaultTableModel(0, 0);
      model.addColumn("County/Municipality");
      model.addColumn("Crime");
      model.addColumn("Total");
      model.addColumn("Years");
      
      JTable processDataTable = new JTable(model);
      //JScrollpane that houses JTable
      JScrollPane tablePane = new JScrollPane(processDataTable);
      
      //Create the processData tab
      JPanel processDataTab = new JPanel();
      processDataTab.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.RED));
      processDataTab.setLayout(new BorderLayout());
      JPanel northData = new JPanel();
      
      //County/municipality search bar
      JLabel label2 = new JLabel("Enter the County/Municipality: ");
      northData.add(label2, BorderLayout.NORTH);
      JTextField countyField = new JTextField(20);
      northData.add(countyField, BorderLayout.NORTH);
      
      //Crime search bar
      JLabel label3 = new JLabel("Enter the Crime Type: ");
      northData.add(label3, BorderLayout.NORTH);
      JTextField crimeField = new JTextField(20);
      northData.add(crimeField, BorderLayout.NORTH);
      
      //Calcualte button
      JButton calc = new JButton("Calculate");
      northData.add(calc, BorderLayout.NORTH);
      
      //Crime type label
      JLabel crimeTypes = new JLabel("     Crime Types Include: Murder, Rape, Robbery, Aggrivated Assault, Breaking and Entering, Larceny, Motor Vehicle Theft");
      crimeTypes.setFont(new Font("Arial", Font.BOLD, 20));
      crimeTypes.setForeground(Color.blue);
      processDataTab.add(crimeTypes, BorderLayout.CENTER);
            
      //Add the northdata and table to the process data tab
      processDataTab.add(northData, BorderLayout.NORTH);
      processDataTab.add(tablePane, BorderLayout.SOUTH);
      
      //Calculate button and action listener
      calc.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
            search = countyField.getText();
            searchCrime = crimeField.getText();
            String[] newRow = null;
            
            if (Arrays.asList(Search.counties).contains(search))
               {
               newRow = TotalsByCounty.addCounty(search, searchCrime);
               model.addRow(newRow);
               }
            else if (Arrays.asList(Search.municipalities).contains(search))
               {
               newRow = TotalsByCounty.addMun(search, searchCrime);
               model.addRow(newRow);
               }
            countyField.setText("");
            crimeField.setText("");
            
            }
      });

      //Reset button and action listener
      JButton reset = new JButton("Reset");
      reset.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      
         processDataTab.remove(processDataTable);
         regionViewTab.revalidate();
         regionViewTab.repaint();

      }
      });
      
      //Create the search tab
      JPanel searchTab = new JPanel();
      searchTab.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.RED));
      DefaultTableModel model1 = new DefaultTableModel(0, 0);
      JTable searchTable = new JTable(model1);      
      JScrollPane regionType3 = new JScrollPane(searchTable);
      regionType3.setPreferredSize(new Dimension(1000, 1000));
       
      //Search bar for search tab
      JLabel searchLabel = new JLabel("Search by County or Municipality: ");
      JTextField searchBox = new JTextField(20);
      JButton go = new JButton("SEARCH");
      
      //Add components to search Tab
      //searchTab.add(imported);
      searchTab.add(searchLabel);
      searchTab.add(searchBox);
      searchTab.add(go);
      searchTab.add(regionType3);
      
      DefaultTableModel model3 = new DefaultTableModel(50, 50);
      
      //Search button action listener
      go.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent event) {
      
               search = searchBox.getText();
               String[][] temp = Search.linearCountySearch(countyData, search);
               String[][] tempest = Search.linearMunSearch(munData, search);
               
               if (Arrays.asList(Search.municipalities).contains(search)){
                  DefaultTableModel model4 = new DefaultTableModel(tempest, columns2);
                  searchTable.setModel(model4);
                  }
                  else if (Arrays.asList(Search.counties).contains(search)){
                     DefaultTableModel model5 = new DefaultTableModel(temp, columns2);
                     searchTable.setModel(model5);

               }
            }
        } );
         
      //Create the help tab
      JPanel helpTab = new JPanel();
      helpTab.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.RED));
      helpTab.setLayout(new BoxLayout(helpTab, BoxLayout.Y_AXIS));
      
      //Show county list
      JLabel countiesLabel = new JLabel("These are valid counties in Maryland");
      countiesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      countiesLabel.setFont(new Font("Arial", Font.BOLD, 20));
      countiesLabel.setForeground(Color.blue);
      helpTab.add(countiesLabel);
      JList countyList = new JList(counties);
      countyList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
      countyList.setVisibleRowCount(10);
      helpTab.add(countyList);
      
      //Show Municipality list
      JLabel munLabel = new JLabel("These are valid municipalities in Maryland");
      munLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      munLabel.setFont(new Font("Arial", Font.BOLD, 20));
      munLabel.setForeground(Color.blue);
      helpTab.add(munLabel);
      JList munList = new JList(municipalities);
      munList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
      munList.setVisibleRowCount(20);
      helpTab.add(munList);
      
      helpTab.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.RED));
      
      //Create the tabbed pane and add the cards
      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.addTab("Welcome to the Maryland Crime Database Tool", welcomeTab);
      tabbedPane.addTab("Region View", regionViewTab);
      tabbedPane.addTab("Search", searchTab);
      tabbedPane.addTab("Process Data", processDataTab);
      tabbedPane.addTab("Help", helpTab);
      
      //Create the frame and show
      frame.getContentPane().add(BorderLayout.CENTER, tabbedPane);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      }
}