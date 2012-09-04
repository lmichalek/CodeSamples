/************************************************************************
 ***                                                                  ***
 ***                Homework 3 - ListPractice Tester                  ***
 ***                          (lmichale)                              ***
 ***                                                                  ***
 ************************************************************************/

import java.util.*;
public class HW3Tester
{
  public static void main(String[] args)
  {
    System.out.println("\n****************************************************");
    System.out.println(  "*****              TESTING HW 3                *****");
    System.out.println(  "****************************************************\n");
    
    System.out.println("PROBLEM 1 - CONCATENATE");
    testConcatenate();
    System.out.println("\nPROBLEM 2 - INSERT AFTER");
    testInsertAfter();
    System.out.println("\nPROBLEM 3 - BUILD LIST");
    testBuildList();
    System.out.println("\nPROBLEM 4 - SAME LIST");
    testSameList();
    System.out.println("\nPROBLEM 5 - BRING TO FRONT");
    testBringToFront();
    System.out.println("\nPROBLEM 6 - MAKE CIRCULAR");
    testMakeCircular();
    System.out.println("\nPROBLEM 7 - INTERSECTION");
    testIntersection();
    System.out.println("\nPROBLEM 8 - REMOVE ALL");
    testRemoveAll();
    System.out.println("\nPROBLEM 9 - INSERT IN ORDER");
    testInsertInOrder();
    System.out.println("\nPROBLEM 10 - REVERSE");
    testReverse();
    
    System.out.println("\n****************************************************");
    System.out.println(  "*****              DONE TESTING                *****");
    System.out.println(  "****************************************************\n");
  }
  
  
  
  
  
  //------------------------------QUESTION #1 - CONCATENATE----------------------------//
  
  public static void testConcatenate()
  {
    int calls = Node.constructCount;
    try
    {
      String s = ListPractice.concatenate(null);
      if(!s.equals(""))
        System.out.println("  concatenate: FAILS for the empty list");
      else
        System.out.println("  concatenate: CORRECT for the empty list");
    }
    catch(Exception e)
    {
      System.out.println("  concatenate: CRASHES for the empty list");
    }
          boolean works = true;
    try
    {
      boolean changedList = false;
      Node abc = generateAbcList();
      if(!ListPractice.concatenate(abc).equals("abc"))
        works = false;
      if(!sameList(abc, generateAbcList())) changedList = true;
      Node a = generateAList();
      if(!ListPractice.concatenate(a).equals("a"))
        works = false;
      if(!sameList(a, generateAList())) changedList = true;
      Node longL = generateLongList();
      if(!ListPractice.concatenate(longL).equals(longListString()))
        works = false;
      if(!sameList(longL, generateLongList())) changedList = true;
      if(works) System.out.println("  concatenate: CORRECT for non-empty list inputs");
      else 
      {
        System.out.println("  concatenate: FAILS for some non-empty list inputs");
        System.out.println("    output should be: abc but it is: "
                             +ListPractice.concatenate(generateAbcList()));
        System.out.println("    output should be: a but it is: "
                             +ListPractice.concatenate(generateAList()));
        System.out.println("    output should be: "+longListString()
                             +" but it is: "+ListPractice.concatenate(generateLongList()));
      }
      if(changedList) System.out.println("  concatenate: FAILS - the original list is modified");
    }
    catch(Exception e)
    {
      System.out.println("  concatenate: CRASHES for some non-empty list inputs");
    }
    if(works)
      calls = Node.constructCount - calls - 108;
    else
      calls = Node.constructCount - calls - 162;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
  }
  
  
  
  
  
  
  
  
  //------------------------------QUESTION #2 - INSERT AFTER----------------------------//
  
