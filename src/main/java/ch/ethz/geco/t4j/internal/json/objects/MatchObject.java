package ch.ethz.geco.t4j.internal.json.objects;

import java.util.List;

/**
 * Represents a match json object.
 */
public class MatchObject {
    public Long id;
    public Long stage_id;
    public Long group_id;
    public Long round_id;
    public Integer number;
    public String type;
    public String status;
    public String scheduled_datetime;
    public String played_at;
    public List<OpponentObject> opponents;
    public List<MatchGameObject> games;

    public static class MatchGameObject {
        public Integer number;
        public String type;
        public String status;
        public List<OpponentObject> opponents;
    }
}
