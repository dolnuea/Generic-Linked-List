/**
 * @author Dolunay Dagci
 * Assignment: Ch20 Generic Linked List
 * Due: Sunday, April 28, 2019
 * This is a Demo class to test the Generic Linked List class methods on a Double List, and a String List.
 */
public class DD_GenericLinkedListDemo {
    public static void main(String[] args) {

        DD_GenericLinkedList<Double> doubles = new DD_GenericLinkedList<>(); //Create list of type Double
        DD_GenericLinkedList<String> strings = new DD_GenericLinkedList<>(); //Create list of type String

        //Add elements
        doubles.add(2.11); doubles.add(5.33); doubles.add(2.44); doubles.add(5.02);
        doubles.add(2,3.43); doubles.add(0, 0.54);
        System.out.println("Double List: " + doubles + "\nSize of the list: " + doubles.size()); //Print list

        strings.add("Sandwich"); strings.add("Burger"); strings.add("Pizza"); strings.add("Cheese"); strings.add(1,"Sushi");
        strings.add(5, "Muffin");
        System.out.println("String List: " + strings + "\nSize of the list: " + strings.size()); //Print list

        //Set the element at 3rd position to 2.7
        doubles.set(3, 2.7);
        System.out.println("\nThe 3rd element has been replaced with: " + doubles.get(3) + "\nCurrent Double List: " + doubles); //Print list
        //Set the element at 4rd position to Noodles.
        strings.set(4, "Noodles");
        System.out.println("\nThe 4rd element has been replaced with: " + strings.get(4) + "\nCurrent String List: " + strings); //Print list

        //Remove the element at 4th position
        doubles.remove(4);
        System.out.println("\nThe 4th element has been removed.\nCurrent Double List: " + doubles); //Print list
        //Remove the element at 2nd position
        strings.remove(2);
        System.out.println("\nThe 2nd element has been removed.\nCurrent String List: " + strings); //Print list

        //Remove element 0.54
        doubles.remove(0.54);
        System.out.println("\nThe element 0.54 has been removed.\nCurrent Double List: "+ doubles); //Print list
        //Remove element Muffin
        strings.remove("Muffin");
        System.out.println("\nThe element Muffin has been removed.\nCurrent String List: "+ strings); //Print list

        //Remove all elements from list
        doubles.clear();
        System.out.println("\nThe Double List has been cleared.\nCurrent Double List: " + doubles); //Print list
        strings.clear();
        System.out.println("\nThe String list has been cleared.\nCurrent String List: " + strings); //Print list
    }
}
