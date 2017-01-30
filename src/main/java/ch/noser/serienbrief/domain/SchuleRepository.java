package ch.noser.serienbrief.domain;

import ch.noser.schule.api.ErziehungsberechtigtePerson;
import ch.noser.schule.api.Klasse;
import ch.noser.schule.api.Lehrer;
import ch.noser.schule.api.Schueler;
import com.google.common.collect.Lists;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by christoph.suter on 29.01.2017.
 */
public class SchuleRepository {

    private final List<Schueler> sus = new ArrayList<>();
    private final List<Lehrer> lehrer = new ArrayList<>();

    public SchuleRepository() {
        initSuS();
        initLehrer();
    }

    private void initSuS() {
        LocalDate date = LocalDate.of(1965, 1, 1);
        sus.add(new Schueler("Frank", "Furt", LocalDate.of(1987, 1, 17), "Sek A",
                             Lists.newArrayList(new ErziehungsberechtigtePerson("Hans", "Furt", date),
                                                new ErziehungsberechtigtePerson("Marta", "Furt", date))));
        sus.add(new Schueler("Ho Lee", "Fuk", LocalDate.of(1987, 6, 25), "Sek B",
                             Lists.newArrayList(new ErziehungsberechtigtePerson("Hans", "Fuk", date),
                                                new ErziehungsberechtigtePerson("Marta", "Fuk", date))));
        sus.add(new Schueler("Karl", "Rasur", LocalDate.of(1987, 6, 2), "Sek A",
                             Lists.newArrayList(new ErziehungsberechtigtePerson("Hans", "Rasur", date),
                                                new ErziehungsberechtigtePerson("Marta", "Rasur", date))));

        ArrayList<ErziehungsberechtigtePerson> eltern = Lists.newArrayList(new ErziehungsberechtigtePerson("Hans", "Imal", date),
                                                                           new ErziehungsberechtigtePerson("Marta", "Imal", date));
        sus.add(new Schueler("Max", "Imal", LocalDate.of(1987, 10, 8), "Sek A", eltern));
        sus.add(new Schueler("Nina", "Imal", LocalDate.of(1987, 7, 9), "Sek B", eltern));

        sus.add(new Schueler("Wolf", "Gang", LocalDate.of(1987, 5, 4), "Sek B",
                             Lists.newArrayList(new ErziehungsberechtigtePerson("Hans", "Gang", date),
                                                new ErziehungsberechtigtePerson("Marta", "Gang", date))));
    }

    private void initLehrer() {
        lehrer.add(new Lehrer("Anette", "Halbestunde", LocalDate.of(1965, 11, 18), "Sek A"));
        lehrer.add(new Lehrer("Hans", "Noetig", LocalDate.of(1965, 7, 15), "Sek B"));
    }


    public Klasse getKlasse(String klasse) {
        List<Schueler> sus = this.sus.stream()
                                     .filter(s -> s.getKlassenId().equals(klasse))
                                     .collect(toList());

        Lehrer lehrer = this.lehrer.stream()
                                   .filter(s -> s.getKlassenId().equals(klasse))
                                   .findFirst()
                                   .orElseThrow(() -> new IllegalArgumentException("Kein Lehrer gefunden fuer: " + klasse));

        return new Klasse(klasse, lehrer, sus);
    }

    public List<Klasse> getAlleKlassen() {
        return sus.stream()
                  .map(Schueler::getKlassenId)
                  .distinct()
                  .map(this::getKlasse)
                  .collect(toList());
    }
}
