package Project;

import java.util.Scanner;
import java.time.LocalDateTime;
public class FlightReservationSystem {

    private Flight[] flights;//flight array to store flights
    private int flightCount;//count of flights(available)
    private static final double FLIGHT_PRICE = 10000;//base price for the flight
    // Constructor
    public FlightReservationSystem() {
        flights = new Flight[10];//initialize flight array with size 10
        flightCount = 0;//initially no flights are added

        //add flights to system with id, capacity, destination and departure city, time
        addFlight(new Flight(101, 2,"Karachi", "Lahore",  LocalDateTime.of(2025, 1, 15, 12, 0, 0, 0)));  // Flight 1: Karachi to Lahore at 12:00 PM
        addFlight(new Flight(102, 2, "Karachi", "Islamabad",  LocalDateTime.of(2025, 1, 15, 14, 0, 0, 0)));  // Flight 2: Karachi to Islamabad at 2:00 PM
        addFlight(new Flight(103, 2,"Karachi", "Peshawar",  LocalDateTime.of(2025, 1, 15, 16, 0, 0, 0)));  // Flight 3: Karachi to Peshawar at 4:00 PM
        addFlight(new Flight(105, 2,"Karachi", "Quetta",  LocalDateTime.of(2025, 1, 15, 18, 0, 0, 0)));  // Flight 4: Karachi to Quetta at 6:00 PM
    }

    // Method to add a flight to the system
    private void addFlight(Flight flight) {
        if (flightCount == flights.length) {
            resizeFlightArray();//if array is full resize it
        }
        flights[flightCount++] = flight;//add the flight to array with increment of 1 in count variable of flights
    }

