package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.Closable;
import ch.ethz.geco.t4j.obj.base.Identifiable;
import ch.ethz.geco.t4j.obj.base.Positionable;

/**
 * A round represents a small step inside a group in which all participants play no more than one match.
 * The purpose of a round is to provide a step in which all participants can, if necessary, play simultaneously.
 */
public interface IRound extends Identifiable, Positionable, Closable {
    int MAX_NAME_LENGTH = 30;

    /**
     * Gets the group that contains this round.
     *
     * @return the group that contains this round.
     */
    IGroup getGroup();

    // TODO: implement round settings
}
