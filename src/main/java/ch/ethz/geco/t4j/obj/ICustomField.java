package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.Identifiable;
import ch.ethz.geco.t4j.obj.base.Positionable;

/**
 * A custom field contains additional information about some objects and can be set
 * by the tournament organizer.<br>
 * <br>
 * A custom field may be associated to a player, a team or a team's player.
 */
public interface ICustomField extends Identifiable, Positionable {
    int MAX_LABEL_LENGTH = 255;
    int MAX_NAME_LENGTH = 255;

    /**
     * Gets the label of the field used for displaying to the user.
     * The name of the field is simply an internal name.
     *
     * @return the field label.
     */
    String getLabel();

    /**
     * Gets the target the field is associated with.
     *
     * @return the field target.
     */
    Target getTarget();

    // TODO: somehow implement custom field types:
    // https://developer.toornament.com/v2/core-concepts/custom-fields#custom-field-custom

    // TODO: implement polymorphic default value

    /**
     * Returns whether the custom field is public or not.
     *
     * @return true if the field is public, false otherwise
     */
    Boolean isPublic();

    /**
     * The target with which the field is associated.
     */
    enum Target {
        PLAYER, TEAM, TEAM_PLAYER
    }
}