    // Method to resize the flight array when capacity is reached
    private void resizeFlightArray() {
        Flight[] newFlights = new Flight[flights.length +1];//increase size of array by 1
        //copy each element by for loop from newflights(temp variable) to flights
        for (int i = 0; i < flightCount; i++) {
            newFlights[i] = flights[i];
        }
        flights = newFlights;  //update the flights array to the new array
    }
    //to calculate price based on membership level(platinum >  gold > silver > none)
    private double calculateDiscount(String membershipLevel) {
        double discountPercentage = 0;//bydefault discount percentage is 0

        //apply discounts based on membership level
        switch (membershipLevel.toLowerCase()) {
            case "platinum":
                discountPercentage = 0.20; //20% for Platinum
                break;
            case "gold":
                discountPercentage = 0.10; //10% for Gold
                break;
            case "silver":
                discountPercentage = 0.05; //5%  for Silver
                break;
            case "none":
                discountPercentage = 0.00; // No discount for None
                break;
            default:
                System.out.println("Invalid membership level. No discount applied.");
                break;
        }

        //apply the discount formula to calculate the final price and ret
        return FLIGHT_PRICE * (1 - discountPercentage);
    }
    //display menu
    public void Menu() {
        Scanner scanner = new Scanner(System.in);//scanner object

        while (true) {
            System.out.println("\n================================");
            System.out.println("  ✈️  FLIGHT RESERVATION  ✈️ ");
            System.out.println("=================================");
            System.out.println(" Welcome to our Flight System!");
            System.out.println("\nPlease select an option:");

            System.out.println("\n\t1. 🚀 Book a Flight");
            System.out.println("\t2. 🛑 Cancel Reservation");
            System.out.println("\t3. 🔍 Search Passenger");
            System.out.println("\t4. 📊 Generate Flight Report");
            System.out.println("\t5. ❌ Cancel Wait-list Reservation");
            System.out.println("\t6. 👋 Exit");

            System.out.print("Enter your choice: ");
            int choice = ErrorHandling .getCorrectIntegerInput(scanner, 1, 6, "Invalid choice. Please enter a number between 1 and 6.");

            switch (choice) {
                //book a flight
                case 1:
                    bookFlight(scanner); // Book a flight
                    break;
                //cancel a flight reservation
                case 2:
                    System.out.print("Enter Flight ID: ");
                    int flightID = ErrorHandling .getCorrectIntegerInput(scanner, 1, Integer.MAX_VALUE, "Invalid Flight ID.");
                    System.out.print("Enter Passenger ID: ");
                    int passengerID = ErrorHandling .getCorrectIntegerInput(scanner, 1, Integer.MAX_VALUE, "Invalid Passenger ID.");
                    cancelFlight(flightID, passengerID);
                    break;
                //search for a passenger
                case 3:
                    System.out.print("Enter Flight ID: ");
                    flightID = ErrorHandling .getCorrectIntegerInput(scanner, 1, Integer.MAX_VALUE, "Invalid Flight ID.");
                    System.out.print("Enter Passenger ID: ");
                    passengerID = ErrorHandling .getCorrectIntegerInput(scanner, 1, Integer.MAX_VALUE, "Invalid Passenger ID.");
                    searchPassenger(flightID, passengerID);
                    break;
                //generate a flight report
                case 4:
                    System.out.print("Enter Flight ID: ");
                    flightID = ErrorHandling .getCorrectIntegerInput(scanner, 1, Integer.MAX_VALUE, "Invalid Flight ID.");
                    Report(flightID);
                    break;
                //cancel a waitlist reservation
                case 5:
                    System.out.print("Enter Flight ID: ");
                    flightID = ErrorHandling.getCorrectIntegerInput(scanner, 1, Integer.MAX_VALUE, "Invalid Flight ID.");
                    System.out.print("Enter Passenger ID: ");
                    passengerID = ErrorHandling.getCorrectIntegerInput(scanner, 1, Integer.MAX_VALUE, "Invalid Passenger ID.");
                    cancelWaitList(flightID, passengerID);
                    break;
                //exit
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            //asking if the user wants to exit or return to the menu
            System.out.println("\nDo you want to return to the main menu or exit?");
            System.out.println("1. Return to Menu");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int exitChoice = ErrorHandling.getCorrectIntegerInput(scanner, 1, 2, "Invalid choice! Please enter 1 for menu or 2 to exit.");

            if (exitChoice == 2) {
                System.out.println("Exiting the system. Goodbye!");
                break; // Exit the loop and end the program
            } else if ((exitChoice == 1)) {
                Menu();
            }
            //if the user chooses 1 the loop will continue & show the menu again
        }
    }
    //to book a flight
    public void bookFlight(Scanner scanner) {
        //display all flights
        System.out.println("\nWe have the following flights available:");
        for (int i = 0; i < flightCount; i++) {
            //display each flight with number, departure & destination city
            System.out.println(" " + (i+1) + ": " + flights[i].getDepartureCity() + " to " +
                    flights[i].getDestinationCity() + " (Flight ID = " + flights[i].getFlightID() + ")");
        }
        //ask user to select a flight from 1 to flight number
        System.out.print("Enter flight number (1-" + flightCount + "): ");
        //used ErrorHandling for valid flight number
        int flightChoice = ErrorHandling.getCorrectIntegerInput(scanner, 1, flightCount, "Invalid choice! Please select a valid flight number.");

        //get the chosen flight from the flights array
        Flight selectedFlight = flights[flightChoice - 1]; //-1 bec array indexes from 0

        //if the flight is full
        if (selectedFlight.getAvailableSeat() >= selectedFlight.getCapacity()) {
            //flight is full ask if the user wants to join the waitlist
            System.out.println("We apologize, our flight " + selectedFlight.getFlightID() + " is full.");
            System.out.println("Don't worry, our airline has a waitlist.");
            System.out.print("\nDo you want to enroll yourself in the waitlist? (y/n): ");
            String userChoice = scanner.nextLine().trim().toLowerCase();

            if (userChoice.equals("y")) {
                //ask  passenger details
                System.out.print("Enter passenger name: ");
                String name = ErrorHandling.getCorrectStringInput(scanner, "Name cannot be empty!");

                System.out.print("Enter membership level (Platinum, Gold, Silver, None): ");
                String membershipLevel = ErrorHandling.getCorrectMembershipInput(scanner);

                System.out.print("Enter phone number: ");
                String phoneNumber = ErrorHandling.getCorrectPhoneNumber(scanner);

                System.out.print("Enter age: ");
                int age = ErrorHandling.getCorrectIntegerInput(scanner, 1, 120, "Invalid age! Please enter a valid age.");

                System.out.print("Enter guardian name: ");
                String guardianName = ErrorHandling.getCorrectStringInput(scanner, "Guardian name cannot be empty!");

                System.out.print("Enter guardian phone number: ");
                String guardianPhoneNumber = ErrorHandling.getCorrectPhoneNumber(scanner);

                System.out.print("Enter CNIC (XXXXX-XXXXXXX-X): ");
                String cnic = ErrorHandling.getCorrectCNIC(scanner);

                //a new passenger obj
                Passenger passenger = new Passenger(name, membershipLevel, phoneNumber, age, guardianName, guardianPhoneNumber, cnic);

                //add passenger to the waitlist
                selectedFlight.addToWaitlist(passenger);

                //to display message confirming the addition to the waitlist
                System.out.println("You have been added to the waitlist for flight " + selectedFlight.getFlightID());
                System.out.println(" We will notify you if a seat becomes available. 😊");
            } else {
                //if user chose not to join the waitlist
                System.out.println("Thank you for considering our services. Goodbye!");
            }
        } else {
            // Flight has available seats, proceed with booking
            // Collect passenger details
            System.out.print("Enter passenger name: ");
            String name = ErrorHandling.getCorrectStringInput(scanner, "Name cannot be empty!");

            System.out.print("Enter membership level (Platinum, Gold, Silver, None): ");
            String membershipLevel = ErrorHandling.getCorrectMembershipInput(scanner);

            System.out.print("Enter phone number: ");
            String phoneNumber = ErrorHandling.getCorrectPhoneNumber(scanner);

            System.out.print("Enter age: ");
            int age = ErrorHandling.getCorrectIntegerInput(scanner, 1, 120, "Invalid age! Please enter a valid age.");

            System.out.print("Enter guardian name: ");
            String guardianName = ErrorHandling.getCorrectStringInput(scanner, "Guardian name cannot be empty!");

            System.out.print("Enter guardian phone number: ");
            String guardianPhoneNumber = ErrorHandling.getCorrectPhoneNumber(scanner);

            System.out.print("Enter CNIC (XXXXX-XXXXXXX-X): ");
            String cnic = ErrorHandling.getCorrectCNIC(scanner);

            //a new passenger obj
            Passenger passenger = new Passenger(name, membershipLevel, phoneNumber, age, guardianName, guardianPhoneNumber, cnic);

            //cal price based on membership priority
            double finalPrice = calculateDiscount(membershipLevel);
            System.out.println("The price for your flight is: " +finalPrice);

            //book flight
            selectedFlight.bookSeat(passenger);  // Automatically displays the boarding pass

            //booking confirmation
            System.out.println("Your seat has been booked");
        }
    }
    public void cancelFlight(int flightID, int passengerID) {
        // Find the flight using the flight ID
        Flight selectedFlight = findFlight(flightID);
        if (selectedFlight != null) {
            // Search for the passenger in the flight's seats
            Passenger[] seats = selectedFlight.getSeats();
            boolean passengerFound = false;
            int seatIndex = -1;

            // Iterate through all the seats to find the passenger
            for (int i = 0; i < seats.length; i++) {
                if (seats[i] != null && seats[i].getPassengerID() == passengerID) {
                    passengerFound = true;
                    seatIndex = i;
                    break;
                }
            }

            if (passengerFound) {
                // Remove the passenger from the seat
                System.out.println("Passenger " + seats[seatIndex].getPassengerName() +
                        " removed from seat " + (seatIndex + 1) + " on flight " + flightID);
                seats[seatIndex] = null; // Set that seat to null (empty)

                // Delete the passenger from the BST (PassengerTree)
                selectedFlight.removePassenger(passengerID);

                // Check if there are any passengers on the waitlist
                PassengerQueue waitlist = selectedFlight.getWaitlist();
                if (!waitlist.isEmpty()) {
                    // Sort the waitlist based on priority (Platinum > Gold > Silver > None)
                    waitlist.sortQueue();

                    // Dequeue the first passenger from the waitlist
                    Passenger nextWaitlistPassenger = waitlist.dequeue();

                    // Allocate the canceled seat (seatIndex) to the waitlist passenger
                    seats[seatIndex] = nextWaitlistPassenger;

                    // Book the seat for the passenger from the waitlist
                    selectedFlight.bookSeat(nextWaitlistPassenger);

                    // Print a message confirming the allocation of the canceled seat
                    System.out.println("Waitlist passenger " + nextWaitlistPassenger.getPassengerName() +
                            " assigned to seat " + (seatIndex + 1) + " on flight " + flightID);

                    // Ensure the seat number on the boarding pass matches the assigned seat
                    nextWaitlistPassenger.setAssignedSeat(seatIndex + 1); // Assign the seat number

                    // Print the boarding pass for the passenger who has been moved to the flight
                    selectedFlight.BoardingPass(nextWaitlistPassenger); // Boarding pass with correct seat number
                    // Call the cancelFromWaitlist method from Flight class to remove the passenger from the waitlist
                    selectedFlight.cancelFromWaitlist(nextWaitlistPassenger.getPassengerID());
                }
            } else {
                System.out.println("Passenger with ID " + passengerID + " not found in flight " + flightID);
            }
        } else {
            System.out.println("Flight with ID " + flightID + " not found.");
        }
    }


    public void searchPassenger(int flightID, int passengerID) {
        //find the flight by its ID
        Flight selectedFlight = findFlight(flightID); // Ensure findFlightByID is defined properly.
        if (selectedFlight != null) {
            //first check the passenger in the flight seat allocation in passengerTree
            Passenger passenger = selectedFlight.passengerTree.search(passengerID);

            if (passenger != null) {
                System.out.println("Passenger found: " + passenger.getPassengerName());
            } else {
                // If not found in passenger list checking the waitlist
                passenger = selectedFlight.getWaitlist().Binarysearch(passengerID);
                if (passenger != null) {
                    System.out.println("Passenger found (from waitlist but now in flight): " + passenger.getPassengerName());
                }  else {
                    System.out.println("Passenger not found.");
                }
            }
        } else {
            System.out.println("Flight not found.");
        }

    }

    public void Report(int flightID) {
        //find the flight based on the Flight ID
        Flight selectedFlight = null;
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].getFlightID() == flightID) {
                selectedFlight = flights[i];
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight with ID " + flightID + " not found!");
            return;
        }

