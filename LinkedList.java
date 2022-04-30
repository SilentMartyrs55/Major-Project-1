// **********************************************************************************
// Title: Major Project Part 2
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: LinkedList.java
// Description: This file allows for the creation and processing of a linked list
// **********************************************************************************

import java.io.*;

public class LinkedList {
 
    Node head;
    
    //Linked list Node.
    static class Node {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }
  
    //Insert a new node
    public static LinkedList insert(LinkedList list,int data)
    {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.next = null;
 
        // If the Linked List is empty, then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            // Else traverse till the last node and insert the new node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            // Insert the new node at last node
            last.next = new_node;
        }
         return list;
    }
    
    //Print the LinkedList.
    public static void printList(LinkedList list)
    {
        Node currNode = list.head;
        System.out.print("\nLinkedList: ");
 
        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + " ");
 
            // Go to next node
            currNode = currNode.next;
        }
        System.out.println("\n");
    }
  
    //Delete a node in the LinkedList by KEY
    public static LinkedList deleteByKey(LinkedList list,
                                         int key)
    {
        //Store head node
        Node currNode = list.head, prev = null;
        
        //If the head node itself holds the key to be deleted
        if (currNode != null && currNode.data == key) {
            list.head = currNode.next; 
             System.out.println(key + " found and deleted");
             return list;
        }

        // If the key is somewhere else, search for the key to be deleted
        while (currNode != null && currNode.data != key) {
            // If currNode does not hold the key, continue to next node
            prev = currNode;
            currNode = currNode.next;
        }
        // If the key was present, it should be at currNode
        if (currNode != null) {
            prev.next = currNode.next;
            System.out.println(key + " found and deleted");
        }
        
        // If key was not present in linked list
        if (currNode == null) {
            System.out.println(key + " not found");
        }
         return list;
    }
    
    //Delete a node in the LinkedList by POSITION
    public static LinkedList
    deleteAtPosition(LinkedList list, int index)
    {
        // Store head node
        Node currNode = list.head, prev = null;

        // If index is 0, then head node itself is to be deleted
        if (index == 0 && currNode != null) {
            list.head = currNode.next; // Changed head
 
            System.out.println(
                index + " position element deleted");
 
             return list;
        }
        
        int counter = 0;
        while (currNode != null) {
 
            if (counter == index) {
                prev.next = currNode.next;
                System.out.println(
                    index + " position element deleted");
                break;
            }
            else {
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }
 
        if (currNode == null) {
            System.out.println(
                index + " position element not found");
        }
         return list;
    }}