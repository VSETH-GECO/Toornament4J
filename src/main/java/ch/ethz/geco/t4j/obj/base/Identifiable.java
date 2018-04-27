package ch.ethz.geco.t4j.obj.base;

public interface Identifiable {
    /**
     * Gets the unique ID of the object.
     *
     * @return the unique ID of the object.
     */
    String getID();

    /**
     * Gets the name of the object.
     *
     * @return the name of the object.
     */
    String getName();
}
