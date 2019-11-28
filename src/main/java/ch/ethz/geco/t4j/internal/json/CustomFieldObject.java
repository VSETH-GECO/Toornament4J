package ch.ethz.geco.t4j.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represenst a custom field json object.
 */
public class CustomFieldObject {
    public Long id;
    public String machine_name;
    public String label;
    public String target_type;
    public String type;
    public Object default_value; // TODO: is this working?
    public Boolean required;
    @JsonProperty("public")
    public Boolean isPublic;
    public Integer position;
}
