package airlinereservation_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* create methods called make reservation view reservation and view flight_details in the reservation class*/
public class Reservation {

	private Connection connection;
// parameterized constructor

	public Reservation(Connection connection, Scanner scanner) {
		this.connection = connection;
	}

	public String makeReservation(Scanner sc ) throws SQLException { // creating method called make reservation to reserve

			System.out.println("Enter Name:");
			String name = sc.nextLine();
			sc.nextLine();

			System.out.println("Enter gender:");
			String gender = sc.nextLine();

			System.out.println("Enter age:");
			int age = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter email:");
			String email = sc.nextLine();

			System.out.println("Enter mobileno:");
			String mobileno = sc.nextLine();

			System.out.println("Enter address:");
			String address = sc.nextLine();
             
			String insertDataSQL = "INSERT INTO reserve (name, gender, age, email, mobileno, address) VALUES ( ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
				// inserting user input values in the table named reserve
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, gender);
				preparedStatement.setInt(3, age);
				preparedStatement.setString(4, email);
				preparedStatement.setString(5, mobileno);
				preparedStatement.setString(6, address);
				int affectedRows = preparedStatement.executeUpdate();
				if (affectedRows > 0) { // checks whether the rows affected
					System.out.println("Reservation successful!");
				} else {
					System.out.println("Reservation unsuccessful.");
				}
			}

		return null;
	}

	public void viewReservation() { // view the reservation method
		try {
			String selectReserveSQL = "SELECT * FROM reserve";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(selectReserveSQL)) {

				System.out.println("***RESERVATION DETAILS***");

				while (resultSet.next()) {
					String id = resultSet.getString("id");
					String Name = resultSet.getString("Name");
					String gender = resultSet.getString("gender");
					int age = resultSet.getInt("age");
					String Email = resultSet.getString("Email");
					String MobileNo = resultSet.getString("MobileNo");
					String Address = resultSet.getString("Address");

					System.out.println("ID: " + id);
					System.out.println("NAME: " + Name);
					System.out.println("GENDER: " + gender);
					System.out.println("AGE: " + age);
					System.out.println("EMAIL: " + Email);
					System.out.println("MOBILE NO: " + MobileNo);
					System.out.println("ADDRESS: " + Address);
					System.out.println("-----------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void viewFlightDetails() { // static flight_details
		try {
			String selectDataSQL = "SELECT * FROM flight_details";
			try (Statement statement = connection.createStatement(); // use of statement because of static data
					ResultSet resultSet = statement.executeQuery(selectDataSQL)) {

				System.out.println(" **FLIGHT DETAILS **");
				while (resultSet.next()) {
					String flightName = resultSet.getString("FlightName");
					String flightNumber = resultSet.getString("flightNumber");
					String departureCity = resultSet.getString("departureCity");
					String arrivalCity = resultSet.getString("arrivalCity");
					String departureTime = resultSet.getString("departureTime");
					String arrivalTime = resultSet.getString("arrivalTime");
					int seatAvailability = resultSet.getInt("seatAvailability");

					System.out.println("Flight Name: " + flightName);
					System.out.println("Flight Number: " + flightNumber);
					System.out.println("Departure City: " + departureCity);
					System.out.println("Arrival City: " + arrivalCity);
					System.out.println("Departure Time: " + departureTime);
					System.out.println("Arrival Time: " + arrivalTime);
					System.out.println("Seat Availability: " + seatAvailability);
					System.out.println("-----------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
