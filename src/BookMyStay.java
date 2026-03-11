import java.util.*;

// Class representing an individual optional offering
class Service {
    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

// Manages the association between reservations and selected services
class AddOnServiceManager {
    // Mapping Reservation ID to a List of Services (One-to-Many)
    private Map<String, List<Service>> reservationAddOns;

    public AddOnServiceManager() {
        this.reservationAddOns = new HashMap<>();
    }

    // Add a service to a specific reservation
    public void addServiceToReservation(String reservationId, Service service) {
        reservationAddOns.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    // Calculate total cost for a reservation's add-ons
    public double calculateTotalAddOnCost(String reservationId) {
        List<Service> services = reservationAddOns.get(reservationId);
        if (services == null) return 0.0;

        double total = 0;
        for (Service s : services) {
            total += s.getPrice();
        }
        return total;
    }
}

// REMOVED 'public' keyword here so it compiles regardless of filename
class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();
        String resId = "Single-1";

        // Defining sample services
        Service breakfast = new Service("Breakfast Buffet", 500.0);
        Service spa = new Service("Spa Treatment", 1000.0);

        // Guest selects services
        manager.addServiceToReservation(resId, breakfast);
        manager.addServiceToReservation(resId, spa);

        // Calculate and Display Output
        double totalCost = manager.calculateTotalAddOnCost(resId);

        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + resId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}