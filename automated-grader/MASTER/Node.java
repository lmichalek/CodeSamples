/************************************************************************
 ***                                                                  ***
 ***                Testing Version of the Node Class                 ***
 ***                           (jacobo)                               ***
 ***                                                                  ***
 ************************************************************************/

public class Node
{
  private String data;
  private Node next; 
  
  public static int constructCount = 0; // To keep track of constructor calls
  
  public Node(String initData, Node initNext)
  {
    data = initData;
    next = initNext;
    constructCount++; // For testing
  }
  
  public String getData()
  {
    return data;
  }
     
  public Node getNext()
  {
    return next;
  }
     
  public void setNext(Node newNext)
  {
    next = newNext;
  }
  
  public void setData(String newData)
  {
    data = newData;
  }
}
