package ch.ethz.geco.t4j.internal.auth;

import java.util.EnumSet;
import java.util.Set;

// TODO: Doesn't look so good, maybe split the scopes into groups
public enum Scope {
    PARTICIPANT_MANAGE_REGISTRATIONS("participant:manage_registrations"),
    PARTICIPANT_MANAGE_PARTICIPATIONS("participant:manage_participations"),
    USER_INFO("user:info"),
    ORGANIZER_VIEW("organizer:view"),
    ORGANIZER_ADMIN("organizer:admin"),
    ORGANIZER_RESULT("organizer:result"),
    ORGANIZER_PARTICIPANT("organizer:participant"),
    ORGANIZER_REGISTRATION("organizer:registration"),
    ORGANIZER_DELETE("organizer:delete");

    private String internalName;
    public static final Set<Scope> ALL = EnumSet.allOf(Scope.class);
    public static final Set<Scope> NONE = EnumSet.noneOf(Scope.class);

    Scope(String internalName) {
        this.internalName = internalName;
    }

    public String getInternalName() {
        return internalName;
    }
}
