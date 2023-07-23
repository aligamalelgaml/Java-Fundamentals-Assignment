package assignment;

import java.util.HashMap;

public class HashCheckExample {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 10);
        hashMap.put("B", 20);
        hashMap.put("C", 30);

        // Test if the hashmap contains a specific key
        String keyToCheck = "B";
        if (hashMap.containsKey(keyToCheck)) {
            int value = hashMap.get(keyToCheck);
            System.out.println("Value for key " + keyToCheck + ": " + value); // Output: Value for key B: 20
        } else {
            System.out.println("Key " + keyToCheck + " not found in the hashmap.");
        }
    }
}
