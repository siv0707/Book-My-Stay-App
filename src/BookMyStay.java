import java.util.*;

// Custom Exception for specific booking errors
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator to ensure inputs meet system requirements
class BookingValidator {
    private static final List<String> VALID_ROOMS = Arrays.asList("Single", "Double", "Suite");

    public void validate(String roomType) throws InvalidBookingException {
        // Requirement: It is case sensitive (Single/Double/Suite)
        if (!VALID_ROOMS.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingValidator validator = new BookingValidator();

        System.out.println("Booking Validation");

        // Input gathering
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = scanner.nextLine();

        try {
            // Validate the input
            validator.validate(roomType);

            // If validation passes
            System.out.println("Booking successful for " + guestName + " in a " + roomType + " room.");

        } catch (InvalidBookingException e) {
            // Requirement: Display meaningful failure message
            System.out.println("Booking failed: " + e.getMessage());
            System.out.println("\nNote: It is case sensitive");
        } finally {
            scanner.close();
        }
    }
}