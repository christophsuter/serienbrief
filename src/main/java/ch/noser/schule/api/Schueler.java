package ch.noser.schule.api;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by christoph.suter on 29.01.2017.
 */
public class Schueler extends Person {


    public final String klassenId;
    public final List<ErziehungsberechtigtePerson> erziehungsberechtigtePersonen;

    public Schueler(String vorname, String nachname, LocalDate gebrutstag, String klassenId, List<ErziehungsberechtigtePerson> erziehungsberechtigtePersonen) {
        super(vorname, nachname, gebrutstag);
        this.klassenId = klassenId;
        this.erziehungsberechtigtePersonen = erziehungsberechtigtePersonen;
    }

    public String getKlassenId() {
        return klassenId;
    }

    public List<ErziehungsberechtigtePerson> getErziehungsberechtigtePersonen() {
        return erziehungsberechtigtePersonen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Schueler schueler = (Schueler) o;

        return klassenId != null ? klassenId.equals(schueler.klassenId) : schueler.klassenId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (klassenId != null ? klassenId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Schueler{" +
                "name='" + getVorname() + " " + getNachname() + '\'' +
                '}';
    }

}