  public static void testInsertAfter()
  {
    int calls = Node.constructCount;
    try
    {
      ListPractice.insertAfter(null, "", "");
      System.out.println("  insertAfter: CORRECT for the empty list");
    }
    catch(Exception e)
    {
      System.out.println("  insertAfter: CRASHES for the empty list");
    }
    try
    {
      boolean works = true;
      Node head = generateAbcList();
      ListPractice.insertAfter(head, "d", "x");
      if(!sameList(head, generateAbcList()))
        works = false;
      
      head = generateAbcList();
      CorrectListPractice.insertAfter(head, "a", "a");
      Node correctHead = generateAbcList();
      ListPractice.insertAfter(correctHead, "a", "a");
      if(!sameList(head,correctHead))
        works = false;
      
      head = generateAbcList();
      CorrectListPractice.insertAfter(head, "c", "a");
      correctHead = generateAbcList();
      ListPractice.insertAfter(correctHead, "c", "a");
      if(!sameList(head, correctHead))
        works = false;
      
      head = generateDupList();
      CorrectListPractice.insertAfter(head, "a", "a");
      correctHead = generateDupList();
      ListPractice.insertAfter(correctHead, "a", "a");
      if(!sameList(head, correctHead))
        System.out.println("  insertAfter: FAILS for lists with duplicate values.");
      else System.out.println("  insertAfter: CORRECT for lists with duplicate values.");
      if(works) System.out.println("  insertAfter: CORRECT for non-empty list inputs");
      else System.out.println("  insertAfter: FAILS for some non-empty list inputs");
    }
    catch(Exception e)
    {
      System.out.println("  insertAfter: CRASHES for some non-empty list inputs");
    }
    calls = Node.constructCount - calls - 32;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
  }
  
  
  
  
  
  
  
  
  //------------------------------QUESTION #3 - BUILD LIST----------------------------//
  
  public static void testBuildList()
  {
    int calls = Node.constructCount;
    try
    {
      Node x = ListPractice.buildList(new ArrayList<String>());
      if(x!=null)
        System.out.println("  buildList: FAILS for the empty list");
      else
        System.out.println("  buildList: CORRECT for the empty list");
    }
    catch(Exception e)
    {
      System.out.println("  buildList: CRASHES for the empty list");
    }
    try
    {
      boolean works = true;
      boolean listUnchanged = true;
      
      ArrayList<String> list = new ArrayList<String>();
      list.add("a");
      ArrayList<String> listCompare = new ArrayList<String>();
      listCompare.add("a");
      
      if(!sameList(ListPractice.buildList(list), CorrectListPractice.buildList(listCompare)))
        works = false;
      if(!list.equals(listCompare))
        listUnchanged = false;
      
      list = new ArrayList<String>();
      list.add("a"); list.add("b"); list.add("b"); list.add("c");
      listCompare.add("b"); listCompare.add("b"); listCompare.add("c");
    
      if(!sameList(ListPractice.buildList(list), CorrectListPractice.buildList(listCompare)))
        works = false;
      if(!list.equals(listCompare))
        listUnchanged = false;
      
      if(works) System.out.println("  buildList: CORRECT output for non-empty inputs");
      else System.out.println("  buildList: FAILS for some non-empy list inputs");
      if(!listUnchanged) System.out.println("  buildList: FAILS - the original ArrayList is modified");
      
      list = new ArrayList<String>();
      list.add("a"); list.add("b"); list.add("a"); list.add("c");
      listCompare = new ArrayList<String>();
      listCompare.add("a"); listCompare.add("b"); listCompare.add("a"); listCompare.add("c");
      
      if(!sameList(ListPractice.buildList(list), CorrectListPractice.buildList(listCompare)))
        System.out.println("  buildList: FAILS on Dave's insertAfter call test: the list a b a c");
      
    }
    catch(Exception e)
    {
      System.out.println("  buildList: CRASHES for some non-empty list inputs");
    }
    calls = Node.constructCount - calls - 17;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
  }
  
  
  
  
  
  
  
  
  //------------------------------QUESTION #4 - SAME LIST----------------------------//
  
