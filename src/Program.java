import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {


        //stworzenie obiektu do interakcji z bazą
        DatabaseConnection dbconn = new DatabaseConnection();

        System.out.println("---1:");

        //przykład 1 - wyswietlanie calej tabeli pielęgniarek
        ResultSet rs = dbconn.executeSelectQuery("WSZYSTKIE_PIELEGNIARKI", null);
        dbconn.printWholeResultSet(rs);

        System.out.println("---2:");

        //przykład 2 - wyświetlanie lekarzy którzy nazywają się piotr
        ResultSet rs2 = dbconn.executeSelectQuery("LEKARZE_WG_IMIENIA", new String[]{"Piotr"});
        dbconn.printWholeResultSet(rs2);

        System.out.println("---3:");

        //przykład 3 - wyswietlanie lekarzy, ktorzy sa dostepni w poniedzialki od 10 do 19
        ResultSet rs3 = dbconn.executeSelectQuery("DOSTEPNOSC_LEKARZA_WG_IMIENIA_NAZWISKA_I_DNIA_TYGODNIA", new String[]{"Piotr", "Kot", "poniedziałek"});
        dbconn.printWholeResultSet(rs3);

        System.out.println("---4:");

        //Przykład 4 - tworzenie listy map na podstawie rezultatu zapytania
        List<Map<String, String>> list = dbconn.resultSetToArrayList(rs2);
        System.out.println(list.get(0).get("IMIE"));

        System.out.println("---5:");

        //Przykład 5 - tworzenie mapy list na podstawie rezultatu zapytania
        Map<String, List<Object>> map = dbconn.resultSetToMap(rs);
        System.out.println(map.get("IMIE").get(1));


        dbconn.closeConnection();
    }

}


