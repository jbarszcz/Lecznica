package gui.pakiet;

import javax.swing.*;

public class pacjenciKonsultacje3 {
    private JPanel konsultacjeInfoPanel;
    private JTable badaniaTable;
    private JTable receptyTable;
    private JTextPane opisTextPane;
    private JPanel mainPanel;
    private JPanel opisKonsultacjiPanel;
    private JPanel receptyPanel;
    private JPanel badaniaPanel;
    private JButton powrotButton;
    private JButton wyszukajToBadanieButton;
    private JTextPane opisBadaniaTextPane;

    public JPanel getKonsultacjeInfoPanel() {
        return konsultacjeInfoPanel;
    }

    public JButton getPowrotButton() {
        return powrotButton;
    }

    public JButton getWyszukajToBadanieButton() {
        return wyszukajToBadanieButton;
    }

    public JTable getBadaniaTable() {

        return badaniaTable;
    }

    public JTable getReceptyTable() {

        return receptyTable;
    }

    public JTextPane getOpisTextPane() {
        return opisTextPane;
    }

    public JTextPane getOpisBadaniaTextPane() {
        return opisBadaniaTextPane;
    }
}
