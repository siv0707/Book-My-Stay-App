import java.util.*;
import java.util.concurrent.*;

// Represents a Request for a booking
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Processor that handles inventory updates in a thread-safe manner
class ConcurrentBookingProcessor {
    private final Map<String, Integer> inventory = new ConcurrentHashMap<>();
    private final Map<String, Integer> roomCounters = new ConcurrentHashMap<>();

    public ConcurrentBookingProcessor() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        roomCounters.put("Single", 1);
        roomCounters.put("Double", 1);
        roomCounters.put("Suite", 1);
    }

    // Synchronized method ensures only one thread modifies inventory at a time
    public synchronized void processBooking(BookingRequest request) {
        int available = inventory.getOrDefault(request.roomType, 0);

        if (available > 0) {
            // Generate Room ID
            int currentId = roomCounters.get(request.roomType);
            String roomId = request.roomType + "-" + currentId;

            // Update State
            inventory.put(request.roomType, available - 1);
            roomCounters.put(request.roomType, currentId + 1);

            System.out.println("Booking confirmed for Guest: " + request.guestName + ", Room ID: " + roomId);
        } else {
            System.out.println("Booking failed for Guest: " + request.guestName + ". No " + request.roomType + " rooms available.");
        }
    }

    public void displayRemainingInventory() {
        System.out.println("\nRemaining Inventory:");
        inventory.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}

public class BookMyStay {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor();

        // Shared queue of requests
        List<BookingRequest> requests = Arrays.asList(
                new BookingRequest("Abhi", "Single"),
                new BookingRequest("Vanmathi", "Double"),
                new BookingRequest("Kural", "Suite"),
                new BookingRequest("Subha", "Single")
        );

        System.out.println("Concurrent Booking Simulation");

        // Using an ExecutorService to simulate multiple threads (Multiple Guests)
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (BookingRequest req : requests) {
            executor.execute(() -> processor.processBooking(req));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        processor.displayRemainingInventory();
    }
}