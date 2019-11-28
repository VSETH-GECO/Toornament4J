package ch.ethz.geco.t4j.internal.json;

import java.util.Map;

/**
 * Represents a stage json object.
 */
public class StageObject {
    public Long id;
    public String name;
    public String number;
    public String type;
    public Boolean closed;
    public Map<String, Object> settings;
}
