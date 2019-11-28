package ch.ethz.geco.t4j.internal.json;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

/**
 * Represents a participant json object.
 */
public class ParticipantObject {
    public Long id;
    public String name;
    @JsonIgnore // FIXME: API inconsistencies (API description promises object but API gives empty array if no custom fields)
    public Map<String, Object> custom_fields;
    public List<PlayerObject> lineup;

    public static class PlayerObject {
        public String name;
        @JsonIgnore
        public Map<String, Object> custom_fields;
    }
}
