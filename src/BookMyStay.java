import java.util.*;

/**
 * UseCase5BookingRequestQueue demonstrates fair request handling.
 * It introduces a Queue to store booking requests in arrival order (FIFO).
 * * @author User
 * @version 5.0
 */

// Domain Model: Reservation represents a guest's intent to book
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

/**
 * BookingRequestQueue manages incoming reservations fairly using a Queue.
 */
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        // LinkedList implements the Queue interface and preserves insertion order
        this.requestQueue = new LinkedList<>();
    }

    // Add request to the queue (Request Intake)
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
    }

    // Process and display requests in FIFO order
    public void processRequests() {
        System.out.println("Booking Request Queue");
        while (!requestQueue.isEmpty()) {
            Reservation res = requestQueue.poll(); // Removes the head of the queue
            System.out.println("Processing booking for Guest: " + res.getGuestName() +
                    ", Room Type: " + res.getRoomType());
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        // Initialize the Booking Queue
        BookingRequestQueue queueManager = new BookingRequestQueue();

        // Guest submits booking requests (Arrival Order)
        queueManager.addRequest(new Reservation("Abhi", "Single"));
        queueManager.addRequest(new Reservation("Subha", "Double"));
        queueManager.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process requests to demonstrate FIFO behavior
        queueManager.processRequests();
    }
}