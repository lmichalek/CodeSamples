/************************************************************************
 ***                                                                  ***
 ***               Correct Implementation of Homework 3               ***
 ***                          (lmichale)                              ***
 ***                                                                  ***
 ************************************************************************/

import java.util.*;
public class CorrectListPractice
{
  public static String concatenate(Node front)
  {
    String s = "";
    Node temp = front;
    while(temp!=null)
    {
      s += temp.getData();
      temp = temp.getNext();
    }
    return s;
  }
  
  public static void insertAfter(Node front, String findData, String newData)
  {
    Node temp = front;
    while(temp != null)
    {
      if(temp.getData().equals(findData))
      {
        Node newNode = new Node(newData, temp.getNext());
        temp.setNext(newNode);
        break;
      }
      temp = temp.getNext();
    }
  }
  
  public static Node buildList(ArrayList<String> list)
  {
    if(list.size()==0) return null;
    Node head = new Node(list.get(0), null);
    boolean b = false;
    Node prev = head;
    for(String s : list)
    {
      if(!b)
      {
        b=true;
        continue;
      }
      prev.setNext(new Node(s, null));
      prev = prev.getNext();
    }
    return head;
  }
 
  public static boolean sameList(Node front1, Node front2)
  {
    Node temp1 = front1;
    Node temp2 = front2;
    
    while(temp1 != null && temp2 != null)
    {
      if(!temp1.getData().equals(temp2.getData())) return false;
      
      temp1 = temp1.getNext();
      temp2 = temp2.getNext();
    }
    
    if(temp1==null && temp2==null) return true;
    else return false;
  }
  
  public static Node bringToFront(Node front, int position)
  {
    if(position < 1 || front==null) return front;
    
    Node temp = front.getNext();
    Node prev = front;
    while(position > 1)
    {
      if(temp==null) return front;
      temp = temp.getNext();
      prev = prev.getNext();
      position--;
    }
    if(temp != null)
    {
      prev.setNext(temp.getNext());
      temp.setNext(front);
      return temp;
    }
    else return front;
  }
  
  public static void makeCircular(Node front)
  {
    Node temp = front;
    if(temp==null) return;
    while(temp.getNext() != null)
      temp = temp.getNext();
    temp.setNext(front);
  }
  
  public static Node intersection(Node front1, Node front2)
  {
    boolean foundFirst = false;
    Node head = new Node("", null);
    Node temp = head;
    
    Node temp1 = front1;
    Node temp2 = front2;
    
    while(temp1 != null)
    {
      String s = temp1.getData();
      while(temp2 != null)
      {
        if(temp2.getData().equals(s))
        {
          if(!foundFirst)
          {
            temp.setData(s);
            foundFirst = true;
          }
          else
          {
            temp.setNext(new Node(s, null));
            temp = temp.getNext();
          }
        }
        temp2 = temp2.getNext();
      }
      
      temp2 = front2;
      temp1 = temp1.getNext();
    }
    if(!foundFirst) return null;
    return head;
    
  }
  
  public static Node insertInOrder(Node front, String newData)
  {
    if(front==null) return null;
    if(front.getData().compareTo(newData) > 0)
      return new Node(newData, front);
    
    Node prev = front;
    Node curr = front.getNext();
    while(curr != null)
    {
      if(curr.getData().compareTo(newData) > 0)
      {
        prev.setNext(new Node(newData, curr));
        return front;
      }
      prev = curr;
      curr = curr.getNext();
    }
    prev.setNext(new Node(newData, null));
    return front;
  }
  
  public static Node reverse(Node front)
  {
    if(front==null) return null;
    Node temp = front;
    Node reverseHead = null;
    Node temp2 = front.getNext();
    while(temp2 != null)
    {
      temp.setNext(reverseHead);
      reverseHead = temp;
      temp = temp2;
      temp2 = temp2.getNext();
    }
    temp.setNext(reverseHead);
    return temp;

  }
  
}