  public static void testSameList()
  {
    int calls = Node.constructCount;
    try
    {
      if(ListPractice.sameList(null,null))
        System.out.println("  sameList: CORRECT when comparing two empty lists");
      else
        System.out.println("  sameList: FAILS when comparing two empty lists");
    }
    catch(Exception e)
    {
      System.out.println("  sameList: CRASHES when comparing empty lists");
    }
    
    try
    {
      boolean works = true;
      if(ListPractice.sameList(null,new Node("a", null)))
        works = false;
      if(ListPractice.sameList(new Node("b", null), null))
        works = false;
      if(works) System.out.println("  sameList: CORRECT when comparing an empty list to a non-empty list");
      else System.out.println("  sameList: FAILS when comparing an empty list to a non-empty list");
    }
    catch(Exception e)
    {
      System.out.println("  sameList: CRASHES when comparing an empty list to a non-empty list");
    }
    try
    {
      boolean works = true;
      boolean listUnchanged = true;
     
      Node list = generateAList();
      if(!ListPractice.sameList(list, list))
        works = false;
      if(!sameList(list, generateAList())) listUnchanged = false;
      
      list = generateAList();
      Node list2 = generateAbcList();
      if(ListPractice.sameList(list, list2) || ListPractice.sameList(list2, list))
        works = false;
      if(!sameList(list, generateAList()) || !sameList(list2, generateAbcList())) listUnchanged = false;
      
      list = generateAbcList();
      list2 = generateAbcList();
      list2.setData("c");
      if(ListPractice.sameList(list, list2) || ListPractice.sameList(list2, list))
        works = false;
      Node n = generateAbcList(); n.setData("c");
      if(!sameList(list, generateAbcList()) || !sameList(list2, n)) listUnchanged = false;
      
      if(works) System.out.println("  sameList: CORRECT for non-empty list inputs");
      else System.out.println("  sameList: FAILS for some non-empty list inputs");
      if(!listUnchanged) System.out.println("  sameList: FAILS - original lists are modified");
    }
    catch(Exception e)
    {
      System.out.println("  sameList: CRASHES for some non-empty list inputs");
    }
    calls = Node.constructCount - calls - 24;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
  }
  
  
  
  
  
  
  
  
  
  //------------------------------QUESTION #5 - BRING TO FRONT----------------------------//
  
  public static void testBringToFront()
  {
    int calls = Node.constructCount;
    try
    {
      Node x = ListPractice.bringToFront(null, 3);
      if(x != null)
        System.out.println("  bringToFront: FAILS for the empty list");
      else
        System.out.println("  bringToFront: CORRECT for the empty list");
    }
    catch(Exception e)
    {
      System.out.println("  bringToFront: CRASHES for the empty list");
    }
    try
    {
      Node x = ListPractice.bringToFront(generateAbcList(), 3);
      if(!sameList(generateAbcList(), x))
        System.out.println("  bringToFront: FAILS when given an invalid position");
      else System.out.println("  bringToFront: CORRECT when given an invalid position");
    }
    catch(Exception e)
    {
      System.out.println("  bringToFront: CRASHES when given an invalid position");
    }
    
    try
    {
      Node x = ListPractice.bringToFront(generateAbcList(), 2);
      if(!sameList(new Node("c", new Node("a", new Node("b", null))), x))
        System.out.println("  bringToFront: FAILS when moving last node in the list");
      else System.out.println("  bringToFront: CORRECT when moving last node in the list");
    }
    catch(Exception e)
    {
      System.out.println("  bringToFront: CRASHES when moving last node in the list");
    }
    
    try
    {
      Node x = ListPractice.bringToFront(generateAList(), 0);
      if(!sameList(generateAList(), x))
        System.out.println("  bringToFront: FAILS when only one node in the list");
      else System.out.println("  bringToFront: CORRECT when only one node in the list");
    }
    catch(Exception e)
    {
      System.out.println("  bringToFront: CRASHES when only one node in the list");
    }
    
    try
    {
      boolean works = true;

      Node student = ListPractice.bringToFront(generateLongList(),0);
      if(!sameList(student,generateLongList()))
        works = false;
      
      student = ListPractice.bringToFront(generateLongList(),20);
      Node solution = CorrectListPractice.bringToFront(generateLongList(), 20);
      
      if(!sameList(student,solution))
        works = false;
      
      if(works) System.out.println("  bringToFront: CORRECT for non-empty list inputs");
      else System.out.println("  bringToFront: FAILS for some non-empty list inputs");
    }
    catch(Exception e)
    {
      System.out.println("  bringToFront: CRASHES for some non-empty list inputs");
    }
    calls = Node.constructCount - calls - 214;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
  }
  
  
  
  
  
  
  
  
  
  
  
