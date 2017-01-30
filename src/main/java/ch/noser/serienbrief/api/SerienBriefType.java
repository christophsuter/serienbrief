package ch.noser.serienbrief.api;

/**
 * Created by christoph.suter on 28.01.2017.
 */
public enum SerienBriefType {

    ElternGespraechSchlecht("schule.eltern.gespraech.schlecht"),
    ElternGespraechGut("schule.eltern.gespraech.gut"),
    ElternAbend("schule.eltern.abend"),
    InformationsAbend("schule.abend");


    private final String type;

    SerienBriefType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
