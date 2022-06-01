import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class toDoDatabase {
    // SQL Database
    private static final String DB_CONNECTION_URL = "jdbc:sqlite:todolist.sqlite";
    // SQL query to create table
    private static final String createTable = "CREATE TABLE IF NOT EXISTS to_do_list (to_do CHAR(255))";
    // SQL query to insert value
    private static final String addSQL = "INSERT INTO to_do_list VALUES(?)";
    // SQL query to delete a to-do text
    private static final String deleteSQL = "DELETE FROM to_do_list WHERE to_do = ?";
    // SQL query to select all from table
    private static final String selectAllSQL = "SELECT * FROM to_do_list";

    // constructor
    toDoDatabase() {

        // create table
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createTable);

        } catch (SQLException e) {
            System.out.println("Error creating database because " + e);
        }
    }

    public void addToDo(toDo tDL) {

        // insert/add value to table
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(addSQL)) {

            preparedStatement.setString(1, tDL.getToDo());
            preparedStatement.execute();


        } catch (SQLException e) {
            System.out.println("Error adding To-Do to list because " + e);
        }
    }

    public void deleteToDo(toDo tDL) {

        // delete to-do text from table
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setString(1, tDL.getToDo());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Error deleting To-Do because " + e);
        }
    }

    public List<toDo> getAllToDo() {

        // select all from table
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
        Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(selectAllSQL);
            // new array list
            List<toDo> getAll = new ArrayList<>();

            // while loop
            while (resultSet.next()) {
                String text = resultSet.getString("to_do");
                toDo tDL = new toDo(text);
                getAll.add(tDL);
            }
            return getAll;

        } catch (SQLException e) {
            System.out.println("Error getting all To-Do text because " + e);
            return null;
        }
    }
}