  //------------------------------QUESTION #6 - MAKE CIRCULAR----------------------------//

  public static void testMakeCircular()
  {
    int calls = Node.constructCount;
    try
    {
      ListPractice.makeCircular(null);
      System.out.println("  makeCircular: CORRECT for the empty list");
    }
    catch(Exception e)
    {
      System.out.println("  makeCircular: CRASHES for the empty list");
    }
    try
    {
      boolean works = true;
      
      Node head = generateAList();
      ListPractice.makeCircular(head);
      Node temp = head;
      for(int i = 0; i < 5; i++)
      {
        if(!temp.getData().equals("a")) works = false;
        temp = temp.getNext();
      }
      
      head = generateDupList();
      ListPractice.makeCircular(head);
      temp = head;
      String s = "aaba";
      int index = 0;
      for(int i = 0; i < 20; i++)
      {
        if(!temp.getData().equals(s.substring(index, index+1))) works = false;
        index++;
        if(index == 4) index = 0;
        temp = temp.getNext();
      }
      
      head = generateLongList();
      ListPractice.makeCircular(head);
      temp = head;
      s = longListString();
      index = 0;
      for(int i = 0; i < 20; i++)
      {
        if(!temp.getData().equals(s.substring(index, index+1))) works = false;
        index++;
        if(index == s.length()) index = 0;
        temp = temp.getNext();
      }
      
      if(works) System.out.println("  makeCircular: CORRECT for non-empty list inputs");
      else System.out.println("  makeCircular: FAILS for some non-empty list inputs");
    }
    catch(Exception e)
    {
      System.out.println("  makeCircular: CRASHES for some non-empty list inputs");
    }
    calls = Node.constructCount - calls - 55;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
  }
  
  
  
  
  
  
  
  
  
  
  //------------------------------QUESTION #7 - INTERSECTION----------------------------//

  public static void testIntersection()
  {
    int calls = Node.constructCount;
    try
    {
      if(ListPractice.intersection(null,null)==null)
        System.out.println("  intersection: CORRECT when comparing two empty lists");
      else
        System.out.println("  intersection: FAILS when comparing two empty lists");
    }
    catch(Exception e)
    {
      System.out.println("  intersection: CRASHES when comparing empty lists");
    }
    
    try
    {
      boolean works = true;
      if(ListPractice.intersection(null,new Node("a", null)) != null)
        works = false;
      if(ListPractice.intersection(new Node("b", null), null) != null)
        works = false;
      if(works) 
        System.out.println("  intersection: CORRECT when comparing an empty list to a non-empty list");
      else System.out.println("  intersection: FAILS when comparing an empty list to a non-empty list");
    }
    catch(Exception e)
    {
      System.out.println("  intersection: CRASHES when comparing an empty list to a non-empty list");
    }
    try
    {
      boolean works = true;
      boolean listUnchanged = true;
     
      Node abc = generateAbcList();
      Node a = generateAList();
      
      if(!sameList(ListPractice.intersection(abc, a), 
                   CorrectListPractice.intersection(generateAbcList(), generateAList())))
        works = false;
      
      if(!sameList(abc, generateAbcList()) || !sameList(a, generateAList()))
        listUnchanged = false;
      
      if(works) System.out.println("  intersection: CORRECT for non-empty list inputs");
      else System.out.println("  intersection: FAILS for some non-empty list inputs");
      if(!listUnchanged) System.out.println("  intersection: FAILS - original lists are modified");
    }
    catch(Exception e)
    {
      System.out.println("  intersection: CRASHES for some non-empty list inputs");
    }
    calls = Node.constructCount - calls - 19;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
  }
  
  
  
  
  
  
  
