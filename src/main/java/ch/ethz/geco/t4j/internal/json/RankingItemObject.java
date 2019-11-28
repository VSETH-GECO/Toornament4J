package ch.ethz.geco.t4j.internal.json;

import java.util.Map;

/**
 * Represents a ranking item json object.
 */
public class RankingItemObject {
    public Long id;
    public Long group_id;
    public Integer number;
    public Integer position;
    public Integer rank;
    public ParticipantObject participant;
    public Integer points;
    public Map<String, Object> properties;
}
