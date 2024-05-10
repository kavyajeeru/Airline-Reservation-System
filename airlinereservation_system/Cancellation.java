package airlinereservation_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Cancellation {
	private Connection connection;

	/* creating cancellation method */
	public Cancellation(Connection connection, Scanner scanner) {
		this.connection = connection;

	}

	public void cancelReservation(Scanner sc) throws SQLException { // cancel reservation method defined to cancel
																	// booking
		sc.nextLine();
		System.out.println("Enter your booking Name to cancel:"); // give name of the person to cancel booking
		String name = sc.nextLine();

		String cancelQuery = "DELETE FROM booking WHERE name = ? ";
		// delete command is used to cancel booking from booking table
		try (PreparedStatement statement = connection.prepareStatement(cancelQuery)) {
			statement.setString(1, name);
			int affectedRows = statement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Booking has been cancelled.");
			} else {
				System.out.println("Failed to cancel booking.");
			}
		}

	}
}