package ch.noser.schule.api;

import java.time.LocalDate;

/**
 * Created by christoph.suter on 29.01.2017.
 */
public class ErziehungsberechtigtePerson extends Person {

    public ErziehungsberechtigtePerson(String vorname, String nachname, LocalDate gebrutstag) {
        super(vorname, nachname, gebrutstag);
    }

    @Override
    public String toString() {
        return "ErziehungsberechtigtePerson{" +
                "name='" + getVorname() + " " + getNachname() + '\'' +
                "}";
    }
}
