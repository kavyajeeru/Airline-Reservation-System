package airlinereservation_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/* creating booking method to book tickets */
public class Booking {
	private Connection connection;
	private Scanner scanner;

	public Booking(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}

	public void booking() { // booking method is defined

		scanner.nextLine();
		System.out.println("name");
		String name = scanner.nextLine();
		System.out.println("flightnumber");
		String flightnumber = scanner.nextLine();
		System.out.println("seatno");
		int seatno = Integer.parseInt(scanner.nextLine());
		// inserting details in the table called booking
		String bookingQuery = "INSERT INTO Booking (name, flightnumber, seatno) VALUES (?, ?, ?)";

		try { // using try-catch block to avoid any errors
			PreparedStatement preparedStatement = connection.prepareStatement(bookingQuery);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, flightnumber);
			preparedStatement.setInt(3, seatno);
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) { // checks whether the rows affected
				System.out.println("BOOKING TOOK PLACE");
			} else {
				System.out.println("PLEASE TRY AGAIN");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
