import gui.pakiet.pacjenciKonsultacje;
import gui.pakiet.pacjenciKonsultacje2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PacjenciKonsultacjeController {
    private DatabaseConnection dbconn;

    private CardLayout cardLayout;



    private pacjenciKonsultacje oknoPierwsze;
    private pacjenciKonsultacje2 oknoDrugie;

    private JPanel cardPanel;

    private JFrame frame;

    private JButton searchPacjenciButton;
    private JButton wyszukajKonsultacjeOdbytePrzezButton;
    private JTable pacjenciTable;

    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JTextField peselTextField;


    public PacjenciKonsultacjeController(DatabaseConnection dbconn) throws SQLException {

        this.dbconn = dbconn;
        initFrame();

        oknoPierwsze = new pacjenciKonsultacje();
        oknoDrugie = new pacjenciKonsultacje2();

        initComponents();
        initListeners();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        frame.add(cardPanel);
        cardPanel.add(oknoPierwsze.getWyborPacjentaPanel(),"1" );
        cardPanel.add(oknoDrugie.getPanel1(), "2");

        cardLayout.show(cardPanel,"1");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fillPacjenciTable("", "", "");
        frame.setVisible(true);


    }


    private void initComponents() {

        searchPacjenciButton = oknoPierwsze.getSearchPacjenciButton();
        wyszukajKonsultacjeOdbytePrzezButton = oknoPierwsze.getWyszukajKonsultacjeOdbytePrzezButton();
        pacjenciTable = oknoPierwsze.getPacjenciTable();
        imieTextField = oknoPierwsze.getImieTextField();
        nazwiskoTextField = oknoPierwsze.getNazwiskoTextField();
        peselTextField = oknoPierwsze.getPeselTextField();


    }

    private void initListeners() {

        searchPacjenciButton.addActionListener(e -> {

            try {
                fillPacjenciTable(imieTextField.getText(), nazwiskoTextField.getText(), peselTextField.getText());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });


        wyszukajKonsultacjeOdbytePrzezButton.addActionListener(

                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            System.out.println(pacjenciTable.getModel().getValueAt(pacjenciTable.getSelectedRow(), 3));
                            cardLayout.show(cardPanel,"2");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Proszę wybrać pacjenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
        );
    }

    private void initFrame() {
        frame = new JFrame("WBD: Lecznica");

        frame.setSize(1000, 500);

        // przed zamknięciem okna odłączamy połączenie z bazą
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    dbconn.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void fillPacjenciTable(String imie, String nazwisko, String pesel) throws SQLException {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Nazwisko");
        columns.add("Imię");
        columns.add("Pesel");
        columns.add("ID");

        ResultSet rs = dbconn.executeSelectQuery("PACJENCI_BY_IMIE_NAWISKO_PESEL", new String[]{imie, nazwisko, pesel});

        List<Map<String, String>> list = dbconn.resultSetToArrayList(rs);
        rs.close();
        for (Map<String, String> mapa : list) {

            values.add(new String[]{mapa.get("NAZWISKO"), mapa.get("IMIE"), mapa.get("PESEL"), String.valueOf(mapa.get("ID"))});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray()) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        pacjenciTable.setModel(tableModel);

    }
}
