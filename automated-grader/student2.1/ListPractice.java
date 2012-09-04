/************************************************************************
 15-121 Homework #3
 
 @author rrowe
 
***************************************************************************/

import java.util.*;

public class ListPractice{
 
 
 public static void print(Node front){
  if(front == null){
   System.out.println("List is empty."); 
  }
  else{
   Node current = front;
   System.out.println("The current list is:"); 
   while(current != null){
    System.out.println(current.getData() + " "); 
    current = current.getNext();
   }
  }
 }
  
 /*************************************************
  
     IMPLEMENT THE FOLLOWING METHODS
  
  
  *************************************************/

 /**
  * 
  * concatenate -- creates one string containing all the strings in the list.
  * 
  * @param front (Node) the beginning of the list.
  * 
  * @return a single String, with proper format, containing all the values 
  * of all the strings in the list.
  */

 public static String concatenate(Node front)
 {
   if (front == null)
   {
     return "";
   }
   String i = front.getData();
   while(front.getNext() != null)
   {
     i += (front.getNext().getData());
     front = front.getNext();
   }
  return i; //modify this line when you implement this method
 }
 
 /**
  * 
  * insertAfter -- inserts a new node after a given string.
  * It doesn't insert a new node if the string is not present in the current list or
  * if the list is empty. It is OK to insert duplicate values, as long as the newData
  * is inserted after the first occurrence of findData. No changes if findData does not 
  * appear in the list.
  * 
  * @param front (Node) the beginning of the list.
  * @param findData (String) value to be used to determine the place on insertion.
  * @param newData (String) value of the string to be inserted.
  */
 
 public static void insertAfter(Node front, String findData, String newData)
 {
   while (front != null)
  {
    if (front.getData() == findData)
    {
      if (front.getNext() != null)
      {
        front.setNext(new Node(newData, front.getNext()));
        break;
      }
      front.setNext(new Node(newData, null));
      break;
    }
    front = front.getNext();
  }
 }
  
 
 /**
  * 
  * buildArrayList -- builds a new list of strings given an ArrayList of strings.
  * 
  * @param list (ArrayList of Strings) a list of the strings.
  * 
  * @return a Node pointing to the beginning of the resulting list.
  */

 public static Node buildList(ArrayList<String> list)
 {
   if (list.size() > 0)
   {
     Node front = new Node(list.get(0), null);
     Node temp = front;
     for (int i = 1; i < list.size(); i++)
     {
       temp.setNext(new Node(list.get(i), null));
       temp = temp.getNext();
     }
     return front;
   }
   return null;
 }
 
 
 
 /**
  * 
  * sameList -- compares two given lists for equality. The two lists are considered
  * identical if they have the same number of strings, with the same values, and in the
  * same order. 
  * 
  * @param front1 (Node) the beginning of one list.
  * @param front2 (Node) the beginning of other list.
  * 
  * @return true if the two list are identical, false otherwise.
  */

 public static boolean sameList(Node front1, Node front2)
 {
   while (front1 != null || front2 != null)
   {
     if (front1 == null && front2 != null)
     {
       return false;
     }
     if (front1.getData().equals(front2.getData()))
     {
       front1 = front1.getNext();
       front2 = front2.getNext();
     }
     else
     {
       return false;
     }
   }
   return true;
 }
 
 
 
 /**
  * 
  * bringToFront -- given a position in the list, it moves the node at that
  * position to the front of the list.  The rest of the other nodes stay in the 
  * same position. It does nothing if the list is empty or the position is invalid.
  * 
  * @param front (Node) the beginning of the list.
  * @param position (int) the node to be moved to the front of the list.
  * 
  * @return a Node pointing to the beginning of the resulting list.
  */

 public static Node bringToFront(Node front, int position)
 {
   Node prev = null;
   Node curr = front;
   int i = 0;
   if (position == 0)
   {
     return front;
   }
   while (i < position)
   {
     prev = curr;
     curr = curr.getNext();
     i++;
   }
   prev.setNext(curr.getNext());
   curr.setNext(front);
   front = curr;
   return front;
 }
 
 
 

 /**
  * 
  * makeCircular -- makes the last node in the list point to the first node. 
  * Thus creating a "circular" list.  It does nothing if the list is empty.
  * 
  * @param front (Node) the beginning of the list.
  * 
  */

 public static void makeCircular(Node front)
 {
   if (front != null)
   {
     Node last = front;
     while (last.getNext() != null)
     {
       last = last.getNext();
     }
     last.setNext(front);
   }
 }
 
 
 