        //get the list of waitlist passengers
        PassengerQueue waitlist = selectedFlight.getWaitlist();

        //display the report
        System.out.println("\n--- Flight Report for Flight ID " + flightID + " ---");
        System.out.println("Flight ID: " + selectedFlight.getFlightID());
        System.out.println("Departure City: " + selectedFlight.getDepartureCity());
        System.out.println("Destination City: " + selectedFlight.getDestinationCity());
        System.out.println("Total Capacity: " + selectedFlight.getCapacity());
        System.out.println("Departure Date: " + selectedFlight.getDepartureTime().toLocalDate());
        System.out.println("Total Seats Filled: " + selectedFlight.getAvailableSeat());
        System.out.println("Seats Left: " + (selectedFlight.getCapacity() - selectedFlight.getAvailableSeat()));

        System.out.println("\n--- Waitlist Members ---");
        if (waitlist.isEmpty()) {
            System.out.println("No waitlist members.");
        } else {
            // in PassengerQueue a method getPassengers() will return the list of passengers
            Passenger[] waitlistPassengers = waitlist.listPassengers();  // Get the array of passengers in the waitlist
            for (int i = 0; i < waitlistPassengers.length; i++) {
                if (waitlistPassengers[i] != null) {  // Avoid null values in case of empty spots
                    System.out.println("Passenger Name: " + waitlistPassengers[i].getPassengerName() +
                            ", Passenger ID: " + waitlistPassengers[i].getPassengerID());
                }
            }
        }
    }
    public void cancelWaitList(int flightID, int passengerID) {
        //finding flight by its ID
        Flight selectedFlight = findFlight(flightID); // Ensure findFlightByID is implemented correctly
        if (selectedFlight != null) {
            //Calling the cancelWaitlistReservation method in the Flight class
            boolean passengerFound = selectedFlight.cancelFromWaitlist(passengerID);
            if (!passengerFound) {
                System.out.println("Passenger not found in the wait-list.");
            } //if passenger found
            else {
                System.out.println("Wait-list reservation successfully canceled.");
            }
        } else {
            System.out.println("Flight not found.");
        }
    }
    private Flight findFlight(int flightID) {//traverse through count of flight and find
        for (int i = 0; i < flightCount; i++) {
            if (flights[i] != null && flights[i].getFlightID() == flightID) {
                return flights[i];
            }
        }
        return null; // Return null if the flight is not found
    }

}