package ch.noser.serienbrief.domain;

import ch.noser.schule.api.Schueler;
import ch.noser.serienbrief.api.SerienBrief;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.noser.serienbrief.api.SerienBriefType.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by christoph.suter on 29.01.2017.
 */
public class SerienBriefServiceTest {

    private SerienBriefServiceImpl testee;

    @Before()
    public void setup() {
        testee = new SerienBriefServiceImpl();
    }

    @Test
    public void testCreateElternAbend() {
        //Arrange
        String klassenId = "Sek A";

        //Act
        List<SerienBrief> elternAbend = testee.createElternAbend(klassenId);

        //Assert
        assertEquals(elternAbend.size(), 3);
        elternAbend.forEach(
                b -> {
                    assertEquals(b.getValue("Klasse.Id"), klassenId);
                    assertEquals(b.getValue("Lehrer.Name"), "Anette Halbestunde");
                    assertEquals(b.getType(), ElternAbend);
                }
        );

        assertEquals(elternAbend.get(0).getValue("Eltern.Nachname"), "Furt");
        assertEquals(elternAbend.get(1).getValue("Eltern.Nachname"), "Rasur");
        assertEquals(elternAbend.get(2).getValue("Eltern.Nachname"), "Imal");
    }


    @Test
    public void createElternAbendNoten() {
        //Arrange
        String klassenId = "Sek A";
        Map<Schueler, List<Float>> noten = new HashMap<>();
        List<Schueler> schueler = testee.repo.getKlasse(klassenId).getSchueler();
        noten.put(schueler.get(0), Lists.newArrayList(1f, 2.5f, 3.5f));
        noten.put(schueler.get(1), Lists.newArrayList(4.5f, 5f, 5f));
        noten.put(schueler.get(2), Lists.newArrayList(5.5f, 5.75f, 6f));

        //Act
        List<SerienBrief> notenBriefe = testee.createElternAbendNoten(noten);

        //Assert
        assertEquals(notenBriefe.size(), 2);
        notenBriefe.forEach(
                b -> {
                    assertEquals(b.getValue("Klasse.Id"), klassenId);
                    assertEquals(b.getValue("Lehrer.Name"), "Anette Halbestunde");
                }
        );

        assertEquals(notenBriefe.get(0).getValue("Eltern.Nachname"), "Furt");
        assertEquals(notenBriefe.get(0).getType(), ElternGespraechSchlecht);
        assertEquals(notenBriefe.get(1).getValue("Eltern.Nachname"), "Imal");
        assertEquals(notenBriefe.get(1).getType(), ElternGespraechGut);
    }

    @Test
    public void createInformationsAbend() {
        List<SerienBrief> briefe = testee.createInformationsAbend();
        assertEquals(briefe.size(), 5);

        assertEquals(briefe.get(0).getValue("Eltern.Nachname"), "Furt");
        assertEquals(briefe.get(1).getValue("Eltern.Nachname"), "Rasur");
        assertEquals(briefe.get(2).getValue("Eltern.Nachname"), "Imal");
        assertEquals(briefe.get(3).getValue("Eltern.Nachname"), "Fuk");
        assertEquals(briefe.get(4).getValue("Eltern.Nachname"), "Gang");
    }

}