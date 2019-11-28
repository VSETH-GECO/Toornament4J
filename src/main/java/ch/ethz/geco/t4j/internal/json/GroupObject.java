package ch.ethz.geco.t4j.internal.json;

import java.util.Map;

/**
 * Represents a group json object.
 */
public class GroupObject {
    public Long id;
    public Long stage_id;
    public Integer number;
    public String name;
    public Boolean closed;
    public Map<String, Object> settings;
}
