package hu.szamlk.nezet;

import hu.szamlk.modell.Festmeny;
import hu.szamlk.modell.Kategoria;
import hu.szamlk.modell.Mutargy;
import hu.szamlk.modell.Szobor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Konzol {

    private List<Mutargy> mutargyak;

    public Konzol() {
        this.mutargyak = new ArrayList<>();

        mutargyak.add(new Szobor(Kategoria.EREDETI, "Michelangelo", "Dávid", "Márvány", 16));
        mutargyak.add(new Szobor(Kategoria.EREDETI, "Rodin", "A gondolkodó", "Bronz", 19));

        mutargyak.add(new Festmeny("impresszinoista", "szén", Kategoria.EREDETI, "Monet", "Tavirózsák"));
        mutargyak.add(new Festmeny("impresszinoista", "festék", Kategoria.EREDETI, "Renoir", "Fürdőzők"));
        mutargyak.add(new Festmeny("szürrealista", "vegyes", Kategoria.EREDETI, "Dali", "Hattyúk"));
        mutargyak.add(new Festmeny("kubista", "szén", Kategoria.EREDETI, "Magritte", "Az ember fia"));
    }

    public void konzolraIr(){
        mutargyak.forEach(System.out::println);
    }

    public void fajlbaIr(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mutargyak.dat"))){

            oos.writeObject(mutargyak);

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void statisztika(){
        HashMap<String, String> stat = new HashMap<>();

        for (Mutargy mutargy : mutargyak) {
            if (mutargy instanceof Festmeny){
                if (stat.containsKey(((Festmeny) mutargy).getStilus())){
                    String alk = stat.get(((Festmeny) mutargy).getStilus());
                    stat.remove(((Festmeny) mutargy).getStilus());
                    stat.put(((Festmeny) mutargy).getStilus(), alk + ", " + mutargy.getAlkoto());
                } else {
                    stat.put(((Festmeny) mutargy).getStilus(), mutargy.getAlkoto());
                }
            }
        }

        System.out.println(stat);
    }
}
