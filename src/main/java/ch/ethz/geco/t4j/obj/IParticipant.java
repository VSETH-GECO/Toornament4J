package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.IToornamentObject;
import ch.ethz.geco.t4j.obj.base.Identifiable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The participant refers to the attendance of a competitor in a single tournament.
 * A participant is tied to its tournament. In the case of one competitor taking part in several tournaments,
 * he will be represented by one distinctive participant for each tournament. <br>
 * <br>
 * The participant is referenced each time there is a need to clearly identify a competitor in the tournament.
 * For example, to identify who is playing a match or to identify the rank of a competitor in a ranking.
 */
public interface IParticipant extends IToornamentObject, Identifiable {
    int MAX_NAME_LENGTH = 40;

    /**
     * Gets the custom fields of the participant.
     *
     * @return the custom fields of the participant.
     */
    Map<String, Object> getCustomFields();

    /**
     * Gets the lineup of the participant, which is a list of participants
     * playing in this team if the current participant represents a team.
     *
     * @return the lineup of the participant or nothing if the participant isn't a team.
     */
    Optional<List<IParticipant>> getLineup();
}
