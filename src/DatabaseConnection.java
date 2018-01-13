import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

class DatabaseConnection {
    private Connection conn;
    private String connectionURL = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private String user = "jbarszcz";
    private String password = "jbarszcz";
    private Properties queries = new Properties();

    DatabaseConnection() throws ClassNotFoundException, SQLException, IOException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        connectToDb();


        queries.load(new FileInputStream("src/config.properties"));


    }

    private void connectToDb() throws SQLException, ClassNotFoundException {

        conn = DriverManager.getConnection(connectionURL, user, password);
        System.out.println("\nConnected to database");

    }

    void closeConnection() throws SQLException {
        conn.close();
        System.out.println("\nConnection with database closed");
    }

    //Odpowiada za wykonanie zapytania Select.
    public ResultSet executeSelectQuery(String queryName, String[] args) throws SQLException {
        String query = String.format(queries.getProperty(queryName), args);
        Statement stmt = conn.createStatement();

        return stmt.executeQuery(query);

    }

    //Do testów - Wyswietla wszystkie dane tabeli ta podstawie danego wyniku zapytania
    void printWholeResultSet(ResultSet rs) throws SQLException {

        int numberOfColumns = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= numberOfColumns; i++) {

                String value = rs.getString(i);
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }


}