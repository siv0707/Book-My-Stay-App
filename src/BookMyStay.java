import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * UseCase4RoomSearch demonstrates safe, read-only data access.
 * It filters inventory to show only available rooms without modifying state.
 * * @author User
 * @version 4.0
 */

// Abstract Domain Model
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
        System.out.println("Available: " + availableRooms + "\n");
    }

    public String getType() { return type; }
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

// Centralized Inventory State
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void initializeRoom(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public Map<String, Integer> getAllInventory() {
        return new HashMap<>(inventory); // Returning a copy for read-only safety
    }
}

/**
 * SearchService handles filtering and displaying available rooms.
 * Enforces the "Read-Only Access" concept.
 */
class SearchService {
    public void searchAvailableRooms(List<Room> rooms, RoomInventory inventory) {
        System.out.println("Room Search\n");
        boolean found = false;

        for (Room room : rooms) {
            int availableCount = inventory.getAvailability(room.getType());

            // Validation Logic: Show only rooms with availability > 0
            if (availableCount > 0) {
                room.displayRoomInfo(availableCount);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms currently available.");
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        // Initialize Domain and Inventory
        RoomInventory inventoryManager = new RoomInventory();
        List<Room> roomTypes = new ArrayList<>();

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        roomTypes.add(single);
        roomTypes.add(doubleRoom);
        roomTypes.add(suite);

        inventoryManager.initializeRoom(single.getType(), 5);
        inventoryManager.initializeRoom(doubleRoom.getType(), 3);
        inventoryManager.initializeRoom(suite.getType(), 2);

        // Execute Search (Read-only operation)
        SearchService searchService = new SearchService();
        searchService.searchAvailableRooms(roomTypes, inventoryManager);
    }
}