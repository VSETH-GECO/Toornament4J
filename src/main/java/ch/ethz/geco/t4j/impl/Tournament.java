package ch.ethz.geco.t4j.impl;

import ch.ethz.geco.t4j.internal.Endpoints;
import ch.ethz.geco.t4j.internal.ToornamentUtils;
import ch.ethz.geco.t4j.internal.json.objects.CustomFieldObject;
import ch.ethz.geco.t4j.internal.json.objects.ParticipantObject;
import ch.ethz.geco.t4j.obj.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Tournament implements ITournament {
    private final long id;
    private final String disciplineID;
    private final String name;
    private final String fullName;
    private final Status status;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ZoneId timezone;
    private final Boolean isPublic;
    private final int size;
    private final Boolean isSingleplayer;
    private final Boolean isOnline;
    private final String location;
    private final String country;
    private final String organization;
    private final String contact;
    private final String discord;
    private final String website;
    private final String description;
    private final String rules;
    private final String prize;
    private final List<String> platforms;
    private final Map<String, String> logos;
    private final Boolean isRegistrationOpen;
    private final ZonedDateTime registrationStart;
    private final ZonedDateTime registrationEnd;

    private final IToornamentClient client;

    public Tournament(IToornamentClient client, long id, String disciplineID, String name, String fullName, Status status, LocalDate startDate,
                      LocalDate endDate, ZoneId timezone, Boolean isPublic, int size, Boolean isSingleplayer,
                      Boolean isOnline, String location, String country, String organization, String contact,
                      String discord, String website, String description, String rules, String prize,
                      List<String> platforms, Map<String, String> logos, Boolean isRegistrationOpen,
                      ZonedDateTime registrationStart, ZonedDateTime registrationEnd) {
        this.id = id;
        this.disciplineID = disciplineID;
        this.name = name;
        this.fullName = fullName;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timezone = timezone;
        this.isPublic = isPublic;
        this.size = size;
        this.isSingleplayer = isSingleplayer;
        this.isOnline = isOnline;
        this.location = location;
        this.country = country;
        this.organization = organization;
        this.contact = contact;
        this.discord = discord;
        this.website = website;
        this.description = description;
        this.rules = rules;
        this.prize = prize;
        this.platforms = platforms;
        this.logos = logos;
        this.isRegistrationOpen = isRegistrationOpen;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.client = client;
    }

    @Override
    public Optional<String> getFullName() {
        return Optional.ofNullable(fullName);
    }

    @Override
    public IDiscipline getDiscipline() {
        return client.getDisciplineByID(disciplineID);
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Optional<LocalDate> getStartDate() {
        return Optional.ofNullable(startDate);
    }

    @Override
    public Optional<LocalDate> getEndDate() {
        return Optional.ofNullable(endDate);
    }

    @Override
    public Optional<ZoneId> getTimezone() {
        return Optional.ofNullable(timezone);
    }

    @Override
    public Boolean isPublic() {
        return isPublic;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Optional<Boolean> isSingleplayer() {
        return Optional.ofNullable(isSingleplayer);
    }

    @Override
    public Boolean isOnline() {
        return isOnline;
    }

    @Override
    public Optional<String> getLocation() {
        return Optional.ofNullable(location);
    }

    @Override
    public Optional<String> getCountry() {
        return Optional.ofNullable(country);
    }

    @Override
    public Optional<String> getOrganization() {
        return Optional.ofNullable(organization);
    }

    @Override
    public Optional<String> getContactEmail() {
        return Optional.ofNullable(contact);
    }

    @Override
    public Optional<String> getDiscordURL() {
        return Optional.ofNullable(discord);
    }

    @Override
    public Optional<String> getWebsite() {
        return Optional.ofNullable(website);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @Override
    public Optional<String> getRules() {
        return Optional.ofNullable(rules);
    }

    @Override
    public Optional<String> getPrize() {
        return Optional.ofNullable(prize);
    }

    @Override
    public Optional<List<String>> getPlatforms() {
        return Optional.ofNullable(platforms);
    }

    @Override
    public Optional<Map<String, String>> getLogos() {
        return Optional.ofNullable(logos);
    }

    @Override
    public ZonedDateTime getOpeningTime() {
        return registrationStart;
    }

    @Override
    public ZonedDateTime getClosingTime() {
        return registrationEnd;
    }

    @Override
    public Boolean isOpen() {
        return isRegistrationOpen;
    }

    @Override
    public long getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<ICustomField> getCustomFields() {
        List<CustomFieldObject> customFieldObjects = ((ToornamentClient) client).REQUESTS.GET.makeRequest(Endpoints.VIEWER + "/tournaments/" + id + "/custom-fields", new TypeReference<List<CustomFieldObject>>() {
        });

        if (customFieldObjects == null)
            return null;

        List<ICustomField> customFields = new ArrayList<>();

        for (CustomFieldObject customFieldObject : customFieldObjects) {
            customFields.add(ToornamentUtils.getCustomFieldByJSON(client, customFieldObject));
        }

        return customFields;
    }

    @Override
    public List<IParticipant> getParticipants() {
        List<ParticipantObject> participantObjects = ((ToornamentClient) client).REQUESTS.GET.makeRequest(Endpoints.VIEWER + "/tournaments/" + id + "/participants", new TypeReference<List<ParticipantObject>>() {
        });

        if (participantObjects == null)
            return null;

        List<IParticipant> participants = new ArrayList<>();

        for (ParticipantObject participantObject : participantObjects) {
            participants.add(ToornamentUtils.getParticipantFromJSON(client, participantObject));
        }

        return participants;
    }

    @Override
    public IParticipant getParticipantByID(Long participantID) {
        return ToornamentUtils.getParticipantFromJSON(this.client, ((ToornamentClient) client).REQUESTS.GET.makeRequest(Endpoints.VIEWER + "/tournaments/" + id + "/participants/" + participantID, ParticipantObject.class));
    }

    @Override
    public IToornamentClient getClient() {
        return client;
    }
}
