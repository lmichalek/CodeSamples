/************************************************************************
 ***                                                                  ***
 ***                   ListPractice.java Printer                      ***
 ***                          (lmichale)                              ***
 ***                                                                  ***
 ************************************************************************/

public class PrintMethod
{
  public static void main(String[] args)
  {
    String s = ""; // Find method to print
    switch(Integer.parseInt(args[0]))
    {
      case(0) : s = "public static void main"; break;
      case(1) : s = "public static String concatenate"; break;
      case(2) : s = "public static void insertAfter"; break;
      case(3) : s = "public static Node buildList"; break;
      case(4) : s = "public static boolean sameList"; break;
      case(5) : s = "public static Node bringToFront"; break;
      case(6) : s = "public static void makeCircular"; break;
      case(7) : s = "public static Node intersection"; break;
      case(8) : s = "public static Node removeAll"; break;
      case(9) : s = "public static Node insertInOrder"; break;
      case(10) : s = "public static Node reverse"; break;
      default: System.out.println("EXITING PROGRAM: invalid method number, 0-10 expected"); return;
    }
    
    // Set up to read
    FileLoader fl = new FileLoader("ListPractice.java");
    boolean reading = false;
    int countBraces = 0;
    boolean countedFirst = false;
    int index = 0;
    String line = "";
    
    // Read in file
    System.out.println();
    while(fl.hasMoreLines())
    {
      line = fl.readLine();
      if(!reading && line.indexOf(s) != -1)
        reading = true; // Found segment to print
      if(reading) // Within printing segment
      {
        System.out.println(line);
        index = 0;
        while(line.indexOf("{",index) > 0)
        {
          index = line.indexOf("{",index) + 1;
          if(!countedFirst) countedFirst = true;
          else countBraces++;
        }
        while(line.indexOf("}",index) > 0)
        {
          index = line.indexOf("}",index) + 1;
          countBraces--;
        }
        if(countBraces == -1) // Encountered closing brace of method
        {
          System.out.println();
          fl.close();
          return;
        }
      }
    }
  }
  
  
}