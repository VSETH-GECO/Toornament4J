package ch.ethz.geco.t4j.impl;

import ch.ethz.geco.t4j.obj.IDiscipline;
import ch.ethz.geco.t4j.obj.IToornamentClient;

import java.util.List;

public class Discipline implements IDiscipline {
    private final IToornamentClient client;

    /**
     * The unique ID of the discipline.
     */
    private final long id;

    /**
     * The name of the discipline.
     */
    private final String name;

    /**
     * The short name of the discipline.
     */
    private final String shortName;

    /**
     * The full name of the discipline.
     */
    private final String fullName;

    /**
     * The name of the right holder of the discipline.
     */
    private final String copyrights;

    /**
     * The platforms on which the discipline can be played.
     */
    private final List<String> platforms;

    /**
     * The minimal team size of the discipline.
     */
    private final Short minimalTeamSize;

    /**
     * The maximal team size of the discipline.
     */
    private final Short maximalTeamSize;

    public Discipline(IToornamentClient client, long id, String name, String shortName, String fullName, String copyrights, List<String> platforms, Short minimalTeamSize, Short maximalTeamSize) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.fullName = fullName;
        this.copyrights = copyrights;
        this.platforms = platforms;
        this.minimalTeamSize = minimalTeamSize;
        this.maximalTeamSize = maximalTeamSize;

        this.client = client;
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
    public long getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IToornamentClient getClient() {
        return client;
    }
}
