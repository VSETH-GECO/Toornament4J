package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.IToornamentObject;
import ch.ethz.geco.t4j.obj.base.Identifiable;
import ch.ethz.geco.t4j.obj.base.Positionable;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface IMatch extends IToornamentObject, Identifiable, Positionable {
    /**
     * Gets the round that contains this match.
     *
     * @return the round that contains this match.
     */
    IRound getRound();

    /**
     * Gets the type of the match.
     *
     * @return the type of the match.
     */
    Type getType();

    /**
     * Gets the status of the match.
     *
     * @return the status of the match.
     */
    Status getStatus();

    /**
     * Gets the date and time when the match is scheduled to be played.
     *
     * @return the scheduled date and time of the match.
     */
    Optional<ZonedDateTime> getScheduledDateTime();

    /**
     * Gets the date and time when the match was played.
     * This is actually the time when a result was provided for the match and
     * may not represent the actual time when the match was played.
     *
     * @return the date and time when the match was played.
     */
    Optional<ZonedDateTime> getPlayedDateTime();

    /**
     * The type of a match
     */
    enum Type {
        DUEL, FFA, BYE
    }

    /**
     * The status of a match
     */
    enum Status {
        PENDING, RUNNING, COMPLETED
    }
}
