package assignment;

import java.util.LinkedList;
public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("1st normal insertion");
        linkedList.add("2nd normal insertion");

        // Insert elements at the first and last position
        linkedList.addFirst("Element added to the first position");
        linkedList.addLast("Element added to the last position");

        System.out.println(linkedList);
    }
}
