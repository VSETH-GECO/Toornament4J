package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.Closable;
import ch.ethz.geco.t4j.obj.base.Identifiable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A tournament is a competition involving a given set of competitors.
 * These competitors play against each others following a predefined path.
 * Some competitors are eliminated along the way and, at the end, a single competitor is declared winner.<br>
 * <br>
 * In Toornament, competitors are defined as participants.
 * The predefined path of matches is defined as the tournament’s structure and is composed of stages,
 * groups, rounds, brackets, rankings and matches.
 * A match can be further detailed with subsets of a match usually called games or match games.
 */
public interface ITournament extends Identifiable, Closable {
    int MAX_NAME_LENGTH = 30;
    int MAX_FULL_NAME_LENGTH = 80;

    int MAX_DESCRIPTION_LENGTH = 1500;
    int MAX_RULE_LENGTH = 10000;
    int MAX_PRIZE_LENGTH = 1500;

    /**
     * Gets the full name of the tournament.
     *
     * @return The full name of the tournament.
     */
    Optional<String> getFullName();

    /**
     * Gets the discipline of the tournament.
     *
     * @return The discipline of the tournament.
     */
    IDiscipline getDiscipline();

    /**
     * Gets the status of the tournament.
     *
     * @return The status of the tournament.
     */
    Status getStatus();

    /**
     * Gets the local start date of the tournament.
     *
     * @return The start date of the tournament.
     */
    Optional<LocalDate> getStartDate();

    /**
     * Gets the local end date of the tournament.
     *
     * @return The end date of the tournament.
     */
    Optional<LocalDate> getEndDate();

    /**
     * Gets the time zone of the tournament.
     *
     * @return The time zone of the tournament.
     */
    Optional<ZoneId> getTimezone();

    /**
     * Returns whether the tournament is published or not.
     *
     * @return Whether the tournament is published or not.
     */
    Boolean isPublic();

    /**
     * Gets the size of the tournament.
     *
     * @return The size of the tournament.
     */
    Integer getSize();

    /**
     * Returns whether the tournament is team vs. team or player vs. player.
     *
     * @return True if a team consists of only one player, false otherwise.
     */
    Optional<Boolean> isSingleplayer();

    /**
     * Returns whether the tournament is online or local.
     *
     * @return True if the tournament is played on the internet, false if local.
     */
    Boolean isOnline();

    /**
     * Gets the location of the tournament. This is more precise than
     * the country of the tournament.
     *
     * @return the location of the tournament.
     */
    Optional<String> getLocation();

    /**
     * Gets the country where the tournament is held.
     *
     * @return The country of the tournament.
     */
    Optional<String> getCountry();

    /**
     * Gets the name of the tournament organizer whether it is an individual, group, association or company.
     *
     * @return The name of the organizer.
     */
    Optional<String> getOrganization();

    /**
     * Gets the email address to contact the tournament organizer.
     *
     * @return The organizer email address.
     */
    Optional<String> getContactEmail();

    /**
     * Gets the Discord URL of the tournament organizer.
     *
     * @return The Discord URL of the organizer.
     */
    Optional<String> getDiscordURL();

    /**
     * Gets the website of the tournament or tournament organizer.
     *
     * @return The tournament website.
     */
    Optional<String> getWebsite();

    /**
     * Gets the description of the tournament.
     *
     * @return The tournament description.
     */
    Optional<String> getDescription();

    /**
     * Gets the rules of the tournament.
     *
     * @return The tournament rules.
     */
    Optional<String> getRules();

    /**
     * Gets the prize of the tournament.
     *
     * @return The tournament prize.
     */
    Optional<String> getPrize();

    /**
     * Gets the platforms on which the tournament can be played.
     *
     * @return The supported platforms for this tournament.
     */
    Optional<List<String>> getPlatforms();

    /**
     * Gets all available logos for the tournament.
     *
     * @return The tournament logos.
     */
    Optional<Map<String, String>> getLogos();

    /**
     * Gets the time when the registration for the tournament opens.
     *
     * @return The opening time of the tournament.
     */
    ZonedDateTime getOpeningTime();

    /**
     * Gets the time when the registration for the tournament closes.
     *
     * @return The closing time of the tournament.
     */
    ZonedDateTime getClosingTime();

    /**
     * Gets a list of the custom fields of the tournament.
     *
     * @return The custom field of the tournament.
     */
    List<ICustomField> getCustomFields();

    /**
     * Gets a list of participants of the tournament.
     *
     * @return A list of participants.
     */
    List<IParticipant> getParticipants();

    /**
     * Gets a participant by its ID.
     * @return
     */
    IParticipant getParticipantByID(Long participantID);

    /**
     * The status of a tournament.
     */
    enum Status {
        SETUP, RUNNING, COMPLETED
    }
}
