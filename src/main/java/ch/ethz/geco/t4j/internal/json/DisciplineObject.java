package ch.ethz.geco.t4j.internal.json;

import java.util.List;

/**
 * Represents a discipline json object.
 */
public class DisciplineObject {
    public long id;
    public String name;
    public String shortname;
    public String fullname;
    public String copyrights;
    public List<String> platforms_available;
    public TeamSizeObject team_size;

    public static class TeamSizeObject {
        public short min;
        public short max;
    }
}
