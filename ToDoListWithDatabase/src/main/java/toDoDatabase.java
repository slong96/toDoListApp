import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class toDoDatabase {
    private static final String DB_CONNECTION_URL = "jdbc:sqlite:todolist.sqlite";
    private static final String createTable = "CREATE TABLE IF NOT EXISTS to_do_list (to_do CHAR(255))";
    private static final String addSQL = "INSERT INTO to_do_list VALUES(?)";
    private static final String deleteSQL = "DELETE FROM to_do_list WHERE to_do = ?";
    private static final String selectAllSQL = "SELECT * FROM to_do_list";

    toDoDatabase() {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createTable);

        } catch (SQLException e) {
            System.out.println("Error creating database because " + e);
        }
    }

    public void addToDo(toDo tDL) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(addSQL)) {

            preparedStatement.setString(1, tDL.getToDo());
            preparedStatement.execute();


        } catch (SQLException e) {
            System.out.println("Error adding To-Do to list because " + e);
        }
    }

    public void deleteToDo(toDo tDL) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setString(1, tDL.getToDo());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Error deleting To-Do because " + e);
        }
    }

    public List<toDo> getAllToDo() {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
        Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(selectAllSQL);
            List<toDo> getAll = new ArrayList<>();

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
