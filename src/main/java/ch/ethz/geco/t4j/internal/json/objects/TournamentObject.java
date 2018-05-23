package ch.ethz.geco.t4j.internal.json.objects;

import ch.ethz.geco.t4j.internal.json.objects.custom.LogoObject;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Represents a Tournament json object.
 */
public class TournamentObject {
    public Long id;
    public String discipline;
    public String name;
    public String full_name;
    public String status; // TODO: test if enum works too here
    public String scheduled_date_start;
    public String scheduled_date_end;
    public String timezone;
    @JsonProperty("public")
    public Boolean isPublic;
    public Integer size;
    public String participant_type;
    public Boolean online;
    public String location;
    public String country;
    public String organization;
    public String contact;
    public String discord;
    public String website;
    public String description;
    public String rules;
    public String prize;
    public List<String> platforms;
    public Map<String, String> logo;
    public Boolean registration_enabled;
    public String registration_opening_datetime;
    public String registration_closing_datetime;
}
