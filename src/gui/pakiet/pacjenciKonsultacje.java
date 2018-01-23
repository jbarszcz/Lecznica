package gui.pakiet;

import javax.swing.*;

public class pacjenciKonsultacje {
    private JPanel wyborPacjentaPanel;
    private JPanel menuPanel;
    private JButton searchPacjenciButton;

    private JScrollPane listaPane;
    private JTable pacjenciTable;
    private JTextField nazwiskoTextField;
    private JTextField imieTextField;
    private JTextField peselTextField;
    private JButton wyszukajKonsultacjeOdbytePrzezButton;
    private JPanel mainPanel;

    public pacjenciKonsultacje() {

    }

    public JTable getPacjenciTable() {return pacjenciTable;}
    public JButton getSearchPacjenciButton() {return searchPacjenciButton;}
    public JButton getWyszukajKonsultacjeOdbytePrzezButton(){return wyszukajKonsultacjeOdbytePrzezButton;}

    public JTextField getImieTextField() {return imieTextField;}
    public JTextField getNazwiskoTextField() {return nazwiskoTextField;}
    public JTextField getPeselTextField() {return peselTextField;}

    public JPanel getMenuPanel() {return menuPanel;}
    public JPanel getWyborPacjentaPanel() {return wyborPacjentaPanel;}

    //public void addToPacjenciList(String pacjent){




}
