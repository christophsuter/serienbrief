package ch.noser.serienbrief.api;

import java.util.Map;

/**
 * Created by christoph.suter on 28.01.2017.
 */
public class SerienBrief {

    private final SerienBriefType type;
    private final Map<String, String> values;

    public SerienBrief(SerienBriefType type, Map<String, String> values) {
        this.type = type;
        this.values = values;
    }

    public String getValue(String property) {
        return values.get(property);
    }

    public SerienBriefType getType() {
        return type;
    }
}
