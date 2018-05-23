package ch.ethz.geco.t4j.internal;

import ch.ethz.geco.t4j.obj.ITournament;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FeaturedTournamentFilter {
    private String name;
    private List<String> disciplines = new ArrayList<>();
    private List<ITournament.Status> statuses = new ArrayList<>();
    private LocalDate scheduledBefore;
    private LocalDate scheduledAfter;
    private List<String> countries = new ArrayList<>();
    private List<String> platforms = new ArrayList<>();
    private Boolean isOnline;
    private Sorting sort;

    /**
     * Partially filters by name. It will match with tournaments containing the given string as substring.
     *
     * @param name The name to filter by.
     */
    public FeaturedTournamentFilter withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Adds a discipline to filter by.
     *
     * @param discipline A discipline to filter by.
     */
    public FeaturedTournamentFilter addDiscipline(String discipline) {
        this.disciplines.add(discipline);
        return this;
    }

    /**
     * Adds a status to filter by.
     *
     * @param status A status to filter by.
     */
    public FeaturedTournamentFilter addStatus(ITournament.Status status) {
        this.statuses.add(status);
        return this;
    }

    /**
     * Show only tournaments before or at the given date.
     *
     * @param date The date after which tournaments get filtered out.
     */
    public FeaturedTournamentFilter beforeDate(LocalDate date) {
        this.scheduledBefore = date;
        return this;
    }

    /**
     * Show only tournaments after or at the given date
     *
     * @param date The date before which tournaments get filtered out.
     */
    public FeaturedTournamentFilter afterDate(LocalDate date) {
        this.scheduledAfter = date;
        return this;
    }

    /**
     * Adds a country to filter by.
     *
     * @param country A country to filter by.
     */
    public FeaturedTournamentFilter addCountry(String country) {
        this.countries.add(country);
        return this;
    }

    /**
     * Adds a platform to filter by.
     *
     * @param platform A platform to filter by.
     */
    public FeaturedTournamentFilter addPlatform(String platform) {
        this.platforms.add(platform);
        return this;
    }

    /**
     * Filter by tournaments that are specifically only online or local.
     *
     * @param isOnline If the tournaments should be filtered by only online or only.
     */
    public FeaturedTournamentFilter isOnline(Boolean isOnline) {
        this.isOnline = isOnline;
        return this;
    }

    /**
     * Sort the result either ascending or descending by their scheduled date.
     *
     * @param sort How to sort the tournaments.
     */
    public FeaturedTournamentFilter sort(Sorting sort) {
        this.sort = sort;
        return this;
    }

    /**
     * Gets the query string to filter by the properties currently set.
     *
     * @return The query string to filter by the currently properties.
     */
    public String getQueryString() {
        List<NameValuePair> queryParameters = new ArrayList<>();

        if (name != null) {
            queryParameters.add(new BasicNameValuePair("name", name));
        }

        if (disciplines.size() > 0) {
            StringBuilder disciplinesString = new StringBuilder();
            for (int i = 0; i < disciplines.size(); i++) {
                if (i == 0)
                    disciplinesString.append(disciplines.get(i));
                else
                    disciplinesString.append(",").append(disciplines.get(i));
            }

            queryParameters.add(new BasicNameValuePair("disciplines", disciplinesString.toString()));
        }

        if (statuses.size() > 0) {
            StringBuilder statusString = new StringBuilder();
            for (int i = 0; i < statuses.size(); i++) {
                if (i == 0)
                    statusString.append(statuses.get(i));
                else
                    statusString.append(",").append(statuses.get(i));
            }

            queryParameters.add(new BasicNameValuePair("statuses", statusString.toString()));
        }

        if (scheduledBefore != null) {
            queryParameters.add(new BasicNameValuePair("scheduled_before", scheduledBefore.toString()));
        }

        if (scheduledAfter != null) {
            queryParameters.add(new BasicNameValuePair("scheduled_after", scheduledAfter.toString()));
        }

        if (countries.size() > 0) {
            StringBuilder countriesString = new StringBuilder();
            for (int i = 0; i < countries.size(); i++) {
                if (i == 0)
                    countriesString.append(countries.get(i));
                else
                    countriesString.append(",").append(countries.get(i));
            }

            queryParameters.add(new BasicNameValuePair("countries", countriesString.toString()));
        }

        if (platforms.size() > 0) {
            StringBuilder platformsString = new StringBuilder();
            for (int i = 0; i < platforms.size(); i++) {
                if (i == 0)
                    platformsString.append(platforms.get(i));
                else
                    platformsString.append(",").append(platforms.get(i));
            }

            queryParameters.add(new BasicNameValuePair("platforms", platformsString.toString()));
        }

        if (isOnline != null) {
            queryParameters.add(new BasicNameValuePair("is_online", isOnline ? "1" : "0"));
        }

        if (sort != null) {
            if (sort == Sorting.SCHEDULED_ASCENDING)
                queryParameters.add(new BasicNameValuePair("sort", "scheduled_asc"));
            else if (sort == Sorting.SCHEDULED_DESCENDING)
                queryParameters.add(new BasicNameValuePair("sort", "scheduled_desc"));
        }

        return "?" + URLEncodedUtils.format(queryParameters, "UTF-8");
    }

    public enum Sorting {
        SCHEDULED_ASCENDING, SCHEDULED_DESCENDING
    }
}