  //------------------------------QUESTION #8 - REMOVE ALL----------------------------//
  
  public static void testRemoveAll()
  {
    int calls = Node.constructCount;
    try
    {
      Node x = ListPractice.removeAll(null,0);
      if(x != null)
        System.out.println("  removeAll: FAILS for the empty list");
      else
        System.out.println("  removeAll: CORRECT for the empty list");
    }
    catch(Exception e)
    {
      System.out.println("  removeAll: CRASHES for the empty list");
    }
    
    
    try
    {
      Node x = ListPractice.removeAll(new Node("a", null),1);
      if(x != null)
        System.out.println("  removeAll: FAILS when removing the only node in the list");
      else
        System.out.println("  removeAll: CORRECT when removing the only node in the list");
    }
    catch(Exception e)
    {
      System.out.println("  removeAll: CRASHES when removing the only node in the list");
    }
    
    try
    {
      Node x = ListPractice.removeAll(generateAbcList(),2);
      if(!sameList(x, generateAbcList()))
        System.out.println("  removeAll: FAILS when no nodes should be removed");
      else
        System.out.println("  removeAll: CORRECT when no nodes should be removed");
    }
    catch(Exception e)
    {
      System.out.println("  removeAll: CRASHES when no nodes should be removed");
    }
    
    try
    {
      Node x = ListPractice.removeAll(generateAbcList(),1);
      if(x != null)
        System.out.println("  removeAll: FAILS when removing all nodes in the list");
      else
        System.out.println("  removeAll: CORRECT when removing all nodes in the list");
    }
    catch(Exception e)
    {
      System.out.println("  removeAll: CRASHES when removing all nodes in the list");
    }
    
    
    try
    {
      Node x = ListPractice.removeAll(new Node("a", new Node("ab", null)),1);
      if(x == null || !x.getData().equals("ab") || x.getNext() != null)
        System.out.println("  removeAll: FAILS when removing the first node in the list");
      else
        System.out.println("  removeAll: CORRECT when removing the first node in the list");
    }
    catch(Exception e)
    {
      System.out.println("  removeAll: CRASHES when removing the first node in the list");
    }
    
    
    try
    {
      Node x = ListPractice.removeAll(new Node("a", new Node("ab", null)),2);
      if(!sameList(x, new Node("a", null)))
        System.out.println("  removeAll: FAILS when removing the last node in the list");
      else
        System.out.println("  removeAll: CORRECT when removing the last node in the list");
    }
    catch(Exception e)
    {
      System.out.println("  removeAll: CRASHES when removing the last node in the list");
    }

    calls = Node.constructCount - calls - 15;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
    
  }
  
  
  
  
  
  //------------------------------QUESTION #9 - INSERT IN ORDER----------------------------//

