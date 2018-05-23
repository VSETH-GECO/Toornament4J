package ch.ethz.geco.t4j.impl;

import ch.ethz.geco.t4j.obj.IDiscipline;

import java.util.List;

public class Discipline implements IDiscipline {
    /**
     * The unique ID of the discipline.
     */
    protected final String id;

    /**
     * The name of the discipline.
     */
    protected final String name;

    /**
     * The short name of the discipline.
     */
    protected final String shortName;

    /**
     * The full name of the discipline.
     */
    protected final String fullName;

    /**
     * The name of the right holder of the discipline.
     */
    protected final String copyrights;

    /**
     * The platforms on which the discipline can be played.
     */
    protected final List<String> platforms;

    /**
     * The minimal team size of the discipline.
     */
    protected final Short minimalTeamSize;

    /**
     * The maximal team size of the discipline.
     */
    protected final Short maximalTeamSize;

    public Discipline(String id, String name, String shortName, String fullName, String copyrights, List<String> platforms, Short minimalTeamSize, Short maximalTeamSize) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.fullName = fullName;
        this.copyrights = copyrights;
        this.platforms = platforms;
        this.minimalTeamSize = minimalTeamSize;
        this.maximalTeamSize = maximalTeamSize;
    }

    @Override
    public String getShortName() {
        return shortName;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getCopyright() {
        return copyrights;
    }

    @Override
    public List<String> getPlatforms() {
        return platforms;
    }

    @Override
    public Short getMinimalTeamSize() {
        return minimalTeamSize;
    }

    @Override
    public Short getMaximalTeamSize() {
        return maximalTeamSize;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
