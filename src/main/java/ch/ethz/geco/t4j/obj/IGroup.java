package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.Closable;
import ch.ethz.geco.t4j.obj.base.IToornamentObject;
import ch.ethz.geco.t4j.obj.base.Identifiable;
import ch.ethz.geco.t4j.obj.base.Positionable;

/**
 * A group represents a portion of a stage that usually involves only a subset of the participants.
 * Most of the time groups can be played simultaneously because they involve different participants.
 * However, in some cases, a group may have to wait the outcome of another group in order to receive
 * additional participants and continue. <br>
 * <br>
 * Some stage types do not require groups.
 * In such case, a single group is still created as a placeholder
 * to comply with the structure of the other stages.
 */
public interface IGroup extends IToornamentObject, Identifiable, Positionable, Closable {
    int MAX_NAME_LENGTH = 30;

    /**
     * Gets the stage that contains this group.
     *
     * @return the stage that contains this group.
     */
    IStage getStage();

    // TODO: implement group settings
}
