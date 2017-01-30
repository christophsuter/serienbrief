package ch.noser.schule.api;

import java.time.LocalDate;

/**
 * Created by christoph.suter on 29.01.2017.
 */
public class Lehrer extends Person {

    private final String klassenId;

    public Lehrer(String vorname, String nachname, LocalDate gebrutstag, String klassenId) {
        super(vorname, nachname, gebrutstag);
        this.klassenId = klassenId;
    }

    public String getKlassenId() {
        return klassenId;
    }

    @Override
    public String toString() {
        return "Lehrer{" +
                "name='" + getVorname() + " " + getNachname() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Lehrer lehrer = (Lehrer) o;

        return klassenId != null ? klassenId.equals(lehrer.klassenId) : lehrer.klassenId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (klassenId != null ? klassenId.hashCode() : 0);
        return result;
    }
}

