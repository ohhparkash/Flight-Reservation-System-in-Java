package Project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    // attributes
    private int flightID;//for storing flight ID
    private Passenger[] seats;// passenger array for seat availability (true = booked, false = available)
    private PassengerQueue waitList;// priority queue for the waitlist
    BinarySearchTree passengerTree;// bst for managingn, passengers details
    private int capacity;// seating capacity of the flight
    private LocalDateTime departureTime;// Scheduled departure time of the flight
    private String destinationCity;//destination city of flight
    private String departureCity;//departure city of flight
    private int AvailableSeat;//to track the next available seat index

    // Constructor
    public Flight(int flightID, int capacity,
                  String destinationCity, String departureCity, LocalDateTime departureTime) {
        this.flightID = flightID;
        this.capacity = capacity;
        this.departureTime = departureTime;
        this.destinationCity = destinationCity;
        this.departureCity = departureCity;
        this.seats = new Passenger[capacity];// initialize seats array with capacity which is null initially
        this.waitList = new PassengerQueue();
        this.passengerTree = new BinarySearchTree();
        this.AvailableSeat = 0; //start with no seats booked
    }

    //to get the seats in array
    public Passenger[] getSeats() {
        return seats;
    }
    //to calculate boarding time (1 hour before departure) by Subtracting  1 hour from the departure time
    public LocalDateTime getBoardingTime()
    {
        return departureTime.minusHours(1);
    }

    //to generate the boarding pass
    public void BoardingPass(Passenger passenger)
    {
        //to get current time & format date(hour min sec) and time(year - month -day)
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        //format of boarding date and time
        String boardingDate = getBoardingTime().format(dateFormat);
        String boardingTime = getBoardingTime().format(timeFormat);

        //print boarding pass
        System.out.println("\n=============================================");
        System.out.println("                ✈️ BOARDING PASS ✈️ ");
        System.out.println("=============================================");
        System.out.println("Flight: " + flightID + "        Boarding Time: " + boardingTime);
        System.out.println("Date: " + boardingDate);
        System.out.println("---------------------------------------------");
        System.out.println("From: " + departureCity + "      To: " + destinationCity);
        System.out.println("---------------------------------------------");
        System.out.println("Passenger ID: " + passenger.getPassengerID());
        System.out.println("Passenger Name: " + passenger.getPassengerName());
        System.out.println("Membership Level: " + passenger.getMembershipLevel());
        System.out.println("---------------------------------------------");
        System.out.println("          Have a pleasant journey! ✈️");
        System.out.println("=============================================");
    }


    // to add a passenger to the waitlist
    public void addToWaitlist(Passenger passenger) {
        waitList.enqueue(passenger);  // Add passenger in queue (waitlist)
    }

    //to cancel a passenger from the waitlist
    public boolean cancelFromWaitlist(int passengerID) {
        waitList.removePassengerByID(passengerID);  //to remove passenger from queue
        System.out.println("Passenger with ID " + passengerID + " removed from waitlist.");
        return false;//tell the cancellation is completed
    }
    // to book seat for a passenger
    public void bookSeat(Passenger passenger) {
        if (AvailableSeat < capacity) { //first check if there are available seats
            // if seat is available it will assign a seat number
            seats[AvailableSeat] = passenger;//mark the seat as booked & passenger to next seat which is available
            passengerTree.insert(passenger);//add the passenger to the BST
            System.out.println("Your Seat Number is : " + (AvailableSeat + 1));
            // Call the generateBoardingPass method to print the boarding pass
            BoardingPass(passenger);

            AvailableSeat++;  // Increment the seat number by 1
        } else {
            //if all seats are booked, add to waitlist
            addToWaitlist(passenger);
        }
    }
    public void removePassenger(int passengerID) {
        passengerTree.delete(passengerID); //to delete the passenger from the bst with ID
    }

    // Getters and Setters
    public int getFlightID() {
        return flightID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public int getAvailableSeat() {
        return AvailableSeat;
    }
    public PassengerQueue getWaitlist() {
        return waitList;  // Return the waitlist (PassengerQueue) of the current flight
    }

}

