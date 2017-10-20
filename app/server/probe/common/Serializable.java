package probe.common;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class Serializable {
    private static Logger log = Logger.getLogger(Serializable.class);

    public @Nullable String toJson() {
        return Helpers.jsonStringify(this);
    };

    public @Nullable String toString() {
        return toJson();
    }
}
