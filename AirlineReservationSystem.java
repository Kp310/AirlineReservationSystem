import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int availableSeats;
    private double ticketPrice;

    public Flight(String flightNumber, String origin, String destination, int availableSeats, double ticketPrice) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        } else {
            System.out.println("No available seats on this flight.");
        }
    }
}

class Reservation {
    private String passengerName;
    private String flightNumber;

    public Reservation(String passengerName, String flightNumber) {
        this.passengerName = passengerName;
        this.flightNumber = flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}

public class AirlineReservationSystem {
    private static List<Flight> flights = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        initializeFlights();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Airline Reservation System");
            System.out.println("1. List Available Flights");
            System.out.println("2. Make a Reservation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAvailableFlights();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Airline Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeFlights() {
        // Add some sample flights
        flights.add(new Flight("F101", "New York", "Los Angeles", 100, 300.0));
        flights.add(new Flight("F102", "Los Angeles", "Chicago", 75, 250.0));
        flights.add(new Flight("F103", "Chicago", "Miami", 50, 200.0));
    }

    private static void listAvailableFlights() {
        System.out.println("Available Flights:");
        System.out.println("Flight Number | Origin      | Destination | Available Seats | Ticket Price");
        for (Flight flight : flights) {
            System.out.printf("%-13s| %-12s| %-12s| %-16d| $%.2f%n",
                    flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(),
                    flight.getAvailableSeats(), flight.getTicketPrice());
        }
    }

    private static void makeReservation(Scanner scanner) {
        scanner.nextLine(); // Consume the newline character left by nextInt()
        System.out.print("Enter your name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter the flight number: ");
        String flightNumber = scanner.nextLine();

        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight != null) {
            if (selectedFlight.getAvailableSeats() > 0) {
                selectedFlight.bookSeat();
                Reservation reservation = new Reservation(passengerName, flightNumber);
                reservations.add(reservation);
                System.out.println("Reservation successful!");
            } else {
                System.out.println("Sorry, no available seats on this flight.");
            }
        } else {
            System.out.println("Invalid flight number.");
        }
    }
}
