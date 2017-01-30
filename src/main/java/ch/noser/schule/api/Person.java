package ch.noser.schule.api;

import java.time.LocalDate;

/**
 * Created by christoph.suter on 29.01.2017.
 */
public class Person {

    private final String vorname;
    private final String nachname;
    private final LocalDate gebrutstag;

    public Person(String vorname, String nachname, LocalDate gebrutstag) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.gebrutstag = gebrutstag;
    }


    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public LocalDate getGebrutstag() {
        return gebrutstag;
    }

    @Override
    public String toString() {
        return "Person{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", gebrutstag=" + gebrutstag +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!vorname.equals(person.vorname)) return false;
        if (!nachname.equals(person.nachname)) return false;
        return gebrutstag.equals(person.gebrutstag);
    }

    @Override
    public int hashCode() {
        int result = vorname.hashCode();
        result = 31 * result + nachname.hashCode();
        result = 31 * result + gebrutstag.hashCode();
        return result;
    }
}
