package ch.ethz.geco.t4j.obj.base;

import ch.ethz.geco.t4j.obj.IToornamentClient;

/**
 * Base class of which properties every toornament object class should have.
 */
public interface IToornamentObject {
    /**
     * Gets the client of the object.
     *
     * @return The client.
     */
    IToornamentClient getClient();
}
