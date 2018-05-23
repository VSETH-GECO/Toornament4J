package ch.ethz.geco.t4j.internal;

import ch.ethz.geco.t4j.Toornament4J;
import ch.ethz.geco.t4j.impl.CustomField;
import ch.ethz.geco.t4j.impl.Discipline;
import ch.ethz.geco.t4j.impl.Tournament;
import ch.ethz.geco.t4j.internal.json.objects.CustomFieldObject;
import ch.ethz.geco.t4j.internal.json.objects.DisciplineObject;
import ch.ethz.geco.t4j.internal.json.objects.ParticipantObject;
import ch.ethz.geco.t4j.internal.json.objects.TournamentObject;
import ch.ethz.geco.t4j.obj.ICustomField;
import ch.ethz.geco.t4j.obj.IParticipant;
import ch.ethz.geco.t4j.obj.IToornamentClient;
import ch.ethz.geco.t4j.obj.ITournament;
import ch.ethz.geco.t4j.util.LogMarkers;
import ch.ethz.geco.t4j.util.ToornamentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ToornamentUtils {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    // Use jackson afterburner
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    /**
     * Converts a discipline JSON object to a discipline object.
     *
     * @param disciplineObject The discipline JSON object to convert.
     * @return The converted discipline object.
     */
    public static Discipline getDisciplineFromJSON(DisciplineObject disciplineObject) {
        if (disciplineObject == null) {
            Toornament4J.LOGGER.warn(LogMarkers.UTIL, "Trying to convert null DisciplineObject.");
            return null;
        }

        return new Discipline(disciplineObject.id, disciplineObject.name, disciplineObject.shortname,
                disciplineObject.fullname, disciplineObject.copyrights, disciplineObject.platforms_available,
                disciplineObject.team_size == null ? null : disciplineObject.team_size.min,
                disciplineObject.team_size == null ? null : disciplineObject.team_size.max);
    }

    /**
     * Converts a tournament JSON object to a tournament object.
     *
     * @param client           The client the tournament belongs to.
     * @param tournamentObject The tournament JSON object to convert.
     * @return The converted tournament object.
     */
    public static Tournament getTournamentFromJSON(IToornamentClient client, TournamentObject tournamentObject) {
        if (tournamentObject == null) {
            Toornament4J.LOGGER.warn(LogMarkers.UTIL, "Trying to convert null TournamentObject.");
            return null;
        }

        ITournament.Status status;
        switch (tournamentObject.status) {
            case "setup":
                status = ITournament.Status.SETUP;
                break;
            case "running":
                status = ITournament.Status.RUNNING;
                break;
            case "completed":
                status = ITournament.Status.COMPLETED;
                break;
            default:
                throw new ToornamentException("Unknown status: " + tournamentObject.status + ". Please contact a developer!");
        }

        Boolean isSingleplayer = null;
        if (tournamentObject.participant_type != null) {
            switch (tournamentObject.participant_type) {
                case "team":
                    isSingleplayer = false;
                    break;
                case "single":
                    isSingleplayer = true;
                    break;
                default:
                    throw new ToornamentException("Unknown participant type: " + tournamentObject.participant_type + ". Please contact a developer!");
            }
        }

        return new Tournament(client, tournamentObject.id, tournamentObject.discipline, tournamentObject.name,
                tournamentObject.full_name, status,
                tournamentObject.scheduled_date_start == null ? null : LocalDate.parse(tournamentObject.scheduled_date_start),
                tournamentObject.scheduled_date_end == null ? null : LocalDate.parse(tournamentObject.scheduled_date_end),
                tournamentObject.timezone == null ? null : ZoneId.of(tournamentObject.timezone),
                tournamentObject.isPublic, tournamentObject.size, isSingleplayer, tournamentObject.online,
                tournamentObject.location, tournamentObject.country, tournamentObject.organization,
                tournamentObject.contact, tournamentObject.discord, tournamentObject.website,
                tournamentObject.description, tournamentObject.rules, tournamentObject.prize,
                tournamentObject.platforms, tournamentObject.logo, tournamentObject.registration_enabled,
                tournamentObject.registration_opening_datetime == null ? null : ZonedDateTime.parse(tournamentObject.registration_opening_datetime, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                tournamentObject.registration_closing_datetime == null ? null : ZonedDateTime.parse(tournamentObject.registration_closing_datetime, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

    /**
     * Converts a {@link CustomFieldObject} JSON object to a {@link CustomField} object.
     *
     * @param client            The client the custom field belongs to.
     * @param customFieldObject The custom field JSON object to convert.
     * @return The converted custom field object.
     */
    public static ICustomField getCustomFieldByJSON(IToornamentClient client, CustomFieldObject customFieldObject) {
        if (customFieldObject == null) {
            Toornament4J.LOGGER.warn(LogMarkers.UTIL, "Trying to convert null CustomFieldObject.");
            return null;
        }

        ICustomField.Target target = null;
        if (customFieldObject.target_type != null) {
            switch (customFieldObject.target_type) {
                case "player":
                    target = ICustomField.Target.PLAYER;
                    break;
                case "team":
                    target = ICustomField.Target.TEAM;
                    break;
                case "team_player":
                    target = ICustomField.Target.TEAM_PLAYER;
                    break;
                default:
                    throw new ToornamentException("Unknown target type: " + customFieldObject.target_type + ". Please contact a developer!");
            }
        } else {
            Toornament4J.LOGGER.warn(LogMarkers.UTIL, "Got CustomFieldObject without target_type. Please contact a developer!");
        }

        return new CustomField(client, customFieldObject.id, customFieldObject.machine_name, customFieldObject.label,
                target, customFieldObject.type, customFieldObject.default_value, customFieldObject.required,
                customFieldObject.isPublic, customFieldObject.position);
    }

    public static IParticipant getParticipantFromJSON(IToornamentClient client, ParticipantObject participantObject) {
        if (participantObject == null) {
            Toornament4J.LOGGER.warn(LogMarkers.UTIL, "Trying to convert null ParticipantObject.");
            return null;
        }

        return null;
    }
}
