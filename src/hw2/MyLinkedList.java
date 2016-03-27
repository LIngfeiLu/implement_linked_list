/*
 * ID:cs12snm
 * PID:A99070381
 * NAME: Zhiyu Mao
 */

package hw2;

import java.util.*;

/**
 *  Title: class MyLinkedList
 *  Description: LinkedList implementation
 *  @author Zhiyu Mao
 *  @version 3.0 15-April-2015
 * */
public class MyLinkedList<E> extends AbstractList<E> {

    private int nelems;
    private Node head;
    private Node tail;
    /**
     * Inner class that supports a node for a MyLinkedList.
     */ 
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** Constructor to create singleton Node */
        public Node(E element)
        {
            data = element;
            next = null;
            prev = null;
        }
        /** Constructor to create singleton link it between previous and next 
         *   @param element Element to add, can be null
         *   @param prevNode predecessor Node, can be null
         *   @param nextNode successor Node, can be null 
         */
        public Node(E element, Node prevNode, Node nextNode)
        {
            data = element;
            prevNode.next = this;
            nextNode.prev = this;
            next = nextNode;
            prev = prevNode;

        }
        /** Remove this node from the list. Update previous and next nodes */
        public void remove()
        {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
        /** Set the previous node in the list
         *  @param p new previous node
         */
        public void setPrev(Node p)
        {
            p.prev = this.prev;
            this.prev.next = p;
            p.next = this;
            this.prev = p;
        }
        /** Set the next node in the list
         *  @param n new next node
         */
        public void setNext(Node n)
        {
            n.next = this.next;
            this.next.prev = n;
            this.next = n;
            n.prev = this;
        }

        /** Set the element 
         *  @param e new element 
         */
        public void setElement(E e)
        {
            data = e;
        }
        /** Accessor to get the next Node in the list */
        public Node getNext()
        {
            return this.next; // XXX-CHANGE-XXX
        }
        /** Accessor to get the prev Node in the list */
        public Node getPrev()
        {
            return this.prev; // XXX-CHANGE-XXX
        } 
        /** Accessor to get the Nodes Element */
        public E getElement()
        {
            return this.data; // XXX-CHANGE-XXX
        } 
    }

    /** ListIterator implementation */ 
    protected class MyListIterator implements ListIterator<E> {

        private boolean forward;
        private boolean canRemove;
        private Node left,right; // Cursor sits between these two nodes
        private int idx;        // Tracks current position. what next() would
        /** Constructor to create a iterator*/
        public MyListIterator()
        {
            left = head;
            right = head.getNext();
            idx = 0;
            canRemove = false;
            forward = false;
        }
        /** Inserts the specified element into the list.
         *  @param e the element to insert
         */
        @Override
            public void add(E e) throws  NullPointerException
            {
                if (e == null){
                    throw new NullPointerException();
                }
                Node n = new Node(e, left, right);
                this.left = n;
                right.setPrev(n);
                left = n;
                idx++;
                forward = true;
                canRemove = false;
                nelems++;
            }
        /** Returns true if this list iterator 
         * has more elements when traversing the 
         * list in the forward direction.
         * @return true if has next element  */
        @Override
            public boolean hasNext()
            {

                if (right != tail)
                {

                    return true;
                }
                else {

                    return false;} // XXX-CHANGE-XXX
            }
        /**Returns true if this list iterator has 
         * more elements when traversing the list 
         * in the reverse direction. 
         * @return if the list iterator has more 
         * elements when traversing the list in 
         * the reverse direction*/
        @Override
            public boolean hasPrevious()
            {

                if (left != head)
                {

                    return true;

                }
                else {return false;} // XXX-CHANGE-XXX
            }
        /** Returns the next element in the list and 
         * advances the cursor position. 
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element*/
        @Override
            public E next() throws NoSuchElementException
            {
                if (right == tail)
                {
                    throw new NoSuchElementException();
                }
                else {
                    E temp = right.getElement();
                    left = right;
                    right = right.getNext();
                    canRemove = true;
                    forward = true;
                    idx++;
                    return temp;
                }
                // XXX-CHANGE-XXX
            }
        /**
         * Returns the index of the element that would be 
         * returned by a subsequent call to next().
         * @return the index of the element that would be 
         * returned by a subsequent call to next, or list 
         * size if the list iterator is at the end of the list
         * */
        @Override
            public int nextIndex()
            {

                if (right == tail){
                    return nelems;
                }
                else {

                    Node current = head;
                    int currenIndex = 0;
                    while (current.getNext() != right){
                        current = current.getNext();
                        currenIndex++;
                    }
                    return currenIndex;
                }
                // XXX-CHANGE-XXX
            }
        /**
         * Returns the previous element in the list and 
         * moves the cursor position backwards.
         * @return the previous element in the list
         * @throws NoSuchElementException if the iteration has no previous element
         * */
        @Override
            public E previous() throws NoSuchElementException
            {
                if (left == head){
                    throw new NoSuchElementException();
                }
                else {
                    E temp = left.getElement();
                    right = left;
                    left = left.getPrev();
                    forward = false;
                    canRemove = true;
                    idx--;
                    return temp;
                } // XXX-CHANGE-XXX
            }
        /**
         * Returns the index of the element that 
         * would be returned by a subsequent call 
         * to previous(). 
         * @return the index of the element that 
         * would be returned by a subsequent call 
         * to previous, or -1 if the list iterator 
         * is at the beginning of the list
         * */
        @Override
            public int previousIndex()
            {

                if (left == head){
                    return -1;
                }
                else {

                    Node current = head;
                    int currenIndex = 0;
                    while (current.getNext() != left){
                        current = current.getNext();
                        currenIndex++;
                    }
                    return currenIndex;
                }

                // XXX-CHANGE-XXX
            }
        /**
         * Removes from the list the last element that 
         * was returned by next() or previous()
         * @throws IllegalStateException  if neither next 
         * nor previous have been called, or remove or add 
         * have been called after the last call to next or previous
         * */
        @Override
            public void remove() throws IllegalStateException
            {
                if (canRemove == false)
                {
                    throw new IllegalStateException();
                }
                else if (forward == true){
                    left.remove();
                    idx--;
                    nelems--;
                    canRemove = false;
                }
                else if (forward == false){
                    right.remove();
                    nelems--;
                    canRemove = false;
                }
            }
        /**
         * Replaces the last element returned by next() or previous() 
         * with the specified element
         * @param e the element with which to replace the last 
         * element returned by next or previous*/
        @Override
            public void set(E e) 
            throws NullPointerException,IllegalStateException
            {
                if (e == null){
                    throw new NullPointerException();
                }
                if (canRemove == false){
                    throw new IllegalStateException();
                }
                else if (forward == true){
                    left.setElement(e);
                    forward = false;
                    //canRemove = false;
                }
                else if (forward == false){
                    right.setElement(e);
                    forward = false;
                }
            }

    }



