package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.IToornamentObject;
import ch.ethz.geco.t4j.obj.base.Identifiable;

import java.util.List;

/**
 * A discipline represents the game which will be played at a tournament.
 */
public interface IDiscipline extends IToornamentObject, Identifiable {
    /**
     * Gets the short name of the discipline.<br>
     * <b>Example:</b> CS:GO
     *
     * @return the short name of the discipline.
     */
    String getShortName();

    /**
     * Gets the full name of the discipline.<br>
     * <b>Example:</b> Counter-Strike: Global Offensive
     *
     * @return the full name of the discipline.
     */
    String getFullName();

    /**
     * Gets name of the publisher of the discipline or any other
     * right related information about the owner of the discipline.<br>
     * <b>Example:</b> Valve Software
     *
     * @return the name of the rights holder of the discipline.
     */
    String getCopyright();

    /**
     * Gets a list of all platforms where this discipline is available.
     *
     * @return a list of available platforms.
     */
    List<String> getPlatforms();

    /**
     * Gets the minimal size of a team in this discipline.
     *
     * @return the minimal size of a team in this discipline.
     */
    Short getMinimalTeamSize();

    /**
     * Gets the maximal size of a team in this discipline.
     *
     * @return the maximal size of a team in this discipline.
     */
    Short getMaximalTeamSize();
}
