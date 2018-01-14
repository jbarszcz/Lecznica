import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY); // Te parametry pozwalają się cofać w ResultSet

        return stmt.executeQuery(query);

    }

    //metoda do modyfikowania bazy (INSERT, UPDATE, itd)
    public int executeUpdateQuery(String queryName, String[] args) throws SQLException {


        String query = String.format(queries.getProperty(queryName), args);
        Statement stmt = conn.createStatement();

        return stmt.executeUpdate(query);
    }

    //metoda do generowania nowego ID przy wstawianiu. Używajmy jej za każdym razem gdy wstawiamy nowe wiersze żeby wszystko było ok.
    public String getNextId(String tableName) throws SQLException {

        ResultSet rs = executeSelectQuery("POBIERZ_LICZBE_WIERSZY", new String[]{tableName});
        rs.next();
        return "" + (rs.getInt(1) + 1);
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

    //metoda zamieniająca ResultSet na listę map (modelujemy w ten sposób tabelę jako obiekt w Javie)
    public List<Map<String, String>> resultSetToArrayList(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList(50);
        while (rs.next()) {
            HashMap row = new HashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }


        return list;

    }

    //metoda zamieniająca ResultSet na mapę list
    Map<String, List<Object>> resultSetToMap(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        Map<String, List<Object>> map = new HashMap<>(columns);
        for (int i = 1; i <= columns; ++i) {
            map.put(md.getColumnName(i), new ArrayList<>());
        }
        while (rs.next()) {
            for (int i = 1; i <= columns; ++i) {
                map.get(md.getColumnName(i)).add(rs.getObject(i));
            }
        }

        return map;
    }
}