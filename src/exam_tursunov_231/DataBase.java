package exam_tursunov_231;

import java.sql.*;

public class DataBase {
    private final String host = "localhost";
    private final String port = "5432";
    private final String dbName = "db_orders";
    private final String login = "postgres";
    private final String password = "";

    private Connection dbCon;

    private Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        dbCon = DriverManager.getConnection(str, login, password);
        return dbCon;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbCon = getDBConnection();
        if (dbCon.isValid(1000)) {
            System.out.println("Connect");
        } else {
            System.out.println("Error");
        }
    }

    public void createDbOrders(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (order_id INT PRIMARY KEY, customer_name VARCHAR(50), total_amount NUMERIC(10,2));";
        Statement statement = getDBConnection().createStatement();
        statement.executeUpdate(sql);
    }

    public void addOrder(String table, int orderId, String customerName, double totalAmount) {
        try {
            Statement statement = getDBConnection().createStatement();
            int rows = statement.executeUpdate("INSERT INTO " + table + "(order_id, customer_name, total_amount) " +
                    "VALUES (" + orderId + ", '" + customerName + "', '" + totalAmount + "');");
            System.out.println("Заказ добавлен!!!\n");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error");
        }
    }

    public void selectAllOrders(String tableName) throws SQLException, ClassNotFoundException {
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from " + tableName);
        while (resultSet.next()) {
            int orderId = resultSet.getInt(1);
            String customerName = resultSet.getString(2);
            double totalAmount = resultSet.getDouble(3);
            System.out.printf("%d. %s. %.2f \n", orderId, customerName, totalAmount);
        }
    }

    public void selectOrdersAboveAmount(String tableName, double amount) throws SQLException, ClassNotFoundException {
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from " + tableName + " WHERE total_amount > " + amount);
        while (resultSet.next()) {
            int orderId = resultSet.getInt(1);
            String customerName = resultSet.getString(2);
            double totalAmount = resultSet.getDouble(3);
            System.out.printf("%d. %s. %.2f \n", orderId, customerName, totalAmount);
        }
    }
}
