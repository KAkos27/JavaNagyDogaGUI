package hu.szamlk.modell;

import java.io.Serializable;
import java.util.UUID;

public class Mutargy implements Serializable {

    private transient UUID uuid;
    private Kategoria kategoria;
    private String alkoto;
    private String cim;

    public Mutargy(Kategoria kategoria, String alkoto, String cim){
        this.kategoria = kategoria;
        this.alkoto = alkoto;
        this.cim = cim;
        setUuid();
    }

    public String getAlkoto() {
        return alkoto;
    }

    private void setUuid(){
        uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Mutargy{" +
                ", kategoria=" + kategoria +
                ", alkoto='" + alkoto + '\'' +
                ", cim='" + cim + '\'' +
                '}';
    }
}
