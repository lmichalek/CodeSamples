/************************************************************************
 15-121 Homework #3
 
 @author yues
 
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

 public static String concatenate(Node front){
  String allcontent="";
  if(front==null)     //if the list is empty, return an empty string
   return "";
  else{ 
   while(front!=null){  
    allcontent=allcontent+"  "+front.getData();
    front=front.getNext();
   }
  }  
  return allcontent;
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
 
 public static void insertAfter(Node front, String findData, String newData){
  while(front!=null){                     //iterate every node in the list
   if(front.getData().equals(findData)){
    front.setNext(new Node(newData, front.getNext()));
    break;//insert the data at the first appearance of the findData
   }
   else
    front=front.getNext();
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

 public static Node buildList(ArrayList<String> list){
  if(list.isEmpty()) return null;
  else {
  int length=list.size();
  Node last=new Node(list.get(length-1),null);// make the last node in the list
  Node current=null;
  for(int i=2;i<=length;i++){
   current=new Node(list.get(length-i),last);//make the last node after the current
   last=current;
  }
  return current;
  }
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

 public static boolean sameList(Node front1, Node front2){
  if(front1==null&&front2==null){      //two empty lists are same
   return true;
  }
  else if(front1!=null&front2!=null){// if the two lists are not empty
   while(front1!=null&&front2!=null){// check every data in these two lists
    if(front1.getData()!=front2.getData())// if one pair of these nodes are not the same, return false
     return false;
    else{ front1=front1.getNext();
          front2=front2.getNext();
    }
   }
   if(front1==null&&front2==null)//after comparing all nodes in both list and no false returned, these two lists are the same
    return true;
   else return false;
  }
   return false; //modify this line when you implement this method
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

 public static Node bringToFront(Node front, int position){
  Node temp=front;
  Node lastNode=temp;     //lastNode refers to the node before temp
  int countPosition=0;    //countPosition refers to the position of temp
  if(front!=null&&position!=0){
   while(temp!=null){
    if(countPosition==position){ //when find the node in the right position
     lastNode.setNext(temp.getNext());// put this node the front of the list 
     temp.setNext(front);
     front=temp;
     break;
    }
    else {                       //if did find the node, move on
     lastNode=temp;
     temp=temp.getNext();
     countPosition++;
    }
   }
  }
  return front; // when the list is empty,just return null; when the position is 0, just return the original front node.
 }
 
 
 

 /**
  * 
  * makeCircular -- makes the last node in the list point to the first node. 
  * Thus creating a "circular" list.  It does nothing if the list is empty.
  * 
  * @param front (Node) the beginning of the list.
  * 
  */

 public static void makeCircular(Node front){
  Node temp=front;
  if(front!=null){
   while(temp.getNext()!=null){// make temp point to the last node in the list 
    temp=temp.getNext();
   }
   temp.setNext(front);// make the last node link to the first node and make the list circular
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
 
 
 public static Node intersection(Node front1, Node front2){
  Node newfront=new Node("",null);            //make a new node to be the front of the new list.
  Node current=newfront;
   if(front1!=null&&front2!=null){                     //when these two list are not null
   while(front1!=null){
    Node temp=front2;
    while(temp!=null){
     if(front1.getData().equals(temp.getData())){// when finding the same data,
      Node newNode=new Node(temp.getData(),null);// make a new node
      current.setNext(newNode);//put the new Node right after the current node
      current=newNode;//make the new node current node
      break;   //since there are no duplicated values in both lists, just jump out of the loop when finding a same value
     }
     else temp=temp.getNext();
    }
    front1=front1.getNext();
   }
   return newfront.getNext();  //the first node of the list is made up by the author, so just return the second node as the front of the list
  }
   return null;
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

 public static Node removeAll(Node front, int length){
  while(front!=null&&(front.getData().length()==length)){// change the front node if it needs to be removed
   front=front.getNext();
  }
  if(front!=null){
  Node temp=front;
  Node lastNode=front;//lastNode refers to the node before temp
  while(temp!=null){
   if(temp.getData().length()==length){//when finding the right data, remove it.
    lastNode.setNext(temp.getNext());
    lastNode=temp;
    temp=temp.getNext();
   }
   else{                              //otherwise, just move on to the next node until temp reaches the end of the list
    lastNode=temp;
    temp=temp.getNext();
   }
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
 
 public static Node insertInOrder(Node front, String newData){
  if(front==null)                   //when the list is empty, just add the data into the list.
   front=new Node(newData,null);
  else if (front.getData().toLowerCase().compareTo(newData.toLowerCase())>=1){//if the first data is alphabetically bigger than the new data
   Node newfront=new Node(newData,front);// make the new node the front of the list
   front=newfront;
   }
  else{             //if the first data is alphabetically smaller than the new data
   Node temp=front;
   Node lastNode=front;
   while(temp!=null){//check every data in the list until we find a position that we can put the new data in. 
   if(lastNode.getData().toLowerCase().compareTo(newData.toLowerCase())<=1){//the position should be: the node before the new data is alphabetically bigger than it
    if(temp.getData().toLowerCase().compareTo(newData.toLowerCase())>=1){//the node after the new data is alphabetically smaller than it
    lastNode.setNext(new Node(newData,temp));//put the new node in this position
    break;//task finished, jump out of the loop
   }
    else{//if did find the right position, move on until temp reach the end.
     lastNode=temp;
     temp=temp.getNext();
    }
    }
   //else front=new Node(newData, front);
  }
  if(temp==null)// when moving to the end of the list
   lastNode.setNext(new Node(newData,null));//add the new node to the end of the list
  }
  return front;
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

 public static Node reverse(Node front){
  Node temp = front;
  Node newHead = null; 
  while (front != null) {// when front doesn't reach the end of the list
  front = front.getNext();//Link the list backward
  temp.setNext(newHead);
  newHead = temp;
  temp=front;
  }
  return newHead;
  }

   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 /*************************************************
  *
  
  USE THE MAIN METHOD TO BUILD THE LIST 
  AND TEST EACH THE METHODS DESCRIBED ABOVE

 *
 *************************************************/
 
 public static void main (String[] args)
 {
  
  Node front1=new Node("a",new Node("b", new Node("C", new Node("D",new Node("f",new Node("d",new Node("g",null)))))));
  Node front2=new Node("a",null);
  Node front3=null;
  Node front4=new Node("s",new Node("e", new Node("d", new Node("G",new Node("f",new Node("t",new Node("k",null)))))));
  Node front5=new Node("a",new Node("b", new Node("C", new Node("D",new Node("f",new Node("f",new Node("g",null)))))));
  Node front6=null;
  Node front7=new Node("a",new Node("b", new Node("C", new Node("D",new Node("f",null)))));
  Node front8=new Node("aegw",new Node("bw", new Node("Cfff", new Node("Dffff",new Node("fwer",new Node("fx",new Node("st",null)))))));
  
  
  
  
  /*
  //test insertAfter method
  System.out.println("The first list is:"+concatenate(front1));
  insertAfter(front1,"f","card");             //insert a data after the existing node
  System.out.println(concatenate(front1));
  insertAfter(front1,"no","c");             //when findData does not exist
  System.out.println(concatenate(front1));
  insertAfter(front1,"g","lalala");         //when inserting at the end of the list
  System.out.println(concatenate(front1));
  insertAfter(front1,"card","lalala");
  System.out.println(concatenate(front1));  
  
  System.out.println("The second List is:"+concatenate(front2));
  insertAfter(front2,"a","c");          //insert a data into a one-Node list
  System.out.println(concatenate(front2));
  
  System.out.println("The third list is:"+concatenate(front3));
  insertAfter(front3,"a","c");          //insert a data into a empty list
  System.out.println("The third list is:"+concatenate(front3));
  */
  
  
  /*
  //test the buildList method
  ArrayList<String> test1=new ArrayList();//create the an arrylist
  for(int i=0;i<10;i++){
   test1.add("i");
  }
  System.out.println("The list is:"+concatenate(buildList(test1)));
  
  ArrayList<String> test2=new ArrayList();// when the array list is empty
  System.out.println("The list is:"+concatenate(buildList(test2)));
  */
  
  
  
  /*
  //test the sameList method
  System.out.println(sameList(front1,front5));//compare two lists that are same
  System.out.println(sameList(front1,front3));//compare a non-empty list with an empty list
  System.out.println(sameList(front3,front6));//compare two empty lists
  System.out.println(sameList(front1,front7));//compare two lists that are partially identical
  */
  
  
  /*
  //test the bringToFront method
  System.out.println(concatenate(bringToFront(front1,2)));//bring the third node to the front
  System.out.println(concatenate(bringToFront(front2,5)));//bring a non-exist node to the front
  System.out.println(concatenate(bringToFront(front2,0)));//bring the first node to the front
  System.out.println(concatenate(bringToFront(front3,11)));//manipulate an empty list
  */
  
  
  /*
  //test the makeCircular method
  makeCircular(front1);
  System.out.println(concatenate(front1));
  */
  
  
  
  //test the intersection method
  //System.out.println(concatenate(intersection(front1,front4)));//two lists that are partially identical
  //System.out.println(concatenate(intersection(front1,front2)));//front1 contains front 2
  //System.out.println(concatenate(intersection(front1,front3)));//front3 is empty
  
  //test the removeAll method
  //System.out.println(concatenate(removeAll(front8,4)));// remove a normal node 
  //System.out.println(concatenate(removeAll(front2,1)));// remove the only node in the list
     //System.out.println(concatenate(removeAll(front6,4)));// remove from an empty list
  //System.out.println(concatenate(removeAll(front1,11)));// remove an non-exist node
  
  
  //test the insertInOrder method
  //System.out.println(concatenate(insertInOrder(front6,"yy")));// insert in an empty list
  //System.out.println(concatenate(insertInOrder(front8,"hi")));// insert in a normal list
  //System.out.println(concatenate(insertInOrder(front2,"zzz")));//insert at the end of the list
  //System.out.println(concatenate(insertInOrder(front8,"aa")));//insert in the first place of the list
  
  //test the reverse method
  //System.out.println(concatenate(reverse(front8)));//reverse a normal list
  //System.out.println(concatenate(reverse(front3)));//reverse an empty list
  //System.out.println(concatenate(reverse(front2)));//reverse a list with one node
  
 }//end of main()
 
}//end of class ListPractice