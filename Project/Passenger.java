package Project;

public class Passenger {
    // variable to generate passengerIDs from 1000
    private static int AssignedID = 1000;
    //variable to store passengerID
    private int passengerID;
    //variable to store passenger name
    private String passengerName;
    //variable to store passenger's membership level (Platinum, Gold, Silver, None)
    private String membershipLevel;
    //variable to store passenger phone number
    private String phoneNumber;
    //variable to store passenger age
    private int age;
    //variable to store passenger guardian name
    private String guardianName;
    //variable to store passenger guardian Phone Number
    private String guardianPhoneNumber;
    //variable to store passenger CNIC
    private String CNIC;
    private int assignedSeat; // Add this field in the Passenger class private int assignedSeat; // Add this field in the Passenger class

    // Constructor of passenger details
    public Passenger(String passengerName, String membershipLevel, String phoneNumber,
                     int age, String guardianName, String guardianPhoneNumber, String CNIC) {
        this.passengerID = AssignedID++;// increment id by 1 for the next passenger
        this.passengerName = passengerName;
        this.membershipLevel = membershipLevel;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.guardianName = guardianName;
        this.guardianPhoneNumber = guardianPhoneNumber;
        this.CNIC = CNIC;
    }

    // Getter and Setter methods

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhoneNumber() {
        return guardianPhoneNumber;
    }

    public void setGuardianPhoneNumber(String guardianPhoneNumber) {
        this.guardianPhoneNumber = guardianPhoneNumber;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    // Getter and Setter for assigned seat
    public int getAssignedSeat() {
        return assignedSeat;
    }

    public void setAssignedSeat(int seatNumber) {
        this.assignedSeat = seatNumber;
    }

}



