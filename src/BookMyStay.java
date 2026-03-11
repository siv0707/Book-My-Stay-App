import java.util.*;

// Service to manage booking cancellations and inventory rollback
class CancellationService {
    private Map<String, Integer> inventory;
    private Stack<String> rollbackHistory; // Tracks released Reservation IDs (LIFO)

    public CancellationService() {
        this.inventory = new HashMap<>();
        this.rollbackHistory = new Stack<>();

        // Initializing inventory for the use case
        inventory.put("Single", 5);
    }

    // Performs the cancellation and rollback logic
    public void cancelBooking(String reservationId, String roomType) {
        System.out.println("Booking Cancellation");

        // 1. Logic: In a real system, we'd verify the ID exists.
        // 2. State Reversal: Push to rollback stack
        rollbackHistory.push(reservationId);

        // 3. Inventory Restoration: Increment the count
        int currentCount = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, currentCount + 1);

        // Display results matching the requirement image
        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
        System.out.println("\nRollback History (Most Recent First):");
        System.out.println("Released Reservation ID: " + rollbackHistory.peek());
        System.out.println("\nUpdated " + roomType + " Room Availability: " + inventory.get(roomType));
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        CancellationService service = new CancellationService();

        // Guest initiates a cancellation for "Single-1"
        // In Use Case 10, we simulate the reversal of a previously confirmed booking
        service.cancelBooking("Single-1", "Single");
    }
}