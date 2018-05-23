package ch.ethz.geco.t4j.internal.json.objects;

import java.util.Map;

/**
 * Represents a round json object.
 */
public class RoundObject {
    public Long id;
    public Long stage_id;
    public Long group_id;
    public Integer number;
    public String name;
    public Boolean closed;
    public Map<String, Object> settings;
}
