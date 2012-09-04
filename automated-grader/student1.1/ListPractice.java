/************************************************************************
  15-121 Homework #3
  
  @author asoto1
  
  ***************************************************************************/

import java.util.*;

public class ListPractice {
  
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
    * 
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
  
  // Loop through the entire linked list, and keep adding to the string
  public static String concatenate(Node front) {
    String result = "";
    
    while(front != null) {
      result += front.getData();
      front = front.getNext();
    }
    return result;
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
  
  public static void insertAfter(Node front, String findData, String newData) {
    
    // Loop through the entire list, if it is empty, or if the data
    // is not found, it will do nothing
    while(front != null) {
      
      if(front.getData().equals(findData)) {
        front.setNext(new Node(newData, front.getNext()));
        return;
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
  
  public static Node buildList(ArrayList<String> list) {
    Node head = null;
    
    for(int i = 0; i < list.size(); i++) {
      head = new Node(list.get(i), head);
    }
    
    return head;
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
  
  public static boolean sameList(Node front1, Node front2) {
    
    // Go until neither of the fronts are null
    while( (front1 != null) && (front2 != null)) {
      
      // If the data is equal, continue looping. Otherwise, non-equal list!
      if(front1.getData().equals(front2.getData())) {
        front1 = front1.getNext();
        front2 = front2.getNext();
      }
      else {
        return false;
      }
    }
    
    // If lists are equal, both fronts should be null as well! This takes care of the case of
    // comparing an empty list to a non-empty list
    return (front1 == front2); 
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
  
  public static Node bringToFront(Node front, int position) {
    Node prev = front;
    Node curr = front;
    
    // List is empty or invalid position, return
    if ( (curr == null) || (position <= 0) ) {
      return front;
    }
    
    // Go through the linked list and find the node requested, and it's previous
    // If we have an invalid position do nothing
    for( int i = 0; i < position; i++ ) {
      prev = curr;
      curr = curr.getNext();
      
      if(curr == null) {
        return front; 
      }
    }
    
    // Previous node gets current node's next
    prev.setNext(curr.getNext());
    
    // The current node will be the new front, so it's next is the curent front
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
  
  public static void makeCircular(Node front) {
    Node iterator = front;
    
    // Empty list! Do nothing
    if(iterator == null) {
      return;
    }
    
    // Loop through until you get the last node
    while( iterator.getNext() != null ) {
      iterator = iterator.getNext(); 
    }
    
    // Last node points to front
    iterator.setNext(front);
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
  
  public static Node intersection(Node front1, Node front2) {
    Node newList = null;
    Node iterator1 = front1;
    Node iterator2 = front2;
    
    // Loop through every possible combination of pairs
    while(iterator1 != null) {
      String stringToMatch = iterator1.getData();
      
      while(iterator2 != null) {
        
        // If they contain the same strings, add to the new list
        if(stringToMatch.equals(iterator2.getData())) {
          newList = new Node(stringToMatch, newList);
        }
        iterator2 = iterator2.getNext();
      }
      
      iterator2 = front2;
      iterator1 = iterator1.getNext();
    }
    
    return newList;
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
  
  public static Node removeAll(Node front, int length) {
    
    // Empty list, do nothing
    if(front == null) {
      return front;
    }
    
    Node curr = front;
    Node prev = front;
    
    // Go through until we hit the last node
    while( curr != null ) {
      
      // The string is exactly the length we want to remove
      if(curr.getData().length() == length) {
        
        // The previous node will skip over this one, and 
        // that will remove it from the linked list
        prev.setNext(curr.getNext());
        
        // If we're trying to remove the first node, do so by
        // setting the front pointer to the second element.
        if (curr == front) {
          front = curr.getNext();
        }
      }
      
      // We only want to change the previous node if we don't remove the current one.
      else { 
        prev = curr;
      }
      
      curr = curr.getNext();
    }
    
    // Return the updated head of the list
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
  
  // 3 cases, insert at start, between two nodes, or at the end.
  public static Node insertInOrder(Node front, String newData) {
    Node curr = front;
    Node newNode = new Node(newData, null);
    
    // If we must insert it to the start of the list, just do it now
    // It is either empty or it should be first
    if( (curr == null) || (curr.getData().compareTo(newData) >= 0) ) {
      newNode.setNext(curr);
      return newNode;
    }
    
    // Otherwise, we have to insert the node at some later point in the list
    while( curr.getNext() != null ) {
      String currString = curr.getData();
      String nextString = curr.getNext().getData();
      
      // Insert after the current node, but before the next node
      if( (currString.compareTo(newData) <= 0) && (nextString.compareTo(newData) >= 0) ) {
        newNode.setNext(curr.getNext());
        curr.setNext(newNode);
        return front;
      }
      
      curr = curr.getNext();
    }
    
    // Finally, it must be at the end of the list, curr.getNext() contains the last node!
    curr.setNext(newNode);
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
  
  public static Node reverse(Node front) {
    Node curr = front;
    Node prev = null;
    Node next = null;
    
    // Do nothing on an empty list
    if(curr == null) {
      return front;
    }
    
    // For the entire list, the current node's next is the PREVIOUS node
    while(curr != null) {
      next = curr.getNext();
      curr.setNext(prev);
      prev = curr;
      curr = next;
    }
    
    return prev; 
  }
  
  /*************************************************
    *
    * 
    USE THE MAIN METHOD TO BUILD THE LIST 
    AND TEST EACH THE METHODS DESCRIBED ABOVE
    
    *
    *************************************************/
  
  public static void main (String[] args) {
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> singleArray = new ArrayList<String>();
    ArrayList<String> emptyArray = new ArrayList<String>();
    
    // Create the list we will be manipulating
    names.add("Alex");
    names.add("John");
    names.add("Shay");
    names.add("Will");
    names.add("Julian");
    
    // Create the list with one element
    singleArray.add("Uno");
    
    // Create the linked lists
    Node namesHead = buildList(names);
    Node singleHead = buildList(singleArray);
    Node emptyHead = buildList(emptyArray);
    
    // Make another list to test intersection
    names.remove("Will");
    names.remove("Julian");
    names.add("Bob");
    names.add("Tony");
    names.remove("John");
    names.add("Julian");
    names.add("Will");
    names.remove("Shay");
    
    Node names2Head = buildList(names);
    
    // Prints to make sure that the arrays and linked lists match, initially
    /*   System.out.println("Name array contains: " + names);
     System.out.println("Single array contains: " + singleArray);
     System.out.println("Empty array contains: " + emptyArray);
     print(namesHead);
     print(singleHead);
     print(emptyHead); */
    
    // Test the concatenation
    /*   System.out.println("Names: " + concatenate(namesHead));
     System.out.println("Single: " + concatenate(singleHead));
     System.out.println("Empty: " + concatenate(emptyHead)); */
    
    // Test insertAfter
    /*   print(namesHead);
     insertAfter(namesHead, "Shay", "Bob");
     print(namesHead);
     
     print(singleHead);
     insertAfter(singleHead, "Uno", "Dos");
     print(singleHead);
     
     print(emptyHead);
     insertAfter(emptyHead, "", "Shouldn't appear!");
     print(emptyHead); */
    
    // Test sameList
    /*   Node singleHead2 = buildList(singleArray);
     System.out.println("Should be true: " + sameList(namesHead, namesHead));
     System.out.println("Should be true: " + sameList(emptyHead, emptyHead));
     System.out.println("Should be true: " + sameList(singleHead, singleHead2));
     System.out.println("Should be false: " + sameList(emptyHead, singleHead));
     System.out.println("Should be false: " + sameList(namesHead, singleHead)); */
    
    // Test bringToFront
    /*   print(namesHead);
     namesHead = bringToFront(namesHead, 0);
     print(namesHead);
     namesHead = bringToFront(namesHead, 3);
     print(namesHead);
     namesHead = bringToFront(namesHead, 5);
     print(namesHead); 
     
     print(emptyHead);
     emptyHead = bringToFront(emptyHead, 1);
     print(emptyHead); 
     
     print(singleHead);
     singleHead = bringToFront(singleHead, 0);
     print(singleHead); */
    
    // Test makeCircular (note, should print forever)
    /*   print(emptyHead);
     makeCircular(emptyHead);
     print(emptyHead);
     
     print(namesHead);
     makeCircular(namesHead);
     print(namesHead); */
    
    // Test intersection
    /*    print(namesHead);
     print(names2Head);
     print(intersection(namesHead, names2Head));
     print(intersection(emptyHead, namesHead));
     print(intersection(emptyHead, singleHead));
     print(intersection(emptyHead, emptyHead)); */
    
    // Test removeALL
    // Make another list to test removeALL
    /*   ArrayList<String> numbers = new ArrayList<String>();
     * 
     numbers.add("1");
     numbers.add("22");
     numbers.add("1");
     numbers.add("22");
     numbers.add("4444");
     numbers.add("333");
     numbers.add("22");
     numbers.add("55555");
     numbers.add("55555");
     
     Node numbersHead = buildList(numbers);
     
     print(numbersHead);
     numbersHead = removeAll(numbersHead, 5);
     print(numbersHead);
     numbersHead = removeAll(numbersHead, 1);
     print(numbersHead);
     numbersHead = removeAll(numbersHead, 2);
     numbersHead = removeAll(numbersHead, 4);
     numbersHead = removeAll(numbersHead, 3);
     
     System.out.println("Should be empty next!");
     print(numbersHead);
     numbersHead = removeAll(numbersHead, 3);
     
     System.out.println("Should be empty again!");
     print(numbersHead); */
    
    // Test insertInOrder
    /*   Node orderedList = null;
     * 
     orderedList = insertInOrder(orderedList, "Y");
     orderedList = insertInOrder(orderedList, "Y");
     orderedList = insertInOrder(orderedList, "G");
     orderedList = insertInOrder(orderedList, "Y");
     orderedList = insertInOrder(orderedList, "G");
     print(orderedList);
     
     orderedList = insertInOrder(orderedList, "X");
     print(orderedList);
     
     orderedList = insertInOrder(orderedList, "B");
     orderedList = insertInOrder(orderedList, "A");
     print(orderedList);
     
     orderedList = insertInOrder(orderedList, "Z");
     orderedList = insertInOrder(orderedList, "Y");
     orderedList = insertInOrder(orderedList, "AB");
     orderedList = insertInOrder(orderedList, "ZZ");
     orderedList = insertInOrder(orderedList, "A");
     orderedList = insertInOrder(orderedList, "C");
     orderedList = insertInOrder(orderedList, "F");
     orderedList = insertInOrder(orderedList, "Z");
     orderedList = insertInOrder(orderedList, "G");
     orderedList = insertInOrder(orderedList, "C");
     
     orderedList = insertInOrder(orderedList, "F");
     print(orderedList); */
    
    // Test reverse
    /*   print(namesHead);
    namesHead = reverse(namesHead);
    print(namesHead);
    
    print(singleHead);
    singleHead = reverse(singleHead);
    print(singleHead);
    
    print(emptyHead);
    emptyHead = reverse(emptyHead);
    print(emptyHead);
    
    print(names2Head);
    names2Head = reverse(names2Head);
    print(names2Head); */
    
  } //end of main()
  
} //end of class ListPractice