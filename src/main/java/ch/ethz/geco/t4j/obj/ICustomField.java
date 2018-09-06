package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.IToornamentObject;
import ch.ethz.geco.t4j.obj.base.Positionable;
/**
 * A custom field contains additional information about some objects and can be set
 * by the tournament organizer.<br>
 * <br>
 * A custom field may be associated to a player, a team or a team's player.
 */
public interface ICustomField extends IToornamentObject, Positionable {
    int MAX_LABEL_LENGTH = 255;
    int MAX_NAME_LENGTH = 255;

    /**
     * Gets the unique ID of the custom field.
     *
     * @return The unique ID of the custom field.
     */
    Long getID();

    /**
     * Gets the internal name of the field used for internal processing.
     *
     * @return The internal name.
     */
    String getInternalName();

    /**
     * Gets the name of the field used for displaying to the user.
     *
     * @return The field name.
     */
    String getName();

    /**
     * Gets the target the field is associated with.
     *
     * @return The field target.
     */
    Target getTarget();

    /**
     * Gets the type of the custom field.
     * This determines what type the default value will have.
     *
     * @return The type of the custom field.
     */
    String getFieldType();

    // TODO: somehow implement custom field types:
    // https://developer.toornament.com/v2/core-concepts/custom-fields#custom-field-custom

    // TODO: implement polymorphic default value

    /**
     * Gets the default value as an object.
     * This can be a list, string, number or null.
     *
     * @return The default value of the field.
     */
    Object getDefaultValue();

    /**
     * Returns whether the custom field is required.
     *
     * @return True if the field is required, false otherwise.
     */
    Boolean isRequired();

    /**
     * Returns whether the custom field is public or not.
     *
     * @return True if the field is public, false otherwise
     */
    Boolean isPublic();

    /**
     * The target with which the field is associated.
     */
    enum Target {
        PLAYER, TEAM, TEAM_PLAYER
    }
}
