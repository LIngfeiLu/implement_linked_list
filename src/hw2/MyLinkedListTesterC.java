/**
 * Title: class LinkedListTester
 *  @author Shiyu Chen, Qishan Wu, Bocun Liu
 *  @version 14-April-2015
 * 
 * Contribution: 
 * Bocun Liu(cs12smx): Wrote testcases for LinkedList (testAddWithOneArgument, testAddWithTwoArgument, testRemove,
 * testClearMethod, testReverse, testAddSize, testsubstractSize)
 *             
 * Shiyu Chen(cs12saz): Wrote testcases for LinkedListIterator (testIteratorAdd, testIteratorHasNext,
 * testIteratorHasPrev, testIteratorNextIndex, testIteratoPreviousIndex, testIteratorRemove,testIteratorSet)
 *               
 * Qishan Wu(cs12sql): Wrote testcases for exceptions of LinkedList and LinkedListIterator (testGetException, testAddException, 
 * testSetException, testRemoveException, testInteratorNextExeption, testpreviousException1, testIterSetException, 
 * testIterRemoveException, testIterAddException, )
 */

 package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * You should modify the information above to add the names and CSE12 accounts
 * of all of the authors of this tester (up to 4 students from CSE 12).
 * 
 * In addition, you should indicate in this header comment for each author,
 * exactly what their contributions were.  E.g. 
 * Student 1: Wrote or helped write methods testAdd, testRemove, ...
 * 
 * Finally, when your tester is complete, you will rename it MyLinkedListTester.java 
 * (renaming MyLinkedList to MyLinkedList everywhere in the file) so that you can
 * use it to test your MyLinkedList and MyListIterator classes.
 */
public class MyLinkedListTesterC
{
  private MyLinkedList<Integer> empty ;
  private MyLinkedList<Integer> one ;
  private MyLinkedList<Integer> several ;
  private MyLinkedList<String>  slist ;
  static final int DIM = 5;
  static final int FIBMAX = 30;
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new MyLinkedList<Integer>();  
    one = new MyLinkedList<Integer>();
    one.add(0,new Integer(0));            
    several = new MyLinkedList<Integer>() ;

    for (int i = DIM; i > 0; i--)
      several.add(0,new Integer(i));   
    
