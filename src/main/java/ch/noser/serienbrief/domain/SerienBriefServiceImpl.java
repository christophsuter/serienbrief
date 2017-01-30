package ch.noser.serienbrief.domain;

import ch.noser.schule.api.ErziehungsberechtigtePerson;
import ch.noser.schule.api.Klasse;
import ch.noser.schule.api.Schueler;
import ch.noser.serienbrief.api.SerienBrief;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.noser.serienbrief.api.SerienBriefType.*;
import static java.util.stream.Collectors.toList;

/**
 * Created by christoph.suter on 29.01.2017.
 */
public class SerienBriefServiceImpl {

    /**
     * Template: schule.eltern.gespraech.gut
     * Hallo {{Eltern.Nachname}}
     *
     * Ihr Kind {{SuS.Vorname}} hat ...
     *
     *
     */
    public static final String SUS_NAME = "SuS.Vorname";
    public static final String KLASSE_ID = "Klasse.Id";
    public static final String LEHRER_NAME = "Lehrer.Name";
    public static final String ELTERN_NACHNAME = "Eltern.Nachname";
    public static final float GUTE_NOTEN = 5.5f;
    public static final float SCHLECHTE_NOTEN = 3.5f;
    SchuleRepository repo = new SchuleRepository(); //This is fine, no Dependency Injection needed.

    public List<SerienBrief> createElternAbend(String klassenId) {
        List<SerienBrief> briefe = new ArrayList<>();
        Klasse klasse = repo.getKlasse(klassenId);

        for (Schueler schueler : klasse.getSchueler()) {
            Map<String, String> values = new HashMap<>();
            values.put(KLASSE_ID, klassenId);
            values.put(LEHRER_NAME, klasse.getLehrer().getVorname() + " " + klasse.getLehrer().getNachname());
            values.put(ELTERN_NACHNAME, schueler.getErziehungsberechtigtePersonen().get(0).getNachname());
            briefe.add(new SerienBrief(ElternAbend, values));
        }
        return briefe;
    }



    public List<SerienBrief> createElternAbendNoten(Map<Schueler, List<Float>> noten) {
        List<SerienBrief> briefe = new ArrayList<>();
        for (Map.Entry<Schueler, List<Float>> entry : noten.entrySet()) {
            float schnitt = calcSchnitt(entry.getValue());

            if (schnitt > GUTE_NOTEN) {
                briefe.add(createGutesElternGeschpraech(entry.getKey(), schnitt));
            }

            if (schnitt <= SCHLECHTE_NOTEN) {
                briefe.add(createSchlechtesElternGeschpraech(entry.getKey(), schnitt));
            }
        }

        return briefe;
    }

    private SerienBrief createGutesElternGeschpraech(Schueler schueler, float schnitt) {
        Map<String, String> values = new HashMap<>();
        String klassenId = schueler.getKlassenId();
        Klasse klasse = repo.getKlasse(klassenId);

        values.put(KLASSE_ID, klassenId);
        values.put(LEHRER_NAME, klasse.getLehrer().getVorname() + " " + klasse.getLehrer().getNachname());
        values.put(ELTERN_NACHNAME, schueler.getErziehungsberechtigtePersonen().get(0).getNachname());
        return new SerienBrief(ElternGespraechGut, values);
    }

    private SerienBrief createSchlechtesElternGeschpraech(Schueler schueler, float schnitt) {
        Map<String, String> values = new HashMap<>();
        String klassenId = schueler.getKlassenId();
        Klasse klasse = repo.getKlasse(klassenId);

        values.put(KLASSE_ID, klassenId);
        values.put(LEHRER_NAME, klasse.getLehrer().getVorname() + " " + klasse.getLehrer().getNachname());
        values.put(ELTERN_NACHNAME, schueler.getErziehungsberechtigtePersonen().get(0).getNachname());
        return new SerienBrief(ElternGespraechSchlecht, values);
    }

    public List<SerienBrief> createInformationsAbend() {
        List<Klasse> alleKlassen = repo.getAlleKlassen();
        List<ErziehungsberechtigtePerson> eltern = new ArrayList<>();
        List<SerienBrief> elternbriefe = new ArrayList<>();

        List<Schueler> schueler = alleKlassen.stream()
                                             .map(Klasse::getSchueler)
                                             .flatMap(List::stream)
                                             .collect(toList());

        for (Schueler s : schueler) {
            ErziehungsberechtigtePerson elternTeil = s.getErziehungsberechtigtePersonen().get(0);
            if (!eltern.contains(elternTeil)) {
                eltern.add(elternTeil);
            }
        }

        for (ErziehungsberechtigtePerson elternTeil : eltern) {
            Map<String, String> values = new HashMap<>();
            values.put(ELTERN_NACHNAME, elternTeil.getNachname());
            elternbriefe.add(new SerienBrief(InformationsAbend, values));
        }

        return elternbriefe;
    }


    private float calcSchnitt(List<Float> noten) {
        Float sum = noten.stream()
                         .reduce(Float::sum)
                         .orElseThrow(() -> new IllegalArgumentException("Keine Noten vorhanden."));

        return sum / noten.size();
    }


}
