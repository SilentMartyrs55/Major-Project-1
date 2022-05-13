// **********************************************************************************
// Title: Major Project Part 4
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: LinkedList.java
// Description: This file allows for the creation and processing of a linked list
// **********************************************************************************

import java.io.*;

 class Node
{
    String data;
    Node next;
    Node(String d)
    {
        data = d;
        next = null;
    }
}
 
class LinkedList
{
    Node head;
 
    //Insert a new node in the front
    public void push(String new_data)
    {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }
    
    //Load the full excel table
    static Node load(String[][] excelTable, int i, int j, int m, int n, Node[][] visited)
    {
         
       //return if i or j is out of bounds
        if (i > m - 1 || j > n - 1)
            return null;
      
       //Check if node is previously created
      if(visited[i][j] != null){
          return visited[i][j];
      }
 
         //create a new node for current i and j
        Node temp = new Node();
        visited[i][j] = temp;
        temp.data = excelTable[i][j];
        temp.next = construct(excelTable, i, j + 1, m, n, visited);
        temp.down = construct(excelTable, i + 1, j, m, n, visited);
        return temp;
        
     }
     
    //Try to find the user defined string in the linked list
    public boolean search(Node head, String x)
    {
        Node current = head;    
        while (current != null)
        {
            if (current.data == x)
                return true;    
            current = current.next;
        }
        return false;    
    }
}