    slist = new MyLinkedList<String>(); 
    slist.add(0,"First");         
    slist.add(1,"Last");
  }

  /** Test if heads of the lists are correct */
  @Test
  public void testGetHead()
  {
    assertEquals("Check 0",new Integer(0),one.get(0)) ;
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
  }

  /** Test if the linkedlist can add element using one argument */
  //**
  @Test
  public void testAddWithOneArgument()
  {
    for(int i=1;i<5;i++){
      several.add(new Integer(i));
      assertEquals("Add element to Several",new Integer(i),several.get(DIM-1+i));
    }

    for(int i=1;i<5;i++){
      slist.add("Final");
      assertEquals("Add element to Slist","Final",slist.get(1+i)) ;
    }
  }

  /**
   * Test if the linkedlist can add element using two arguments
  */ 
  @Test
  public void testAddWithTwoArgument()
  {
    for(int i=1;i<=DIM;i++){
      several.add(5-i,new Integer(i));
      assertEquals("Add element to Several at specific index",new Integer(i),several.get(5-i)) ;
    }

     slist.add(0,"New First");
     assertEquals("Add element to Slist at specific index", "New First",slist.get(0));
  }

  /**
   * Test if the lists can remove element
  */ 
   @Test
  public void testRemove()
  {
    for(int i=0;i<3;i++){
      several.remove(1);
      assertEquals("Remove element of Several at specific index", new Integer(3+i) ,several.get(1));
    }

    slist.remove(0);
    assertEquals("Remove element of Slist at specific index", "Last",slist.get(0));
  }
  
  /** Test if size of lists are correct */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size",0,empty.size()) ;
    assertEquals("Check One Size",1,one.size()) ;
    assertEquals("Check Several Size",DIM,several.size()) ;
  }
  
  /** Test setting a specific entry */
  @Test
  public void testSet()
  {
    slist.set(1,"Final");
    assertEquals("Setting specific value", "Final",slist.get(1));
  }
  
  /** Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
  }

  /** Test out of bounds exception on get */
  @Test
  public void testGetException()
  {
    try 
    {
      empty.get(0);
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }
  
  /**
   * Test if the lists can generate proper exceptions on methoad add()
  */ 
  @Test
  public void testAddException()
  {    
    try 
    {
    	several.add(6,7);
    	fail("should have generated an out of bound exception");
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
    try 
    {
    	several.add(-1,7);
    	fail("should have generated an out of bound exception");
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
   
   try
   {
   	several.add(3, null);
   	fail("should have generated an NullPointException");    	
   }
   catch(NullPointerException e)
   {
   	//normal
   }
   	
   try
   {
   	empty.add(null);
   	fail("should have generated an NullPointException");
   }
 catch(NullPointerException e)
 {
 	//normal
 }
  }
  
  /**
   * Test if the list can generate proper exceptions on methoad set()
  */
  
   @Test 
   public void testSetException()
   {	  

	   try
	   {
		   several.set(3,null);
		   fail("should have generated an NullPointException");
	   }
	   catch(NullPointerException e)
	    {
	    	//normal
	    }
	   
	   try
	   {
		 several.set(5,7);
		   fail("should have generated IndexOutOfBoundsException");

	   }
	   catch(IndexOutOfBoundsException e)
	   {
	     //  normal
	   }
	   try
	   {
		   several.set(-1,7);
		   fail("should have generated IndexOutOfBoundsException");
	   }
	   catch(IndexOutOfBoundsException e)
	   {
	     //  normal
	   }
   }
   
   /**
   * Test if the lists can generate proper exceptions on methoad remove()
   */
   @Test
   public void testRemoveException()
   {
	   try
	   {
		   several.remove(5);
		   fail("should have generated IndexOutOfBoundsException");
	   }
	   catch(IndexOutOfBoundsException e)
	   {
		   // normal
	   }
	   try
	   {
		   several.remove(-1);
		   fail("should have generated IndexOutOfBoundsException");
	   }
	   catch(IndexOutOfBoundsException e)
	   {
		   // normal
	   }
   }
   
   /**
   * Test if the lists can generate proper exceptions on methoad clear()
  */

@Test
  public void testClearMethod()
  {
	  empty.clear();
	  one.clear();
	  several.clear();
	  assertTrue("empty is size zero",empty.isEmpty());
	  assertTrue("one is size zero",one.isEmpty());
	  assertTrue("several is size zero",several.isEmpty());
  }

  /**
   * Test if the lists can be successfully reversed using add() and remove()
  */
  @Test
  public void testReverse()
  {
	  int j=DIM;
	  for(int i=0;i<DIM;i++)
	  {  
		  several.add(i,several.get(DIM-1));
		  several.remove(DIM);
	  }
	  
	  for(int i=0;i<DIM;i++)
	  {
		  assertEquals("Check reverse number",new Integer(j),several.get(i));
		  j--;
	  }
  }

  /**
   * Test if the size of lists is correct after adding several elements
  */
  
    @Test
  public void testAddSize()
  {
	  several.add(new Integer(6));
	  assertEquals("Check size of several after adding",DIM+1,several.size());
  
	  one.add(new Integer(1));
	  assertEquals("Check size of one after adding",2,one.size());
	  
	  slist.add("New Last");
	  assertEquals("Check size of slist after adding",3,slist.size());
  }

  /**
   * Test if the size of lists is correct after removing several elements
  */
  
  @Test
  public void testsubstractSize()
  {
	  for(int i=DIM;i>0;i--)
	  {
		  several.remove(0);
		  assertEquals("Check size of sveral after subtracting",i-1,several.size());
	  }
	  
	  one.remove(0);
	  assertEquals("Check size of one after subtracting",0,one.size());
  
	  slist.remove("Last");
	  assertEquals("Check size of slist after subtracting",1,slist.size());
  
  }


  /** Test iterator on empty list and several list */
  @Test
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  }

  /**
   * Test if the list iterator returns the next element in the list when going forward.
  */
  @Test
  public void testIteratorNext()
  {

     ListIterator<Integer> iter;

     //One
     iter = one.listIterator();
     assertEquals("The next elemnt of One iterator",new Integer(0),iter.next());

     //Multiple
     iter = several.listIterator();
    for(int i=1;i<5;i++){
      assertEquals("The next elemnt of Several iterator",new Integer(i),iter.next());
    }

    ListIterator<String> iter2;

    iter2 = slist.listIterator();
    assertEquals("The second element of Slist iterator","First",iter2.next()) ;
    assertEquals("The first element of Slist iterator","Last",iter2.next()) ;

  }
  
  /**
   * Test if the list iterator returns the previous element in the list when going backford.
  */
  @Test
  public void testIteratorPrev()
  {

     ListIterator<Integer> iter;

     //One 
     iter = one.listIterator();
     iter.next();
     assertEquals("The first element of One iterator",new Integer(0),iter.previous());


     //Multiple 
     iter = several.listIterator();
     for(int i=1;i<=DIM;i++){   //Move to the last position of the list
      iter.next();
    }

    for(int i=DIM;i>=1;i--){
      assertEquals("The previous elemnt of Several iterator",new Integer(i),iter.previous());
    }

     //Slist 
    ListIterator<String> iter2;
     iter2 = slist.listIterator();
     iter2.next();
     iter2.next();
     assertEquals("The second element of Slist iterator","Last",iter2.previous()) ;
     assertEquals("The first element of Slist iterator","First",iter2.previous()) ;


  }

   /**
   * Test if the list iterator inserts the given item into the list immediately before whatever 
   * would have been returned by a call to next()
   */
  @Test
  public void testIteratorAdd()
  {

     ListIterator<Integer> iter;
     
     //Empty
     iter = empty.listIterator();
    for(int i=1;i<DIM;i++){
      iter.add(new Integer(i));
      assertEquals("Add element to Empty iterator",new Integer(i),iter.previous());
    }
  }

  /**
   * Test if the list iterator returns true if there are more elements when going in the 
   * forward direction and false if there is no element
   */

  @Test
  public void testIteratorHasNext()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;

    //False
    iter = empty.listIterator();
    assertTrue("Empty iterator  has next element", !iter.hasNext());

    //True
    iter = one.listIterator();
    assertTrue("One iterator  does not has next element", iter.hasNext());

  }

  /**
   * Test if the list iterator returns true if there are more elements when going in the 
   * backward direction and false if there is no element
   */

   @Test
  public void testIteratorHasPrev()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;

    //False
    iter = empty.listIterator();
    assertTrue("Empty iterator has previous element", !iter.hasPrevious());

    //False
    iter = one.listIterator();
    assertTrue("One iterator  has previous element", !iter.hasPrevious());

    //True
    iter = several.listIterator();
    iter.next();
    assertTrue("Several iterator  does not have previous element", iter.hasPrevious());
  }

  /**
   * Test if the list iterator  returns the index of the element that would be returned 
   * by a call to next() and whether it returns the size of the list if at the end of the list.
   */

  @Test
  public void testIteratorNextIndex()
  {
    ListIterator<Integer> iter;

    //Empty
    iter = empty.listIterator();
    assertEquals("Empty iterator  has next index", 0, iter.nextIndex());

    //Several
    iter = several.listIterator();
    for(int i=1;i<DIM;i++)
    {
      assertEquals("Several iterator has wrong next index", i-1, iter.nextIndex());
      iter.next();
    }
  }

  /**
   * Test if the list iterator  returns the index of the element that would be returned 
   * by a call to previous() and whether it returns -1 at the start of the list.
   */
   @Test
  public void testIteratoPreviousIndex()
  {
    ListIterator<Integer> iter;

    //Empty
    iter = empty.listIterator();
    assertEquals("Empty iterator has previous index",-1,iter.previousIndex());

    //One
    iter = one.listIterator();
    assertEquals("The first element in One iterator  has previous index",-1, iter.previousIndex());


    //Multiple 
     iter = several.listIterator();
    for(int i=1;i<=DIM;i++){   //Move to the last position of the list
      iter.next();
    }

    for(int i=DIM;i>=1;i--){
      assertEquals("Several iterator has wrong previous index",i-1,iter.previousIndex());
      iter.previous();
    }
  }

  /**
   * Test if the list iterator removes the last element returned by the most recent call 
   * to either next/previous.
   */

   @Test
  public void testIteratorRemove()
  {
    ListIterator<Integer> iter;
    iter= several.listIterator();

    //Next
    iter.next();//move to index1 (1)
    iter.remove();
    assertEquals("Remove element from Several after calling next", new Integer(2), iter.next());

    //Previous
    iter.next();
    iter.next();
    iter.previous(); //move to index2 (5)
    assertEquals("Remove element from Several after calling previous", new Integer(4), iter.next());

  }

  /**
   * Test if the list iterator changes the value in the node returned by the most recent 
   * next/previous with the new value.
   */

  @Test
  public void testIteratorSet()
  {
    ListIterator<Integer> iter;
    iter= several.listIterator();

    //Forward
    for(int i=1;i<=DIM;i++){
      iter.next();
      iter.set(new Integer(10+i));
    }

    for(int i=DIM;i>=1;i--){
      int elem = iter.previous();
      assertEquals("Set element in several iterator in forward direction", new Integer(10+i), new Integer(elem));
    }

    //Backward

    for(int i=1;i<=DIM;i++){ //Move to the last position
      iter.next();
    }

    for(int i=1;i<=DIM;i++){
      iter.previous();
      iter.set(new Integer(i));
    }

    for(int i=DIM;i>=1;i--){
      assertEquals("Set element in several iterator in backward direction", new Integer(i), iter.next());
    }
  }

  /**
   * Test if the list iterator can generate proper exceptions on methoad next()
   */
    @Test
  public void testInteratorNextExeption()
  {
    ListIterator<Integer> iter;
    try
    {
      iter = empty.listIterator();
      iter.next();
      fail("should have generate NoSuchElementException");
    }
    catch(NoSuchElementException e)
    {
      //normal;
    }
    
    try
    {
      iter = one.listIterator();
      assertEquals("List did not return the correct value ", new Integer(0) ,iter.next());
      iter.next();
      fail("should have generate NoSuchElementException");
    }
    catch(NoSuchElementException e)
    {
      //normal;
    }
    
    
    ListIterator<String> iter2;
    try
    {
      iter2 = slist.listIterator();
      
      assertEquals("List did not return the correct value ", "First" ,iter2.next());      
      assertEquals("List did not return the correct value ", "Last" ,iter2.next());
      iter2.next();
      fail("should have generate NoSuchElementException");
    }
    catch(NoSuchElementException e)
    {
      //normal;
    }
  }

  /**
   * Test if the list iterator can generate proper exceptions on methoad previous()
   */
  @Test
  public void testpreviousException1()
  {

      ListIterator<Integer> iter;
      try
      {
        iter = empty.listIterator();
        iter.next();
        fail("should have generate NoSuchElementException");
      }
      catch(NoSuchElementException e)
      {
        //normal;
      }
  }
    /**
   * Test if the list iterator can generate proper exceptions on methoad set()
   */
    
  @Test
  public void testIterSetException()
  {

      ListIterator<Integer> iter;
      
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.remove();
        iter.set(3);
         fail("should not have generate IllegalStateException");
      }
      catch(IllegalStateException e)
      {
       //normal;
      }
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.add(3);
        iter.set(3);
        fail("should have generate IllegalStateException");
      }
      catch(IllegalStateException e)
      {
        //normal;
      }
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.remove();
        iter.next();
        iter.set(3);
      }
      catch(IllegalStateException e)
      {
        fail("should not generate IllegalStateException");
      }
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.add(3);
        iter.previous();
        iter.set(3);
      }
      catch(IllegalStateException e)
      {
        fail("should not generate IllegalStateException");
      }
  }
  @Test

  /**
   * Test if the list iterator can generate proper exceptions on methoad remove()
   */

  public void testIterRemoveException()
  {

      ListIterator<Integer> iter;
      
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.set(3);
        iter.remove();
      }
      catch(IllegalStateException e)
      {
        fail("should not generate IllegalStateException");
      }
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.add(3);
        iter.remove();
        fail("should have generate IllegalStateException");
      }
      catch(IllegalStateException e)
      {
        //normal;
      }
      
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.previous();
        iter.remove();
        iter.remove();
        fail("should not generate IllegalStateException");
      }
      catch(IllegalStateException e)
      {
        //normal
      }
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.add(3);
        iter.remove();
        fail("should not generate IllegalStateException");
      }
      
      catch(IllegalStateException e)
      {
        //normal
      }
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.remove();
      }
      catch(IllegalStateException e)
      {
        fail("should not generate IllegalStateException");
      }
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.previous();
        iter.remove();
      }
      catch(IllegalStateException e)
      {
        fail("should not generate IllegalStateException");
      }
      
  }

  /**
   * Test if the list iterator can generate proper exceptions on methoad add()
   */
  
  @Test

  public void testIterAddException()
  {
     ListIterator<Integer> iter;
      
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.add(null);
        fail("should have generate NullPointerException");
      }
      catch(NullPointerException e)
      {
        //normal;
      }
      try
      {
        iter = several.listIterator();
        //iter.next();
        iter.next();
        iter.next();
        iter.set(null);
        fail("should have generate NullPointerException");
      }
      catch(NullPointerException e)
      {
        //normal;
      }
  }
    


  /** test Iterator Fibonacci.
    * This is a more holistic test for the iterator.  You should add
    * several unit tests that do more targeted testing of the individual
    * iterator methods.  */
  @Test
  public void testIteratorFibonacci()
  {
    
    MyLinkedList<Integer> fib  = new MyLinkedList<Integer>();
    ListIterator<Integer> iter;
    // List: 0 1 1 2 3 5 8 13 ... 
    // Build the list with integers 1 .. FIBMAX
    int t, p = 0, q = 1;
    fib.add(0,p);
    fib.add(1,q);
    for (int k = 2; k <= FIBMAX; k++)
    {
      t = p+q;
      fib.add(k,t);
      p = q; q = t; 
    }
    // Now iterate through the list to near the middle, read the
    // previous two entries and verify the sum.
    iter = fib.listIterator();
    int sum = 0;
    for (int j = 1; j < FIBMAX/2; j++)
      sum = iter.next();
    iter.previous();
    assertEquals(iter.previous() + iter.previous(),sum);
    // Go forward with the list iterator
    assertEquals(iter.next() + iter.next(),sum);
  }
}

