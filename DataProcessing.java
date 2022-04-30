// **********************************************************************************
// Title: Major Project Part 2
// Author: Chris Lamb
// Course Section: CMIS202-ONL1 (Seidel) Spring 2022
// File: DataProcessing.java
// Description: This file allows for queues and stacks to be implemented with the Crime Database Tool
// **********************************************************************************

import java.util.Stack;
 
public class DataProcessing {
    //class of queue having two stacks
    static class Queue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;
    }
 
    //Push an item to stack
    static void push(Stack<Integer> top_ref, int new_data)
    {
        //Push the data onto the stack
        top_ref.push(new_data);
    }
 
    //Pop an item from stack
    static int pop(Stack<Integer> top_ref)
    {
        //If the stack is empty then error
        if (top_ref.isEmpty()) {
            System.out.println("Stack Underflow");
            System.exit(0);
        }
 
        //Pop the data from the stack
        return top_ref.pop();
    }
 
    //Enqueue an item to the queue
    static void enQueue(Queue q, int x)
    {
        push(q.stack1, x);
    }
 
    //deQueue an item from queue
    static int deQueue(Queue q)
    {
        int x;
 
        //If both stacks are empty then error
        if (q.stack1.isEmpty() && q.stack2.isEmpty()) {
            System.out.println("Q is empty");
            System.exit(0);
        }
 
        //Move elements from stack1 to stack 2 only if stack2 is empty
        if (q.stack2.isEmpty()) {
            while (!q.stack1.isEmpty()) {
                x = pop(q.stack1);
                push(q.stack2, x);
            }
        }
        x = pop(q.stack2);
        return x;
    }
}