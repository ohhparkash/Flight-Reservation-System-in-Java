package Project;
import java.util.Scanner;

//class to handle errors
public class ErrorHandling {

    // to get a correct integer input from the user within range
    public static int getCorrectIntegerInput(Scanner scanner, int minimum, int maximum, String errorMessage) {
        //loop until correct input is received
        while (true) {
            if (scanner.hasNextInt()) {//checks if the next input is an integer
                int value = scanner.nextInt(); //reads the integer value
                scanner.nextLine(); // Consume newline
                //cgeck if the value is within range
                if (value >= minimum && value <= maximum) {
                    return value; //return the correct value
                }
            } else {
                scanner.nextLine(); //consumes incorrect input to avoid infinite loop
            }
            // Prints error message if input is not correct
            System.out.println(errorMessage);
        }
    }

    // to get a valid string input from the user (oin only letters and spaces)
    public static String getCorrectStringInput(Scanner scanner, String errorText) {
        //loop will run until correct input is received
        while (true) {
            String input = scanner.nextLine();//read input
            if (!input.isEmpty() && input.matches("[a-zA-Z\\s]+")) {//check if input contains only letters and spaces
                return input;//return the correct string
            }
            System.out.println(errorText + " (Only letters and spaces are allowed.)");
        }
    }

    //to get a valid membership level input from the user(Platinum, Gold, Silver, or None)
    public static String getCorrectMembershipInput(Scanner scanner) {
        while (true) {//loop will run until correct input is received
            String input = scanner.nextLine();//read input
            // Check if the input matches any of the valid membership levels but case-insensitive
            if (areStringsEqual(input, "Platinum") || areStringsEqual(input, "Gold") ||
                    areStringsEqual(input, "Silver") || areStringsEqual(input, "None")) {
                return input;//return the correct membership
            }
            System.out.println("Invalid membership level. Please enter Platinum, Gold, Silver, or None.");
        }
    }

    // Custom method to check if two strings are equal in a case-insensitive manner
    public static boolean areStringsEqual(String str1, String str2) {
        // first check if any strings are null
        if (str1 == null || str2 == null) {
            return false;
        }
        //convert both strings to lowercase and compare them
        return str1.toLowerCase().equals(str2.toLowerCase());
    }

    //to get a valid phone number(only 11 digits)
    public static String getCorrectPhoneNumber(Scanner scanner) {
        while (true) {//loop will run until correct input is received
            String input = scanner.nextLine(); //read the input
            if (matchHashPattern(input, "###########")) {  // Matches exactly 10 digits
                return input;// return the correct phone number
            }
            System.out.println("Invalid phone number. Please enter a 11-digit phone number.");
        }
    }

    // Method to get a valid CNIC format XXXXX-XXXXXXX-X
    public static String getCorrectCNIC(Scanner scanner) {
        while (true) {//loop will run until correct input is received
            String input = scanner.nextLine();//read the input
            if (matchHashPattern(input, "#####-#######-#")) {  //matches CNIC format XXXXX-XXXXXXX-X
                return input;
            }
            System.out.println("Invalid CNIC format. Please use the format XXXXX-XXXXXXX-X.");
        }
    }

    //method to manually match a pattern by #
    private static boolean matchHashPattern(String input, String pattern) {
        //checks if the length of input matches the pattern length
        if (input == null || input.length() != pattern.length()) {
            return false;//return false if lengths don't match
        }
//iterate through each character of the pattern and input
        for (int i = 0; i < pattern.length(); i++) {
            char currentPatternChar = pattern.charAt(i); //get current character in the pattern

            char currentInputChar = input.charAt(i);//get current character in the input


            //if= pattern character is digit ( #) check if the input is a digit
            if (currentPatternChar == '#') {
                if (!Character.isDigit(currentInputChar)) {
                    return false;//return false if the input is not a digit
                }
            }
            //if the pattern character is a character, check if the input matches it
            else if (currentPatternChar != currentInputChar) {
                return false;//returns false if characters do not match
            }
        }
        return true;//return true if all characters match # pattern
    }
}


