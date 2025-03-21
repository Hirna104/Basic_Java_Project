// TODO -- create header comments

import java.util.Scanner;

// TODO -- copy the Booking2 class from assignment one part A and rename the class to Booking and rename the file to Booking.java
// TODO -- implement the toString method in the Booking class

public class YeppoonCabinsMenu {
	final int ENTER_BOOKING = 1;
	final int DISPLAY_BOOKINGS = 2;
	final int DISPLAY_STATISTICS = 3;
	final int SEARCH_BOOKINGS = 4;
	final int EXIT = 5;

	// TODO -- declare any further constants

	final int MAX_BOOKINGS = 10;

	// TODO -- declare array of Booking objects

	Booking[] bookings = new Booking[MAX_BOOKINGS];

	// TODO -- declare variable for the current booking entered (integer)

	private int pointer = 0;

	Scanner inputMenuChoice = new Scanner(System.in);

	private int getMenuItem() {
		System.out.println("\nPlease select from the following");
		System.out.println(ENTER_BOOKING + ". Enter booking name and number of nights");
		System.out.println(DISPLAY_BOOKINGS + ". Display all booking names, number of nights and charges");
		System.out.println(DISPLAY_STATISTICS + ". Display Statistics");
		System.out.println(SEARCH_BOOKINGS + ". Search for booking");
		System.out.println(EXIT + ". Exit the application");
		System.out.print("\nEnter choice==> ");

		String choice = inputMenuChoice.nextLine();

		while (choice.equals("") || !isStringNumeric(choice)) {
			System.out.println("Error - Menu selection name cannot be blank and must be numeric");

			System.out.print("\nEnter choice==> ");

			choice = inputMenuChoice.nextLine();
		}

		return Integer.parseInt(choice);
	}

	private boolean isStringNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}

		return true;
	}

	private void processOrders() {
		int choice = getMenuItem();
		while (choice != EXIT) {
			switch (choice) {
				case ENTER_BOOKING:
					enterBooking();
					break;
				case DISPLAY_BOOKINGS:
					displayAllBookings();
					break;
				case DISPLAY_STATISTICS:
					displayStatistics();
					break;
				case SEARCH_BOOKINGS:
					searchBookings();
					break;
				default:
					System.out.println("ERROR choice not recognised");
			}
			choice = getMenuItem();
		}
	}

	private void enterBooking() {
		// TODO -- check if maximum bookings has been reached (do this after getting the
		// other functionality working)
		if (pointer == MAX_BOOKINGS) {
			System.out.println("Error - maximum bookings to be entered has been reached");
		} else {

			Scanner sc = new Scanner(System.in);
			// TODO -- read in the booking name (as a string)
			System.out.print("\nPlease enter the booking name ==> ");
			String name = sc.nextLine(); 
			// TODO -- create validation loop (do this after getting the other functionality working)
			while (name.equals("")) {
				System.out.println("\nError - Booking name cannot be blank");
				System.out.print("\nPlease enter the booking name ==> ");
				name = sc.nextLine(); 
			}

			// TODO -- read in the number of nights
			System.out.print("\nPlease enter number of nights ==> ");
			int nights = sc.nextInt(); 
			// TODO -- create validation loop (do this after getting the other functionality working)
			while (!(nights >= 1)){
				System.out.println("\nError - Number of nights must be atleast 1");
				System.out.print("\nPlease enter number of nights ==> ");
				nights = sc.nextInt(); 
			}

			// TODO -- add the data to the array (use the new keyword and the parameterised constructor in Booking class)
			Booking booking = new Booking(name,nights);
			bookings[pointer] = booking;
			
			displayHeading();
			
			// TODO -- display the booking name, number of nights and the charge
			System.out.print(booking);

			// TODO -- increment the current booking variable for the next entry
			pointer++;


		}
	}

	private void displayHeading() {
		System.out.printf("%-30s%-11s%-6s\n", "Booking Name", "Nights", "Charge");
	}

	private void displayAllBookings() {
		// TODO -- check if there has been an booking entered (do this after getting the other functionality working)
		if(pointer == 0){
			System.out.println("Error - you must enter at least one booking");
		}else{

			// TODO -- display all of the entries entered so far (just display the current entries not the whole array, use the current booking variable as the termination condition)
			displayHeading();

			for (int i = 0; i < pointer; i++) {
				System.out.println(bookings[i]);
			}
		}
	}
	
	private void displayStatistics() {
		// TODO -- check if there has been an booking entered (do this after getting the other functionality working)
		if(pointer == 0){
			System.out.println("Error - you must enter at least one booking");
		}else{
			// TODO -- loop though the current entries in the array and calculate and display the statistics
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			String minCustomer = "";
    		String maxCustomer = "";
    		int totalNights = 0;
    		double totalCharges = 0;

			for (int i = 0; i < pointer; i++) {
				//for minmum booking
				if(bookings[i].getNightsStay() < min){
					min = bookings[i].getNightsStay();
					minCustomer = bookings[i].getBookingName();
				}

				//for maximum booking
				if(bookings[i].getNightsStay() > max){
					max = bookings[i].getNightsStay();
					maxCustomer = bookings[i].getBookingName();
				}

				totalNights += bookings[i].getNightsStay();
      			totalCharges += bookings[i].calculateCharge();
			}

			double averageNights = (double) totalNights / pointer;

			System.out.println(maxCustomer + " has the maximum number of " + max + " nights");
			System.out.println(minCustomer + " has the minimum number of " + min + " nights");
			
    		System.out.println("\n\nAverage number of nights per booking is " + averageNights);
    		System.out.printf("\nThe total charges are: $%.2f", totalCharges);

		}
	}

	private void searchBookings() {
		// TODO -- check if there has been an booking entered (do this after getting the other functionality working)
		if(pointer == 0){
			System.out.println("Error - you must enter at least one booking");
		}else{

			Scanner sc = new Scanner(System.in);
			
			// TODO -- read the search key
			System.out.print("\nPlease Enter the booking name ==> ");
			String search = sc.nextLine();
			
			// TODO -- loop though the current entries in the array to search for the search key
			for (int i = 0; i < pointer; i++) {
				if(search.equals(bookings[i].getBookingName())){
					System.out.println("\nBooking found");
					displayHeading();
					System.out.println(bookings[i]);
					return;
				}
			}

			System.out.println(search + " \nNot found");

			
			// TODO -- display the found item or report it has not been found
		}
	}

	public static void main(String[] args) {
		System.out.println("\n\nWelcome to the Yeppoon Cabins Management System");
		YeppoonCabinsMenu app = new YeppoonCabinsMenu();
		
		app.processOrders();

		System.out.println("\n\nThank you for using the Yeppoon Cabins Management System");
		System.out.println("\n\nProgram written by [your name & ID]\n");
	}
}