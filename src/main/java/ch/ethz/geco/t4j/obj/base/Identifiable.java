package ch.ethz.geco.t4j.obj.base;

public interface Identifiable {
    /**
     * Gets the unique ID of the object.
     *
     * @return The unique ID of the object.
     */
    long getID();

    /**
     * Gets the name of the object.
     *
     * @return The name of the object.
     */
    String getName();
}
