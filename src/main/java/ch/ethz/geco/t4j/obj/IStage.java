package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.Closable;
import ch.ethz.geco.t4j.obj.base.IToornamentObject;
import ch.ethz.geco.t4j.obj.base.Identifiable;
import ch.ethz.geco.t4j.obj.base.Positionable;

/**
 * A stage is a major step in a tournament.
 * Its purpose is to arrange and organize the competition for some of the participants
 * following a specific and standardized method. The method is defined by the type of stage.
 */
public interface IStage extends IToornamentObject, Identifiable, Positionable, Closable {
    int MAX_NAME_LENGTH = 30;

    /**
     * Gets the stage type.
     *
     * @return the stage type.
     */
    Type getType();

    // TODO: implement stage settings

    /**
     * The type of the stage.
     */
    enum Type {
        SINGLE_ELIM, DOUBLE_ELIM, ROUND_ROBIN,
        BRACKET, LEAGUE, SWISS
    }
}