  public static void testInsertInOrder()
  {
    int calls = Node.constructCount;
    try
    {
      Node x = ListPractice.insertInOrder(null, "a");
      if(sameList(x, generateAList()))
        System.out.println("  insertInOrder: CORRECT for the empty list");
      else System.out.println("  insertInOrder: FAILS for the empty list");
    }
    catch(Exception e)
    {
      System.out.println("  insertInOrder: CRASHES for the empty list");
    }
    
    try
    {
      Node x = ListPractice.insertInOrder(new Node("b", null), "a");
      if(sameList(x, new Node("a", new Node("b", null))))
        System.out.println("  insertInOrder: CORRECT for inserting at the beginning of the list");
      else System.out.println("  insertInOrder: FAILS for inserting at the beginning of the list");
    }
    catch(Exception e)
    {
      System.out.println("  insertInOrder: CRASHES for inserting at the beginning of the list");
    }
    
    try
    {
      Node x = ListPractice.insertInOrder(new Node("b", null), "c");
      if(sameList(x, new Node("b", new Node("c", null))))
        System.out.println("  insertInOrder: CORRECT for inserting at the end of the list");
      else System.out.println("  insertInOrder: FAILS for inserting at the end of the list");
    }
    catch(Exception e)
    {
      System.out.println("  insertInOrder: CRASHES for inserting at the end of the list");
    }
    
    
    calls = Node.constructCount - calls - 9;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
  }
  
  
  
  
  //------------------------------QUESTION #10 - REVERSE----------------------------//
  
  public static void testReverse()
  {
    int calls = Node.constructCount;
    try
    {
      if(ListPractice.reverse(null)==null)
        System.out.println("  reverse: CORRECT for the empty list");
      else System.out.println("  reverse: FAILS for the empty list");
    }
    catch(Exception e)
    {
      System.out.println("  reverse: CRASHES for the empty list");
    }
    
    try
    {
      if(sameList(ListPractice.reverse(generateAList()),generateAList()))
        System.out.println("  reverse: CORRECT for the one-element list");
      else System.out.println("  reverse: FAILS for the one-element list");
    }
    catch(Exception e)
    {
      System.out.println("  reverse: CRASHES for the one-element list");
    }
    
    try
    {
      boolean works = true;
      
      Node x = ListPractice.reverse(generateAbcList());
      if(!sameList(x, new Node("c", new Node("b", new Node("a", null))))) works = false;
      
      if(works) System.out.println("  reverse: CORRECT for non-empty list inputs");
      else System.out.println("  reverse: FAILS for some non-empty list inputs");
    }
    catch(Exception e)
    {
      System.out.println("  reverse: CRASHES for some non-empty list inputs");
    }
    calls = Node.constructCount - calls - 8;
    if(calls > 0) System.out.println("  NOTE: Student made "+calls
                                       +" unnecessary Node constructor calls");
    
  }
  
  
  
  
  
  
  
  
  //------------------------------TEST CASE GENERATORS----------------------------//
  
  public static Node generateAbcList()
  {
    return new Node("a", new Node("b", new Node("c", null)));
  }
  
  public static Node generateAList()
  {
    return new Node("a", null);
  }
  
  public static Node generateLongList()
  {
    Node head = new Node("a", null);
    Node temp = head;
    for(int i = 1; i < 50; i++)
    {
      temp.setNext(new Node(""+(char)('a'+i), null));
      temp = temp.getNext();
    }
    return head;
  }
  
  public static String longListString()
  {
    String s = "";
    for(int i = 0; i <50; i++)
    {
      s += (char)('a' + i);
    }
    return s;
  }
  
  public static Node generateAbcAbcList()
  {
    return new Node("a", new Node("b", new Node("c", new Node("a", new Node("b", new Node("c", null))))));
  }
  
  public static Node generateDupList()
  {
    return new Node("a", new Node("a", new Node("b", new Node("a", null))));
  }
  
  public static boolean sameList(Node n1, Node n2)
  {
    Node temp1 = n1;
    Node temp2 = n2;
    
    while(temp1 != null && temp2 != null)
    {
      if(!temp1.getData().equals(temp2.getData())) return false;
      
      temp1 = temp1.getNext();
      temp2 = temp2.getNext();
    }
    if(temp1==null && temp2==null) return true;
    else return false;
  }
  
}