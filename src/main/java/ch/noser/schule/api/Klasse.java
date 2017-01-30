package ch.noser.schule.api;

import java.util.List;

/**
 * Created by christoph.suter on 29.01.2017.
 */
public class Klasse {

    private final String KlasseId;
    private final Lehrer lehrer;
    private final List<Schueler> schueler;

    public Klasse(String klasseId, Lehrer lehrer, List<Schueler> schueler) {
        KlasseId = klasseId;
        this.lehrer = lehrer;
        this.schueler = schueler;
    }

    public String getKlasseId() {
        return KlasseId;
    }

    public Lehrer getLehrer() {
        return lehrer;
    }

    public List<Schueler> getSchueler() {
        return schueler;
    }

    @Override
    public String toString() {
        return "Klasse{" +
                "KlasseId='" + KlasseId + '\'' +
                ", lehrer=" + lehrer +
                ", schueler=" + schueler +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Klasse klasse = (Klasse) o;

        return KlasseId != null ? KlasseId.equals(klasse.KlasseId) : klasse.KlasseId == null;
    }

    @Override
    public int hashCode() {
        return KlasseId != null ? KlasseId.hashCode() : 0;
    }
}
