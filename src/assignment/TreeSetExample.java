package assignment;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(10);
        hashSet.add(5);
        hashSet.add(15);

        // Convert hash set to tree set
        TreeSet<Integer> treeSet = new TreeSet<>(hashSet);


        // Retrieve and remove the last element
        try {
            int lastElement = treeSet.pollLast();
            System.out.println("Last Element: " + lastElement); // Output: Last Element: 15
        } catch (NullPointerException e) {
            System.err.println("Last element is null, tree must be empty!");
            e.printStackTrace();
        }
    }
}
