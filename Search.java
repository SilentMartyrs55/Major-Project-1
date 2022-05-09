// **********************************************************************************
// Title: Major Project Part 3
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: Search.java
// Description: This file searches the excel files by county/municipality
// **********************************************************************************
import java.util.*;
import java.util.LinkedList;
import java.text.DecimalFormat;

   
public class Search{
   
    //Create the arrayList to store our search results
    public static ArrayList<String> ar = new ArrayList<String>();  
      
    //String array containing all counties in MD  
    public static String[] counties = {"Allegany", "Anne Arundel", "Baltimore", "Calvert", "Caroline", "Carroll", "Cecil", "Charles",
                         "Dorchester", "Frederick", "Garrett", "Harford", "Howard", "Kent", "Montgomery", "Prince George's",
                         "Queen Anne's", "Somerset", "St. Mary's", "Talbot", "Washington", "Wicomico", "Worcester"};
    
    //String array containing all municipalities in MD                     
    public static String[] municipalities = {"Aberdeen", "Accident", "Annapolis", "Baltimore", "Barclay", "Barnesville", "Barton", "Belair", "Berlin",
                                      "Berwyn Heights", "Betterton","Bladensburg", "Boonsboro", "Bowie", "Brentwood", "Brookeville", "Brunswick",
                                      "Burkittsville", "Cambridge", "Capitol Heights", "Cecilton", "Centreville", "Charlestown", "Chesapeake Beach",
                                      "Chesapeake City", "Chestertown", "Cheverly", "Chevy Chase", "Chevy Chase Sector III", "Chevy Chase Sector V",
                                      "Chevy Chase View", "Chevy Chase Village", "Church Creek", "Church Hill", "Clear Spring", "College Park", 
                                      "Colmar Manor", "Cottage City", "Crisfield", "Cumberland", "Deer Park", "Delmar", "Denton", "District Heights",
                                      "Eagle Harbor", "East New Market", "Easton", "Edmonston", "Elkton", "Emmittsburg", "Fairmount Heights",
                                      "Federalsburg", "Forest Heights", "Frederick", "Friendship Heights", "Freindsville", "Frostburg", "Fruitland",
                                      "Funkstown", "Gaithersburg", "Galena", "Galestown", "Garrett Park", "Glen Echo", "Glenarden", "Goldsboro",
                                      "Grantsville", "Greenbelt", "Greensboro", "Hagerstown", "Hampstead", "Hancock", "Havre De Grace", "Hebron",
                                      "Henderson", "Hillsboro", "Hurlock", "Hyattsville", "Indian Head", "Keedysville", "Kensington", "Kitzmiller",
                                      "Landover Hills", "LaPlata", "Laurel", "Laytonsville", "Leonardtown", "Loch Lynn Heights", "Lonaconing", 
                                      "Manchester", "Mardela Springs", "Martin's Addition", "Marydel", "Middletown", "Midland", "Millington",
                                      "Morningside", "Mount Rainier", "Mountain Lake Park", "Mt. Airy", "Myersville", "New Carrollton", "New Market",
                                      "New Windsor", "North Beach", "North Brentwood", "North Chevy Chase", "North East", "Oakland", "Ocean City",
                                      "Oxford", "Perryville", "Pittsville", "Pocomoke City", "Poolesville", "Port Deposit", "Preston", "Princess Anne",
                                      "Queen Anne", "Queenstown", "Ridgely", "Rising Sun", "Riverdale Park", "Rock Hall", "Rockville", "Rosemont",
                                      "Salisbury", "Seat Pleasant", "Sharpsburg", "Sharpstown", "Smithsburg", "Snow Hill", "Somerset", "St. Michaels",
                                      "Sudlersville", "Sykesville", "Takoma Park", "Taneytown", "Templeville", "Thurmont", "Trappe", "Union Bridge",
                                      "University Park", "Upper Marlboro", "Vienna", "Walkersville", "Washington Grove", "Westernport", "Westminster",
                                      "Willards", "Williamsport", "Woodsboro"
                                     };

    //This method searches the county data excel file and displays the crimes and their counts by years
    public static void linearCountySearch(String[][] countyData, String searchTerm)
    {
            DecimalFormat df = new DecimalFormat("#,###");
      int i = 0;
      int j = 0;
      
      if (!Arrays.asList(counties).contains(searchTerm))
         System.out.println(searchTerm + " is not in the list of counties in MD");
      
      while (i < 1103){
         if (countyData[i][j].equals(searchTerm + " County")){
            for(int k = 0; k < 10; k++)
               //ar.add(countyData[i][k]);
               System.out.print(countyData[i][k] + " - ");
               System.out.println();
               i++;
         } else{
            i++;
         }     
      }
      }
    
    //This method searches the municipality data excel file and displays the crimes and their counts by years  
      public static void linearMunSearch(String[][] munData, String searchTerm)
    {
    
      int l = 0;
      int m = 0;
      
      if (!Arrays.asList(municipalities).contains(searchTerm))
         System.out.println(searchTerm + " is not in the list of municipalities in MD");
      
      while (l < 4284){
         if (munData[l][m].equals(searchTerm)){
            for(int n = 0; n < 9; n++)
                  System.out.print(munData[l][n]+" - ");
                  System.out.println();
               l++;
         } else{
            l++;
         }     
      }
      }
   } 
