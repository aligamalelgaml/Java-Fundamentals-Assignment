package assignment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateFormatExample {
    public static void main(String[] args) {
        List<LocalDateTime> dates = new ArrayList<>();

        // Add current date and time to the list
        dates.add(LocalDateTime.now());

        // Define different date and time formats
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");

        // Print the dates with different formats
        for (LocalDateTime date : dates) {
            System.out.println("Format 1: " + date.format(formatter1));
            System.out.println("Format 2: " + date.format(formatter2));
            System.out.println("Format 3: " + date.format(formatter3));
        }
    }
}
