package airlinereservation_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/* creating methods called register ,login and user_exist*/
public class User {

	private Connection connection;
	private Scanner scanner;

	public User(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}

	public void Register() { // defining register
		scanner.nextLine();

		System.out.println("Name");
		String name = scanner.nextLine();
		System.out.println("Email");
		String email = scanner.nextLine();
		System.out.println("Password");
		String password = scanner.nextLine();
		if (User_exist(email)) {
			System.out.println("User already exists for this email address");
			return;
		}
		String Register_query = "INSERT INTO User (name ,email , password) VALUES (?,?,?)";
		// inserting user input values into a table name called user
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Register_query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			int affectedRows = preparedStatement.executeUpdate();// updates the values into a table
			if (affectedRows > 0) {
				System.out.println("REGISTRATION SUCCESSFUL");
			} else {
				System.out.println("REGISTRATION FAILED");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean User_exist(String email) { // checks whether the user is exist before
		String query = "SELECT * FROM User WHERE email = ?"; // using select command to check
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultset = preparedStatement.executeQuery();
			return resultset.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public String Login() { // creating login method to login when the user is existed before
		scanner.nextLine();
		System.out.println("Email");
		String email = scanner.nextLine();
		System.out.println("Password");
		String password = scanner.nextLine();
		String Login_query = "SELECT * FROM User WHERE Email = ? AND Password = ?";// checks for the user
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(Login_query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				return email;
			} else {
				return null;
			}
		} catch (SQLException e) { // catches the exceptions
			e.printStackTrace();
			return null;
		}
	}

}