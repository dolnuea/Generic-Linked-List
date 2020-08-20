import java.util.*;
/**
 * @author Dolunay Dagci
 * Assignment: Ch20 Generic Linked List
 * Due: Sunday, April 28, 2019
 * This is Linked List @class modified as a generic class that accepts generic types and this class has a private @class named DD_Node,
 * which includes 2 constructors, and private fields. This class includes methods for adding, removing, getting, and setting elements, and
 * clearing the list (removing all the elements from the list) etc.
 * @param <E> of any type of Collection
 */
public class DD_GenericLinkedList<E> {

    /**Private DD_Node class of the Generic Linked List.
     * @param <T> of any type
     */
    protected class DD_Node<T> {
        T value;   // Value of a list element
        DD_Node<T> next;  // Next node in the list
        DD_Node<T> prev;      // Previous element in the list

        /**
         Constructor
         @param val The element to be stored in the node.
         @param n The reference to the successor node.
         @param p The reference to the predecessor node.
         */
        DD_Node(T val, DD_Node<T> n, DD_Node<T> p)
        {
            value = val; next = n; prev = p;
        }

        /**
         Constructor
         @param val The element to be stored in the node.
         */
        DD_Node(T val) {
            // Just call the other (sister) constructor
            this(val, null, null);
        }
    }

    private DD_Node<E> first;   // Head of the list
    private DD_Node<E> last;    // Last element on the list

    /**
     Constructor
     */
    DD_GenericLinkedList() {
        first = null;
        last = null;
    }

    /**
     The isEmpty method checks to see if the list
     is empty.
     @return true if list is empty, false otherwise.
     */
    private boolean isEmpty() {
        return first == null;
    }

    /**
     The size method returns the length of the list.
     @return The number of elements in the list.
     */
    int size() {
        int count = 0;
        DD_Node<E> p = first;
        while (p != null)
        {
            // There is an element at p
            count ++;
            p = p.next;
        }
        return count;
    }

    /**
     The add method adds to the end of the list.
     @param e The value to add.
     */
    void add(E e) {
        if (isEmpty()) {
            last = new DD_Node<>(e);
            first = last;
        }
        else {
            // Add to end of existing list
            last.next = new DD_Node<>(e, null, last);
            last = last.next;
        }
    }

    /**
     This add method adds an element at an index.
     @param e The element to add to the list.
     @param index The index at which to add.
     @exception IndexOutOfBoundsException
     When the index is out of bounds.
     */
    void add(int index, E e) {
        if (index < 0  || index > size()) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        // Index is at least 0
        if (index == 0) {
            // New element goes at beginning
            DD_Node<E> p = first; // Old first
            first = new DD_Node<>(e, p, null);
            if (p != null)
                p.prev = first;
            if (last == null)
                last = first;
            return;
        }

        // pred will point to the predecessor
        // of the new node.
        DD_Node<E> pred = first;
        for (int k = 1; k <= index - 1; k++) {
            pred = pred.next;
        }

        // Splice in a node with the new element
        // We want to go from  pred-- succ to pred--middle--succ
        DD_Node<E> succ = pred.next;
        DD_Node<E> middle = new DD_Node<>(e, succ, pred);
        pred.next = middle;
        if (succ == null)  last = middle;
        else succ.prev = middle;
    }

    /**
     The toString method computes the string
     representation of the list.
     @return The string representation of the
     linked list.
     */
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        // Use p to walk down the linked list
        DD_Node<E> p = first;
        while (p != null) {
            strBuilder.append(p.value).append(" ");
            p = p.next;
        }
        return strBuilder.toString();
    }

    /**
     The remove method removes the element
     at a given position.
     @param index The position of the element
     to remove.
     @return The element removed.
     @exception NoSuchElementException when there's no element at the specified index.
     */
    E remove(int index) {
        if (index < 0 || index >= size() || size()==0) {
            String message = String.valueOf(index);
            throw new NoSuchElementException(message);
        }

        // Locate the node targeted for removal
        DD_Node<E> target = first;
        for (int k = 1; k <= index; k++) target = target.next;

        E element = target.value;  // Element to return
        DD_Node<E> pred = target.prev;// DD_Node before the target
        DD_Node<E> succ = target.next;// DD_Node after the target

        // Route forward and back pointers around the node to be removed
        if (pred == null) first = succ;
        else pred.next = succ;

        if (succ == null) last = pred;
        else succ.prev = pred;

        return element;
    }

    /**
     The remove method removes an element from the list.
     @param element The element to remove.
     @return true if the element was removed, false otherwise.
     */

    boolean remove(E element) {
        if (isEmpty())
            return false;

        // Locate the node targeted for removal
        DD_Node<E> target = first;
        while (target != null && !element.equals(target.value)) target = target.next;

        if (target == null) return false;

        DD_Node<E> pred = target.prev;        // DD_Node before the target
        DD_Node<E> succ = target.next;        // DD_Node after the target

        // Route forward and back pointers around
        // the node to be removed
        if (pred == null) first = succ;
        else pred.next = succ;

        if (succ == null) last = pred;
        else succ.prev = pred;

        return true;
    }

    /**
     * The clear method removes all elements from list.
     */
    void clear(){
        first = null;
        last = null;
    }

    /**
     * The get method gets the element at the specified position.
     * @param index position in the list
     * @return the element at the index
     * @exception IndexOutOfBoundsException when the index is out of bounds.
     */
    E get(int index){
        if (index < 0 || index >= size() || first==null || last ==null) throw new IndexOutOfBoundsException();
        DD_Node<E> temp = first;
        for (int k = 0; k < index; k++) temp = temp.next; //Get to the index and find the element at index
        if (temp == null) throw new IndexOutOfBoundsException();
        return temp.value;
    }

    /**
     * The set method replaces the element at the index with the specified element.
     * @param index specified position in the list
     * @param element for replacement
     * @return the old element of the specified index
     * @exception IndexOutOfBoundsException when the index is out of bounds.
     */
    E set(int index, E element){
        if (index < 0 || index >= size() || first==null || last ==null) throw new IndexOutOfBoundsException();
        DD_Node<E> temp = first;
        for (int k = 0; k < index; k++) temp = temp.next; //Get to the index and find the element at index
        E oldValue = temp.value; //Assign the old value to the value at the current index
        temp.value = element; //Replace the element at the index with the specified element
        return oldValue;
    }
}
