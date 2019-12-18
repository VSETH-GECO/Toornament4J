package ch.ethz.geco.t4j.internal.json;

import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.util.List;

/**
 * Represents an error object, you'll encounter in API responses.
 */
public class ErrorObject {
    public List<SingleErrorObject> errors;

    public static class SingleErrorObject {
        @NonNull
        public String message = "";
        @NonNull
        public String scope = "";
        @Nullable
        public String property_path;
        @Nullable
        public String invalid_value;
        @Nullable
        public String type;
    }
}
