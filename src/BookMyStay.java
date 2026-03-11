import java.io.*;
import java.util.*;

// Service to handle saving and loading inventory to/from a file
class PersistenceService {
    private static final String FILE_NAME = "inventory.txt";

    // Saves the current inventory map to a file
    public void saveInventory(Map<String, Integer> inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Loads inventory from a file, or returns empty map if file doesn't exist
    public Map<String, Integer> loadInventory() {
        Map<String, Integer> inventory = new HashMap<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return inventory;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                if (parts.length == 2) {
                    inventory.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory. Starting fresh.");
        }
        return inventory;
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        PersistenceService persistence = new PersistenceService();

        System.out.println("System Recovery");

        // 1. Attempt to Load Data (Deserialization)
        Map<String, Integer> inventory = persistence.loadInventory();

        // 2. Failure Tolerance: If map is empty, initialize default values
        if (inventory.isEmpty()) {
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        // 3. Display Current State (Matches image_d51fb9.png)
        System.out.println("\nCurrent Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));

        // 4. Save Data (Serialization for next restart)
        persistence.saveInventory(inventory);
    }
}
