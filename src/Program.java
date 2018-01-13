import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Program {


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {


        //stworzenie obiektu do interakcji z bazą
        DatabaseConnection dbconn = new DatabaseConnection();

        //przykład 1 - wyswietlanie calej tabeli lekarzy
        ResultSet rs = dbconn.executeSelectQuery("WSZYSTKIE_PIELEGNIARKI", null);
        dbconn.printWholeResultSet(rs);

        System.out.println("---");

        //przykład 2 - wyświetlanie lekarzy którzy nazywają się piotr
        ResultSet rs2 = dbconn.executeSelectQuery("LEKARZE_WG_IMIENIA", new String[]{"Piotr"});
        dbconn.printWholeResultSet(rs2);

        System.out.println("---");

        //przykład 3 - wyswietlanie lekarzy, ktorzy sa dostepni w poniedzialki od 10 do 19
        ResultSet rs3 = dbconn.executeSelectQuery("DOSTEPNOSC_LEKARZA_WG_IMIENIA_NAZWISKA_I_DNIA_TYGODNIA", new String[]{"Piotr", "Kot", "poniedziałek"});
        dbconn.printWholeResultSet(rs3);


        dbconn.closeConnection();


    }

}


