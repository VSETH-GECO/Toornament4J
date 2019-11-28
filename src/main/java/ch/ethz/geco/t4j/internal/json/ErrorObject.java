package ch.ethz.geco.t4j.internal.json;

import java.util.List;

/**
 * Represents an error object, you'll encounter in API responses.
 */
public class ErrorObject {
    public List<SingleErrorObject> errors;

    public static class SingleErrorObject {
        public String message;
        public String scope;
        public String property_path;
        public String invalid_value;
        public String type;
    }
}
