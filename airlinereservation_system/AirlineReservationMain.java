package airlinereservation_system;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class AirlineReservationMain {
	private static final String URL = "jdbc:mysql://localhost:3306/airline";
	// airline is the database name which is created in the mysql
	private static final String USERNAME = "root"; // the user name of mysql
	private static final String PASSWORD = "root"; // the password of mysql

	public static void main(String[] args) throws IOException {
		try {
			// to connect with the mysql using jar files
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			connection.createStatement();
			// creates objects for all the classes
			Scanner scanner = new Scanner(System.in);
			User user = new User(connection, scanner);
			Reservation reservation = new Reservation(connection, scanner);
			Booking booking = new Booking(connection, scanner);
			Cancellation cancellation = new Cancellation(connection, scanner);
			String email = null;
			// using while with switch to run loop
			while (true) {
				System.out.println("**  WELCOME TO AIRLINE RESERVATION SYSTEM  **");
				System.out.println();
				System.out.println("1.USER");
				System.out.println("2.RESERVATION");
				System.out.println("3.BOOKING");
				System.out.println("4.CANCELLATION");
				System.out.println("5.EXIT");
				System.out.println("ENTER YOUR CHOICE");
				int choice = scanner.nextInt(); // created choice variable to help switch
				switch (choice) {
				case 1:
					user.Register(); // calling register method in user class
					System.out.println("\033[H\033[2J");
					System.out.flush();
					break;
				case 2: // calling login method in user class to do reservation
					email = user.Login();
					if (email != null) { // when this condition becomes true it jumps to the inner while loop
						System.out.println("USER LOGGED IN .. ");

						if (!user.User_exist(email)) { // when this condition becomes true it lets to register before
		
							System.out.println();
							System.out.println("1. REGISTER ");
							System.out.println("2. EXIT ");
							System.out.println("ENTER YOUR CHOICE");
							if (scanner.nextInt() == 1) {
								user.Register();
								System.out.println("REGISTRATION SUCCESS");

							} else {
								break;
							}
						}

						int choice2 = 0;
						while (choice2 != 3) { // when user got login the inner while loop starts working
							System.out.println();
							System.out.println("1.RESERVATION");
							System.out.println("2.VIEW RESERVATION");
							System.out.println("3.FLIGHT DETAILS");
							System.out.println("4.EXIT");
							System.out.println("ENTER YOUR CHOICE ");
                            
								choice2 = scanner.nextInt();
							
							switch (choice2) {
							case 1:
								reservation.makeReservation(scanner);
							// make reservation method
								break;
							case 2:
								reservation.viewReservation(); // views the reservation details
								
								break;
							case 3:
								reservation.viewFlightDetails();// views the flight details
								break;
							case 4:
								break;
							default:
								System.out.println("ENTER VALID CHOICE");

							}
						}
					}
					break;
				case 3:
					booking.booking(); // calls booking method
					System.out.println("THANK YOU FOR BOOKING, HAVE A NICE JOURNEY");
					break;

				case 4:
					cancellation.cancelReservation(scanner); // calls cancel reservation
					break;

				case 5:
					System.out.println("Exiting..."); // exiting from the program
					System.out.println("THANK YOU FOR USING AIRLINE RESERVATION SYSTEM");
					return;
					
				default:
					System.out.println("Invalid choice! Please enter a valid option.");
					break;
				}
				
			 }
			
		   }   catch (SQLException e) {
			e.printStackTrace();
		}
	}
}