import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gui.pakiet.*;
public class Program {


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        DatabaseConnection dbconn = new DatabaseConnection();

        PacjenciKonsultacjeController pkController = new PacjenciKonsultacjeController(dbconn);



        //dbconn.closeConnection();




}

}
