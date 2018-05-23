package ch.ethz.geco.t4j.internal.json.objects;

/**
 * Represents an opponent json object used in matches and brackets
 */
public class OpponentObject {
    public Integer number;
    public Integer position;
    public String result;
    public Integer rank;
    public Boolean forfeit;
    public Integer score;
    public String source_type;
    public String source_node_id;
    public ParticipantObject participant;
}