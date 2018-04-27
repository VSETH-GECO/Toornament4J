package ch.ethz.geco.t4j.obj.base;

public interface Closable {
    /**
     * Returns whether the object is still open or not. When an object was closed
     * this often means that it cannot be modified anymore.
     *
     * @return whether the object is still open or not.
     */
    Boolean isOpen();
}