    //  Implementation of the MyLinkedList Class


    /** Only 0-argument constructor is define */
    public MyLinkedList()
    {
        head = new Node(null);
        tail = new Node(null);
        head.next =tail;
        tail.prev =head;

    }
    /**
     * get the size of linked list
     * @return the size of the list
     * */
    @Override
        public int size()
        {
            // need to implement the size method
            return nelems; // XXX-CHANGE-XXX 
        }
    /**
     * Returns the element at the specified position in this list.
     * @param index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     * */
    @Override
        public E get(int index) throws IndexOutOfBoundsException
        {
            if (index < 0 || index >= nelems){
                throw new IndexOutOfBoundsException();
            }
            Node current = head;
            int currenIndex = 0;
            while (currenIndex <= index)
            {
                current = current.getNext();
                currenIndex++;
            }
            return current.getElement();  // XXX-CHANGE-XXX
        }

    @Override
        /** Add an element to the list 
         * @param index  where in the list to add
         * @param data data to add
         * @throws IndexOutOfBoundsException
         * @throws NullPointerException
         */ 
        public void add(int index, E data) 
        throws IndexOutOfBoundsException,NullPointerException
        {
            if (data==null){
                throw new NullPointerException();
            }
            else if (index > nelems || index < 0)
            {
                throw new IndexOutOfBoundsException();
            }
            else {
                Node current = head;
                int currenIndex = 0;
                while (currenIndex < index)
                {
                    current = current.getNext();
                    currenIndex++;
                }
                Node n = new Node(data);
                current.setNext(n);
                nelems++; }
        }

    /** Add an element to the end of the list 
     * @param data data to add
     * @throws NullPointerException
     */ 
    public boolean add(E data) throws NullPointerException
    {
        if (data==null){
            throw new NullPointerException();

        }
        else {
            Node n = new Node(data);
            tail.getPrev().setNext(n);
            nelems++;
            return true;
        }
    }

    /** Set the element at an index in the list 
     * @param index  where in the list to add
     * @param data data to add
     * @return element data added
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */ 
    public E set(int index, E data) 
        throws IndexOutOfBoundsException,NullPointerException
        {

            if (data==null){
                throw new NullPointerException();
            }
            else if (index >= nelems || index <0)
            {
                throw new IndexOutOfBoundsException();
            }
            Node current = getNth(index);
            current.setElement(data);
            return data; // XXX-CHANGE-XXX
        }

    /** Remove the element at an index in the list 
     * @param index  where in the list to add
     * @return element the data found
     * @throws IndexOutOfBoundsException
     */ 
    public E remove(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= nelems){
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        int currenIndex = 0;
        while (currenIndex <= index)
        {
            current = current.getNext();
            currenIndex++;
        }
        E info = current.getElement();
        current.remove();
        nelems--;
        return info; // XXX-CHANGE-XXX
    }

    /** Clear the linked list */
    public void clear()
    {
        head.setNext(tail);
        tail.setPrev(head);
        nelems = 0;
    } 

    /** Determine if the list empty 
     *  @return true if empty, false otherwise */
    public boolean isEmpty()
    {

        return (nelems == 0);  // XXX-CHANGE-XXX
    }



    // Helper method to get the Node at the Nth index
    /**
     * Helper method to get the Node at the Nth index
     * 
     * */
    private Node getNth(int index) 
    {

        Node current = head;
        int currenIndex = 0;
        while (currenIndex < index)
        {
            current = current.getNext();
            currenIndex++;
        }
        return current.getNext();  // XXX-CHANGE-XXX
    }




    /**
     * call MyListIterator, when user want to 
     * use iterator() to create a iterator
     */
    public Iterator<E> iterator()
    {
        return new MyListIterator();
    }
    /**
     * call MyListIterator, when user want to 
     * use listIterator() to create a iterator
     */
    public ListIterator<E> listIterator()
    {
        return new MyListIterator();
    }
}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

