/*
 * ID:cs12snm
 * PID:A99070381
 * NAME: Zhiyu Mao
 */
/*
 * Yuming Qiao cs12soq
testBooleanAdd(), testAdd(), testRemove(), testEmpty(), testClear()

Hongda Xiao cs12sqo
testGetException(),testIterator(), testValidAddIterator(), testInvalidAddIterator(), testValidSetIterator(), testIteratorFibonacci()

Zhouhang Shao cs12soz
testSet(), testValidRemoveIterator(), testIteratorNext(), testIteratorInvalidNext(), testIteratorHasNext(), testIteratorPrevious()
Zhiyu Mao cs12snm
testIteratorInvalidPrevious(), testIteratorHasPrevious(), testIteratorNextIndex(), testIteratorPreviousIndex()

 * */
package hw2;

import org.junit.*;

import static org.junit.Assert.*;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for LinkedList class
 *  @author Yuming Qiao, Hongda Xiao, Zhiyu Mao, Zhouhang Shao
 *  @version 3.0 15-April-2015
 * */

public class MyLinkedListTester
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
			// List: 1,2,3,...,Dim
			for (int i = DIM; i > 0; i--)
				several.add(0,new Integer(i));

			// List: "First","Last"
			slist = new MyLinkedList<String>();
			slist.add(0,"First");
			slist.add(1,"Second");
			slist.add(2,"Last");
		}
	/**
	 * Test consecutive remove in iterator
	 */
	@Test
		public void testIteratorRemove() {
		System.out.println(several);
		ListIterator<Integer> iter = several.listIterator();
		for (int i = 1; i <= DIM; i++)
			assertEquals(new Integer(i), iter.next());
		for (int i = DIM; i >= 1; i--) {
			assertEquals(new Integer(i), iter.previous());
			iter.remove();
		}
		
		
	}
	/** Test if heads of the lists are correct */ 
	@Test
		public void testGetHead()
		{
			assertEquals("Check 0",new Integer(0),one.get(0)) ;
			assertEquals("Check 0",new Integer(1),several.get(0)) ;

		}

	/** Test if get() method return the right
	 * value and throw exception when index is not existed
	 *  in the list */
	@Test
		public void testGet(){     
			try{
				several.add(new Integer(6));
				assertEquals("Check 6", new Integer(6), several.get(5));
			}
			catch (NullPointerException e)
			{
			}
		}

	/** Test if size of lists are correct */

	@Test
		public void testListSize()
		{
			assertEquals("Check Empty Size",0,empty.size()) ;
			assertEquals("Check One Size",1,one.size()) ;
			assertEquals("Check Several Size",DIM,several.size()) ;
		}




	/** Test boolean add method */
	@Test
		public void testBooleanAdd()
		{
			// Test if successfully added to the END position
			assertTrue("Check adding element to the end position of the empty list",
					empty.add(new Integer(0)));
			assertTrue("Check adding element to the end position of the one list",
					one.add(new Integer(2)));
			assertTrue("Check adding element to the end position of the sevaral list",
					several.add(new Integer(3)));

			// Test if not successfully added to the LAST position
			assertEquals("check the last element added to an empty list", 
					new Integer(0), empty.get(empty.size() - 1));
			assertEquals("check the last element added to an one list",
					new Integer(2), one.get(one.size() - 1));
			assertEquals("check the last element added to the several list",
					new Integer(3), several.get(several.size() - 1));      
		}

	/** Test the void add method 
	 */
	@Test
    public void testAdd(){

        // Add an element to the BEGINNING of the list.
        several.add(0,new Integer(3));     
        assertEquals("Check size updated correctly after insertion",
                6, several.size());
        assertEquals("Check successfully add to the beginning",
                new Integer(3), several.get(0));
        assertEquals("Check the next of the added element", 
                new Integer(1), several.get(1));

        // Add an element to the END of the list
        several.add(several.size(), new Integer(2));
        assertEquals("Check size updated correctly after insertion",
                7, several.size());
        assertEquals("Check successfully add to the end",
                new Integer(2), several.get(several.size() - 1));
        assertEquals("Check the prev of the added element", 
                new Integer(5), several.get(5));
        

        // Add an element to the MIDDLE of the list
        several.add(3, new Integer(5));
        assertEquals("Check size updated correctly after insertion",
                8, several.size());
        assertEquals("Check successfully add to the MIDDLE",
                new Integer(5), several.get(3));
        assertEquals("Check the prev of the added element", new Integer(2), 
                several.get(2));
        assertEquals("Check the next of the added element", new Integer(3), 
                several.get(4));
        //Add an element to the middle of the list  
        slist.add(1, "Insert at position 1");
        assertEquals("Check size updated correctly after insertion", 
                4, slist.size());
        assertEquals("Check successfully add to the MIDDLE", 
                "Insert at position 1", slist.get(1));


    }

	/** Test the remove method 
	 */
	@Test
	public void testRemove(){      
        //remove the first element
        several.remove(0);
        assertEquals("List size is not properly changed after valid deletion",
                4,several.size());

        //remove the middle of the element
        several.remove(2);


        assertEquals("remove is not performed correctly DATA", 
                new Integer(5), several.get(2));
        assertEquals("remove is not performed correctly PREV", new Integer(3), 
                several.get(1));
        assertEquals("List size is not properly changed after valid deletion", 
                3,several.size());
        //remove the last elements
        several.remove(several.size() - 1);
        assertEquals("List size is changed after INVALID deletion",
                2,several.size());


    }

	/** Test the void empty method 
	 */
	@Test
		public void testEmpty()

		{
			assertTrue("empty is empty",empty.isEmpty()) ;
			assertTrue("one is not empty",!one.isEmpty()) ;
			assertTrue("several is not empty",!several.isEmpty()) ;
		}

	/** Test clear method */
	@Test
		public void testClear()
		{
			empty.clear();
			one.clear();
			several.clear();
			assertEquals("Check if Empty Size is cleared",0,empty.size()) ;
			assertEquals("Check if One Size is cleared",0,one.size()) ;
			assertEquals("Check if Several Size is cleared",0,several.size()) ;
		}


	/** Test out of bounds exception on get */ 
	@Test
		public void testGetException()
		{       
			try 
		{
		    empty.set(3,new Integer(1));
		fail("Should have generated an exception");  
	
		}
			catch(IndexOutOfBoundsException e)
			{
			//  normal
			}
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
			for (iter = several.listIterator(); iter.hasNext(); iter.next()){
				counter++;
			}
			assertEquals("Iterator several count", counter, DIM);
		}

	/** Test the iterator's add method
	 * when the test is valid and doesn't throw
	 * exception.
	 */
	@Test
		public void testValidAddIterator()
		{
			int i = 0;
			ListIterator<Integer> testIterator;
			for (testIterator = several.listIterator(); testIterator.hasNext(); 
					testIterator.next())
				i++;

			// Test if the elements are successfully added to the several list
			assertEquals("Iterator several count", i, DIM);
			assertTrue("The previous element", testIterator.hasPrevious());

			// Test if an integer 3 is successfully added to the list.
			testIterator.previous();
			testIterator.add(new Integer(3));
			assertEquals("check if 3 is added to the list at index 4", new Integer(3), several.get(4));

			// Test if an integer 6 is successfully added to the list.
			testIterator.add(new Integer(6));
			assertEquals("check if 3 is added to the list at index 4", new Integer(6), several.get(5));
			assertEquals("Size is updated correctly after adding 3 ",7, several.size());
		}
	/** Test the iterator's add method
	 * and if tester throw exception when out of bounds.
	 */
	@Test
		public void testInvalidAddIterator()
		{
			int i = 0;
			ListIterator<Integer> testIterator;
			for (testIterator = several.listIterator(); testIterator.hasNext(); 
					testIterator.next()){
				i++;      
				try 
				{
					testIterator.add(null);
					fail("Should have generated an exception");  
				}
				catch(NullPointerException e)
				{
				}
			}
		}
	/** Test if the Iterator is valid
	 */
	@Test
	 public void testValidSetIterator(){
        int counter = 0 ;
        ListIterator<Integer> iter;

        for (iter = several.listIterator() ; iter.hasNext(); iter.next())
            counter++;
        assertEquals("Iterator several count", counter, DIM);
        assertTrue("The previous element", iter.hasPrevious());

        iter.set(new Integer(15));
        assertEquals("Iterator several count", counter, DIM);
        assertEquals("The element is set correctly", new Integer(15),
                several.get(4));

        try{
            iter.set(null);
            fail("should have generated exception");
        }
        catch(NullPointerException e){

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
			assertEquals(iter.next() + iter.next(),sum);
		}



	/** Test setting a specific entry */ 
	@Test
		public void testSet(){
			//test the if the value is correctly set
			several.set(1, new Integer(2));
			several.set(4, new Integer(3));
			assertEquals("check the element is correctly added at 1 in linkedlist several", 
					new Integer(2), several.get(1));
			assertEquals("check the element is correctly added at 4 in linkedlist several",
					new Integer(3), several.get(4));
			one.set(0,new Integer(1));
			assertEquals("check the element is correctly added at 0 in linkedlist one",
					new Integer(1),one.get(0));
			slist.set(1,"Final");
			assertEquals("Setting specific value", "Final",slist.get(1));
		}


	/** Test valid remove of a specific entry */
	@Test
		public void testValidRemoveIterator(){
			int counter = 0 ;
			ListIterator<Integer> iter;

			for (iter = several.listIterator() ; iter.hasNext(); iter.next())
				counter++;
			assertEquals("Iterator several count", counter, DIM);
			assertTrue("The previous element", iter.hasPrevious());

			iter.remove();
			assertEquals("The element is set correctly", 4, several.size());

			try{
				iter.remove();
				fail("should have generated exception");
			}
			catch(IllegalStateException e){
			}
		} //closes this method
	/**Test the next() method*/
	@Test
		public void testIteratorNext(){
			ListIterator<Integer> iter1 = several.listIterator();
			assertEquals(iter1.next(), new Integer(1));
			assertEquals(iter1.next(), new Integer(2));
			assertEquals(iter1.next(), new Integer(3));
			assertEquals(iter1.next(), new Integer(4));
			assertEquals(iter1.next(), new Integer(5));
		}

	/**Test the invalid use of the next() method*/
	@Test 
		public void testIteratorInvalidNext(){
			try{
				ListIterator<Integer> iter1 = several.listIterator();
				for (int i = 0; i < 5; i++){
					iter1.next();
				}
				iter1.next();
				fail("there should be an exception");
			}
			catch(NoSuchElementException n){
				// do nothing
			}
		}

	/** Test the hasNext() method*/
	@Test
		public void testIteratorHasNext(){
			ListIterator<Integer> iter1 = several.listIterator();
			assertTrue(iter1.hasNext());
			for (int i = 0; i < 5; i++){
				iter1.next();
			}
			assertFalse(iter1.hasNext());
		}

	/** test the hasprevious() method */
	@Test
		public void testIteratorPrevious(){
			ListIterator<Integer> iter1 = several.listIterator();
			for(int i = 0; i < 5; i++){
				iter1.next();
			}
			assertEquals(iter1.previous(), new Integer(5));
		} 
	/** Test the invalid previous method*/
	@Test
		public void testIteratorInvalidPrevious(){
			try{
				ListIterator<Integer> iter1 = several.listIterator();
				for(int i = 0; i < 5; i++){
					iter1.next();
				}
				for(int i = 0; i < 6; i++){
					iter1.previous();
				}
				fail("Should throw an exception");
			}
			catch(NoSuchElementException n){
			}	
		}
	/** Test if the iterator's hasPrevious method is correct */ 
	@Test 
		public void testIteratorHasPrevious(){
			ListIterator<Integer> iter1 = several.listIterator();
			assertFalse(iter1.hasPrevious());
			iter1.next();
			assertTrue(iter1.hasPrevious());
		}
	/** Test if the iterator's nextIndex method is correct */ 
	@Test
		public void testIteratorNextIndex(){
			ListIterator<Integer> iter1 = several.listIterator();
			ListIterator<Integer> iter2 = empty.listIterator();
			for (int i = 0; i < 5; i++){
				iter1.next();
				assertEquals(iter1.nextIndex(), i+1);
			}
			assertEquals(iter2.nextIndex(), 0);

		}
	/** Test if the iterator's previous method is correct */
	@Test
		public void testIteratorPreviousIndex(){
			ListIterator<Integer> iter1 = several.listIterator();
			assertEquals(iter1.previousIndex(), -1);
			for (int i = 0; i < 5; i++){
				iter1.next();
				assertEquals(iter1.previousIndex(), i);  
			}
		}

} // end of file
