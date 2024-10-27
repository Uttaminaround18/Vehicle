package in.sp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import in.sp.bean.Underwriter;

public class UnderwriterDao {
	private String jdbcURL = "jdbc:sqlite:C:\\Users\\Admin\\MySQLiteDB";
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");


	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(jdbcURL);
			System.out.println("Connection successfull");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public UnderwriterDao() {
		// TODO Auto-generated constructor stub
		getConnection();
	}

	private static final String INSERT_Underwriter_SQL = "INSERT INTO underwriter" + "  (name, password, doj, dob) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_Underwriter_BY_ID = "select id,name,password,doj,dob from underwriter where id =?";
	private static final String SELECT_ALL_Underwriter = "select * from underwriter";
	private static final String DELETE_Underwriter_SQL = "delete from underwriter where id = ?;";
	private static final String UPDATE_Underwriter_SQL = "update underwriter set name = ?,password= ?, doj =? where id = ?;";

	public void insertUnderwriter(Underwriter Underwriter) throws SQLException {
		System.out.println(INSERT_Underwriter_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Underwriter_SQL)) {
			preparedStatement.setString(1, Underwriter.getName());
			preparedStatement.setString(2, Underwriter.getPassword());
			preparedStatement.setString(3, Underwriter.getDoj());
			preparedStatement.setString(4, Underwriter.getDob());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Underwriter selectUnderwriter(int id) {
		Underwriter underwriter = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Underwriter_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String doj = rs.getString("doj");
				String dob = rs.getString("dob");
				LocalDateTime dojj = LocalDateTime.parse(doj, df);
				LocalDateTime dobb = LocalDateTime.parse(dob, df);
				underwriter = new Underwriter(name, password, dojj, dobb);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return underwriter;
	}

	public List<Underwriter> selectAllUnderwriters() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Underwriter> Underwriters = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Underwriter);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String doj = rs.getString("doj");
				String dob = rs.getString("dob");
				LocalDateTime dojj = LocalDateTime.parse(doj, df);
				LocalDateTime dobb = LocalDateTime.parse(dob, df);
				Underwriters.add( new Underwriter(name, password, dojj, dobb));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Underwriters;
	}

	public boolean deleteUnderwriter(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_Underwriter_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUnderwriter(Underwriter underwriter) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_Underwriter_SQL);) {
			System.out.println("updated Underwriter:" + statement);
			statement.setString(1, underwriter.getName());
			statement.setString(2, underwriter.getPassword());
			statement.setString(3, underwriter.getDoj());
			statement.setInt(4, underwriter.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
