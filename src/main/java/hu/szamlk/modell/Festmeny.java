package hu.szamlk.modell;

public class Festmeny extends Mutargy{

    private String stilus;
    private String technika;

    public Festmeny(String stilus, String technika, Kategoria kategoria, String alkoto, String cim) {
        super(kategoria, alkoto, cim);
        this.stilus = stilus;
        this.technika = technika;
    }

    public String getStilus() {
        return stilus;
    }

    public String getAlkoto(){
        return super.getAlkoto();
    }

    @Override
    public String toString() {
        String os = super.toString();

        return os + " ---> " + "Festmeny{" +
                "stilus='" + stilus + '\'' +
                ", technika='" + technika + '\'' +
                '}';
    }
}
