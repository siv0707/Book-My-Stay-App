/**
 * UseCase2RoomInitialization demonstrates object modeling through inheritance.
 * This version introduces abstract classes and concrete room implementations
 * within the BookMyStay application framework.
 * * @author User
 * @version 2.0
 */

// Abstract class representing a generalized Room
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

    // Method to display room details and current availability
    public void displayRoomInfo(int available) {
        System.out.println(this.type + ":");
        System.out.println("Beds: " + this.beds);
        System.out.println("Size: " + this.size + " sqft");
        System.out.println("Price per night: " + this.price);
        System.out.println("Available: " + available + "\n");
    }
}

// Concrete class for Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 250, 1500.0);
    }
}

// Concrete class for Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 400, 2500.0);
    }
}

// Concrete class for Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 750, 5000.0);
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Hotel Room Initialization\n");

        // Initializing room objects using Polymorphism
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Storing availability using individual static variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Displaying information to the console
        single.displayRoomInfo(singleAvailable);
        doubleRoom.displayRoomInfo(doubleAvailable);
        suite.displayRoomInfo(suiteAvailable);
    }
}