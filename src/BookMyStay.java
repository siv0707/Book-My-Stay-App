import java.util.*;

// Booking Request class
class BookingRequest {
    String customerName;
    String roomType;

    BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

public class BookMyStay {

    // Queue for booking requests (FIFO)
    static Queue<BookingRequest> requestQueue = new LinkedList<>();

    // Inventory of rooms
    static Map<String, Integer> inventory = new HashMap<>();

    // Map room type -> allocated room IDs
    static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Counter for unique room IDs
    static int roomCounter = 1;

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);

        // Initialize allocation sets
        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());

        // Add booking requests to queue
        requestQueue.add(new BookingRequest("Alice", "Single"));
        requestQueue.add(new BookingRequest("Bob", "Double"));
        requestQueue.add(new BookingRequest("Charlie", "Single"));
        requestQueue.add(new BookingRequest("David", "Suite"));
        requestQueue.add(new BookingRequest("Eva", "Suite"));

        processBookings();
    }

    // Process booking requests
    public static void processBookings() {

        while (!requestQueue.isEmpty()) {

            BookingRequest request = requestQueue.poll();

            System.out.println("\nProcessing Booking for: " + request.customerName);
            System.out.println("Requested Room Type: " + request.roomType);

            int available = inventory.getOrDefault(request.roomType, 0);

            if (available > 0) {

                // Generate unique room ID
                String roomID = request.roomType.substring(0,1).toUpperCase() + roomCounter++;

                Set<String> roomSet = allocatedRooms.get(request.roomType);

                if (!roomSet.contains(roomID)) {

                    roomSet.add(roomID);

                    // Update inventory
                    inventory.put(request.roomType, available - 1);

                    System.out.println("Reservation Confirmed");
                    System.out.println("Allocated Room ID: " + roomID);
                }

            } else {
                System.out.println("Reservation Failed: No rooms available");
            }
        }

        // Final report
        System.out.println("\nFinal Room Allocation:");
        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + " -> " + allocatedRooms.get(type));
        }
    }
}