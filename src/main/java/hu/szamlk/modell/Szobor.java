package hu.szamlk.modell;

import java.text.Collator;
import java.util.Comparator;

public class Szobor extends Mutargy implements Comparable<Szobor>{

    private String anyag;
    private int szazad;

    public Szobor(Kategoria kategoria, String alkoto, String cim, String anyag) {
        this(kategoria, alkoto, cim, anyag, 20);
    }

    public Szobor(Kategoria kategoria, String alkoto, String cim, String anyag, int szazad) {
        super(kategoria, alkoto, cim);

        if (szazad > 21){
            throw new NemLetezoSzazadException("A század nem lehet nagyobb mint 21");
        }

        this.anyag = anyag;
        this.szazad = szazad;
    }

    public AnyagComparator getAnyagComparator(){
        return new AnyagComparator();
    }

    public SzazadComparator getSzazadComparator(){
        return new SzazadComparator();
    }

    @Override
    public String toString() {
        String os = super.toString();

        return os + " ---> " + "Szobor{" +
                "anyag='" + anyag + '\'' +
                ", szazad=" + szazad +
                '}';
    }

    @Override
    public int compareTo(Szobor masik) {
        Collator c = Collator.getInstance();
        return c.compare(this.getAlkoto(), masik.getAlkoto());
    }

    private class AnyagComparator implements Comparator<Szobor> {

        @Override
        public int compare(Szobor egyik, Szobor masik) {
            Collator c = Collator.getInstance();
            return c.compare(egyik.anyag, masik.anyag);
        }
    }

    private class SzazadComparator implements Comparator<Szobor> {

        @Override
        public int compare(Szobor egyik, Szobor masik) {
            if (egyik.szazad > masik.szazad){
                return 1;
            } else if (egyik.szazad < masik.szazad) {
                return -1;
            }

            return 0;
        }
    }
}
