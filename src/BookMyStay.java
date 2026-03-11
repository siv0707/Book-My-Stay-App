import java.util.ArrayList;
import java.util.List;

// Represents an individual confirmed reservation
class Booking {
    private String guestName;
    private String roomType;

    public Booking(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

// Service to manage the list of past bookings and generate summaries
class BookingHistoryService {
    // List is ideal here because it preserves the order of confirmation
    private List<Booking> history = new ArrayList<>();

    // Adds a booking to the history list
    public void recordBooking(Booking booking) {
        history.add(booking);
    }

    // Prints the historical report
    public void generateReport() {
        System.out.println("--- Booking History Report ---");
        if (history.isEmpty()) {
            System.out.println("No history found.");
        } else {
            for (Booking b : history) {
                System.out.println(b.toString());
            }
        }
        System.out.println("------------------------------");
    }
}

// Main class name matches your filename BookMyShow.java
public class BookMyStay {
    public static void main(String[] args) {
        BookingHistoryService historyService = new BookingHistoryService();

        // Step 1: Confirm and record bookings
        historyService.recordBooking(new Booking("Abhi", "Single"));
        historyService.recordBooking(new Booking("Subha", "Double"));
        historyService.recordBooking(new Booking("Vanmathi", "Suite"));

        // Step 2: Display Output Header
        System.out.println("Book My Stay App - Operational Visibility");
        System.out.println("Booking History and Reporting\n");

        // Step 3: Admin generates the report from stored data
        historyService.generateReport();
    }
}