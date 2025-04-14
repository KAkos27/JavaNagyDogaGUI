package hu.szamlk.nezet;

import hu.szamlk.modell.Kategoria;
import hu.szamlk.modell.Mutargy;
import hu.szamlk.modell.Szobor;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Grafikus {
    private JComboBox comboBox1;
    private JPanel mainPanel;
    private JButton felvesz;
    private JList lista;
    private JButton mozgat;
    private JMenuItem beolvas;
    private JMenuItem kilepes;

    public Grafikus(){
        ini();
        beolvasEsemeny();
        kilepesEsemeny();
        felveszEsemeny();
        mozgatEsemeny();
    }

    private void ini() {
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);

        beolvas = new JMenuItem("Beolvas");
        kilepes = new JMenuItem("Kilépés");

        JMenu jMenu = new JMenu("Menü");

        jMenu.add(beolvas);
        jMenu.add(new JSeparator());
        jMenu.add(kilepes);

        JMenuBar jmb = new JMenuBar();
        jmb.add(jMenu);

        frame.setJMenuBar(jmb);
        frame.revalidate();
    }

    private void beolvasEsemeny(){
        beolvas.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);


            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()))){

                ArrayList<Mutargy> mutargyak = (ArrayList<Mutargy>) ois.readObject();

                mutargyak.forEach(mutargy -> {
                    String osztaly = mutargy instanceof Szobor ? "szobor" : "festmény";
                    String szoveg = mutargy.getAlkoto() + " (" + osztaly + ")";
                    comboBox1.addItem(szoveg);
                });

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void kilepesEsemeny(){
        kilepes.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(null, JOptionPane.OK_CANCEL_OPTION) == 0){
                System.exit(0);
            }
        });
    }

    private void felveszEsemeny() {
        felvesz.addActionListener(e -> {
            comboBox1.addItem(new Szobor(Kategoria.EREDETI, "Valaki", "Cim", "Anyag").getAlkoto() + " (szobor)");
        });
    }

    private void mozgatEsemeny() {
        mozgat.addActionListener(e -> {
            lista.add((Component) comboBox1.getItemAt(comboBox1.getSelectedIndex()));
        });
    }

    public static void main(String[] args) {
        new Grafikus();
    }
}