 /**
  * 
  * intersection -- creates a new list with the strings present in both lists.
  * It doesn't modify the original lists.
  * 
  * @param front1 (Node) the beginning of a list
  * @param front2 (Node) the beginning of another list
  * 
  * @return a Node pointing to the beginning of the resulting list.
  */
 
 public static Node intersection(Node front1, Node front2)
 {
   Node intersections = null;
   Node prev = null;
   while (front1 != null)
   {
     Node temp = front2;
     while (temp != null)
     {
       if (front1.getData() == temp.getData())
       {
         Node curr = new Node(front1.getData(), prev);
         prev = curr;
       }
       temp = temp.getNext();
     }
     front1 = front1.getNext();
   }
   intersections = prev;
   return intersections;
 }
 
 
 
 /**
  * 
  * removeAll -- deletes all the strings which have as many characters as
  * specified by. It cannot create any new nodes!
  * 
  * @param front (Node) the beginning of the list.
  * @param length (int) the number of characters.
  * 
  * @return a Node pointing to the beginning of the resulting list.
  */

 public static Node removeAll(Node front, int length)
 {
   while (front.getData().length() == length)
   {
     if (front.getNext() != null)
     {
       front = front.getNext();
     }
     else
       return null;
   }
   Node curr = front;
   Node prev = null;
   while (curr != null)
   {
     if (curr.getData().length() == length)
     {
       prev.setNext(curr.getNext());
       prev = curr;
       curr = curr.getNext();
       
     }
     else
     {
       prev = curr;
       curr = curr.getNext();
     }
   }
  return front;
 }
   
 
 
 

 
 
 /**
  * 
  * insertInOrder -- inserts a new city (string) in alphabetical order (A..Z)
  * into an existing list which is already in order (alphabetized).
  * 
  * @param front (Node) the beginning of the current list.
  * @param newData (String) value of the string to be inserted.
  * 
  * @return a Node pointing to the beginning of the resulting list.
  */
 
 public static Node insertInOrder(Node front, String newData)
 {
   if (front == null)
   {
     front = new Node(newData, null);
     return front;
   }
   Node curr = front;
   Node prev = null;
   if (newData.compareTo(curr.getData()) <= 0)
   {
     front = new Node(newData, front);
     return front;
   }
   while (curr != null)
   {
     if (newData.compareTo(curr.getData()) <= 0)
     {
       Node temp = new Node(newData, curr);
       prev.setNext(temp);
       return front;
     }
     else if (newData.compareTo(curr.getData()) > 0)
     {
       prev = curr;
       curr = curr.getNext();
     }
   }
   if (curr == null)
   {
     prev.setNext(new Node(newData, null));
   }
  return front; //modify this line when you implement this method
 }
 
 
 
 
 

 
 
 /**
  * 
  * reverse -- reverses the existing list.  The first node becomes the last one,
  * the second node becomes the next to the last one, ..., the last node becomes 
  * the first one. It does nothing if the list is empty. It cannot create new nodes,
  * and it cannot call setData().
  * 
  * @param front (Node) the beginning of the list.
  * 
  * @return a Node pointing to the beginning of the resulting list.
  */

 public static Node reverse(Node front)
 {
   Node curr = front;
   Node prev = null;
   Node temp = null;
   while (curr != null)
   {
     temp = curr.getNext();
     curr.setNext(prev);
     prev = curr;
     curr = temp;
   }
   front = prev;
   return front; 
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 /*************************************************
  *
  
  USE THE MAIN METHOD TO BUILD THE LIST 
  AND TEST EACH THE METHODS DESCRIBED ABOVE

 *
 *************************************************/
 
 public static void main(String[] args)
 {
   ListPractice lists = new ListPractice();
   Node front1 = new Node("A", null);
   Node front2 = null;
   Node front3 = new Node("Atlanta", new Node("Cleveland", new Node("Denver", null)));
   Node front4 = new Node("A", new Node("B", new Node("C", null)));
   ArrayList<String> list = new ArrayList<String>();
   list.add("A");
   list.add("B");
   list.add("C");
   //print(bringToFront(front4, 1));
   //System.out.println(concatenate(front1));
   //insertAfter(front1, "A", "D");
   //System.out.println(sameList(front2, front4));
   //makeCircular(front3);
   //System.out.println(buildList(list).getData());
   //print(intersection(front3, front4));
   //print(removeAll(front4, 1));
   //print(reverse(front3));
   //print(insertInOrder(front3, "Huston"));
   
   
 
     
  //create the lists here
  //thoroughly test each of the methods described above
  
 }//end of main()
 
}//end of class ListPractice