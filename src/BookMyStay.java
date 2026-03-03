import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup demonstrates centralized inventory management.
 * It replaces scattered variables with a HashMap to manage room availability.
 * * @author User
 * @version 3.0
 */

// Abstract class representing the domain model for a Room
abstract class Room {
    protected String type;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomInfo(int availableRooms) {
        System.out.println(this.type + ":");
        System.out.println("Beds: " + this.beds);
        System.out.println("Size: " + this.size + " sqft");
        System.out.println("Price per night: " + this.price);
        System.out.println("Available Rooms: " + availableRooms + "\n");
    }

    public String getType() {
        return type;
    }
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 250, 1500.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 400, 2500.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 750, 5000.0); }
}

/**
 * Encapsulates inventory logic using a HashMap for centralized state management.
 */
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    // Register room types and their initial counts
    public void initializeRoom(String type, int count) {
        inventory.put(type, count);
    }

    // Retrieve availability for a specific room type
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Hotel Room Inventory Status\n");

        // Initialize Domain Objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize Centralized Inventory
        RoomInventory inventoryManager = new RoomInventory();
        inventoryManager.initializeRoom(single.getType(), 5);
        inventoryManager.initializeRoom(doubleRoom.getType(), 3);
        inventoryManager.initializeRoom(suite.getType(), 2);

        // Displaying information using the centralized inventory state
        single.displayRoomInfo(inventoryManager.getAvailability(single.getType()));
        doubleRoom.displayRoomInfo(inventoryManager.getAvailability(doubleRoom.getType()));
        suite.displayRoomInfo(inventoryManager.getAvailability(suite.getType()));
    }
}