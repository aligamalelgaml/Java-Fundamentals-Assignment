package assignment;

public class CastingExample {
    public static void main(String[] args) {
        Object obj = "Hello, I am actually a String!";

        // Perform class casting to convert Object to String
        String str = (String) obj;

        System.out.println(str); // Output: Hello, I am a String!
    }
}
