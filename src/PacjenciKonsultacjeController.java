import com.sun.xml.internal.bind.v2.model.core.ID;
import gui.pakiet.pacjenciKonsultacje;
import gui.pakiet.pacjenciKonsultacje2;
import gui.pakiet.pacjenciKonsultacje3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PacjenciKonsultacjeController {
    private DatabaseConnection dbconn;

    private CardLayout cardLayout;


    private pacjenciKonsultacje oknoPierwsze;
    private pacjenciKonsultacje2 oknoDrugie;
    private pacjenciKonsultacje3 oknoTrzecie;

    private JPanel cardPanel;

    private JFrame frame;

    private JButton searchPacjenciButton;
    private JButton wyszukajKonsultacjeOdbytePrzezButton;
    private JButton oknoDrugiePowrotButton;
    private JButton wczytajTeKonsultacjeButton;

    private JButton oknoTrzeciePowrotButton;
    private JButton wyszukajToBadanieButton;

    private JTable pacjenciTable;
    private JTable konsultacjeTable;
    private JTable receptyTable;
    private JTable badaniaTable;

    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JTextField peselTextField;
    private JTextPane opisTextPane;
    private JTextPane opisBadaniaTextPane;


    public PacjenciKonsultacjeController(DatabaseConnection dbconn) throws SQLException {

        this.dbconn = dbconn;
        initFrame();
        DateFormat df = new SimpleDateFormat();

        oknoPierwsze = new pacjenciKonsultacje();
        oknoDrugie = new pacjenciKonsultacje2();
        oknoTrzecie = new pacjenciKonsultacje3();

        initComponents();
        initListeners();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        frame.add(cardPanel);

        cardPanel.add(oknoPierwsze.getWyborPacjentaPanel(), "1");
        cardPanel.add(oknoDrugie.getMainPanel(), "2");
        cardPanel.add(oknoTrzecie.getKonsultacjeInfoPanel(), "3");

        cardLayout.show(cardPanel, "1");

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


        konsultacjeTable = oknoDrugie.getKonsultacjeTable();
        oknoDrugiePowrotButton = oknoDrugie.getPowrotButton();
        wczytajTeKonsultacjeButton = oknoDrugie.getWczytajTeKonsultacjeButton();

        oknoTrzeciePowrotButton = oknoTrzecie.getPowrotButton();
        wyszukajToBadanieButton = oknoTrzecie.getWyszukajToBadanieButton();
        receptyTable = oknoTrzecie.getReceptyTable();
        badaniaTable = oknoTrzecie.getBadaniaTable();
        opisTextPane = oknoTrzecie.getOpisTextPane();
        wyszukajToBadanieButton = oknoTrzecie.getWyszukajToBadanieButton();
        opisBadaniaTextPane = oknoTrzecie.getOpisBadaniaTextPane();
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
                            String idPacjenta = pacjenciTable.getModel().getValueAt(pacjenciTable.getSelectedRow(), 3).toString();
                            initDrugieOkno(idPacjenta);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Proszę wybrać pacjenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                }
        );


        oknoDrugiePowrotButton.addActionListener(
                e -> {
                    cardLayout.show(cardPanel, "1");
                });

        oknoTrzeciePowrotButton.addActionListener(
                e -> {
                    cardLayout.show(cardPanel, "2");
                });


        wczytajTeKonsultacjeButton.addActionListener(

                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String idKonsultacji = konsultacjeTable.getModel().getValueAt(konsultacjeTable.getSelectedRow(), 3).toString();
                            initTrzecieOkno(idKonsultacji);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Proszę wybrać konsultację", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                }


        );

        wyszukajToBadanieButton.addActionListener(

                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String IdBadania = badaniaTable.getModel().getValueAt(badaniaTable.getSelectedRow(), 2).toString();
                            fillOpisBadania(IdBadania);

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Proszę wybrać badanie", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                            ex.printStackTrace();
                        }
                    }


                });

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

    private void initDrugieOkno(String IdPacjenta) throws SQLException {
        fillKonsultacjeTable(IdPacjenta);
        cardLayout.show(cardPanel, "2");
    }

    private void initTrzecieOkno(String IdKonsultacji) throws SQLException {
        fillReceptyTable(IdKonsultacji);
        fillBadaniaTable(IdKonsultacji);
        fillOpis(IdKonsultacji);
        cardLayout.show(cardPanel, "3");
    }

    private void fillOpis(String IdKonsultacji) throws SQLException {
        ResultSet rs = dbconn.executeSelectQuery("OPIS_BY_ID_KONSULTACJI", new String[]{IdKonsultacji});
        List<Map<String, String>> opisy = dbconn.resultSetToArrayList(rs);
        String opisKonsultacji = opisy.get(0).get("OPIS");
        opisTextPane.setText(opisKonsultacji);
    }

    private void fillOpisBadania(String IdBadania) throws SQLException {
        ResultSet rs = dbconn.executeSelectQuery("WYNIK_BADANIA_BY_ID_ZLECONE_BADANIA", new String[]{IdBadania});
        List<Map<String, String>> opisy = dbconn.resultSetToArrayList(rs);
        try {
            String opisBadania = opisy.get(0).get("OPIS");
            opisBadaniaTextPane.setText(opisBadania);
        } catch (Exception ex) {
            opisBadaniaTextPane.setText("Brak dostępnego opisu badania.");
        }

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

    private void fillKonsultacjeTable(String IdPacjenta) throws SQLException {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Termin");
        columns.add("Lekarz");
        columns.add("Numer gabinetu");
        columns.add("id");

        ResultSet rs = dbconn.executeSelectQuery("KONSULTACJE_BY_ID_PACJENTA", new String[]{IdPacjenta});
        List<Map<String, String>> list = dbconn.resultSetToArrayList(rs);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        for (Map<String, String> mapa : list) {
            values.add(new String[]{dateFormat.format(mapa.get("DATA")), mapa.get("IMIE_LEKARZA") + " " + mapa.get("NAZWISKO_LEKARZA"), String.valueOf(mapa.get("ID_GABINETU")), String.valueOf(mapa.get("ID"))});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray()) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        konsultacjeTable.setModel(tableModel);

    }

    private void fillReceptyTable(String IdKonsultacji) throws SQLException {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("LEK");
        columns.add("DAWKOWANIE");
        columns.add("DO KIEDY");

        ResultSet rs = dbconn.executeSelectQuery("RECEPTY_BY_ID_KONSULTACJI", new String[]{IdKonsultacji});
        List<Map<String, String>> list = dbconn.resultSetToArrayList(rs);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        for (Map<String, String> mapa : list) {
            values.add(new String[]{mapa.get("NAZWA_LEKU"), mapa.get("DAWKOWANIE"), dateFormat.format(mapa.get("DO_KIEDY"))});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray()) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        receptyTable.setModel(tableModel);

    }

    private void fillBadaniaTable(String IdKonsultacji) throws SQLException {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Badanie");
        columns.add("Data");
        columns.add("nr badania");

        ResultSet rs = dbconn.executeSelectQuery("ZLECONE_BADANIA_BY_ID_KONSULTACJI", new String[]{IdKonsultacji});
        List<Map<String, String>> list = dbconn.resultSetToArrayList(rs);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        for (Map<String, String> mapa : list) {
            values.add(new String[]{mapa.get("RODZAJ_BADANIA"), dateFormat.format(mapa.get("DATA")), String.valueOf(mapa.get("ID"))});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray()) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        badaniaTable.setModel(tableModel);

    }

}
