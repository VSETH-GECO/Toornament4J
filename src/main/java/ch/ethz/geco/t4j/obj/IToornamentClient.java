package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.internal.FeaturedTournamentFilter;
import ch.ethz.geco.t4j.util.ToornamentException;

import java.util.List;

public interface IToornamentClient {
    /**
     * Gets the API token the client will use to authenticate at the API.
     *
     * @return The API token.
     */
    String getAPIToken();

    /**
     * Gets the client ID the client will use to authorize at the API.
     *
     * @return The client ID.
     */
    String getClientID();

    /**
     * Gets the client secret the client will use to authorize at the API.
     *
     * @return The client secret.
     */
    String getClientSecret();

    /**
     * Gets a list of available disciplines at Toornament. Note that you can only request
     * 50 disciplines at once. The discipline numbers start at zero.
     *
     * @param from The number of the first discipline to request, inclusive.
     * @param to   The number of the last discipline to request, inclusive.
     * @return A list of available disciplines.
     * @throws ToornamentException If the requested range was too big (> 50).
     */
    List<IDiscipline> getDisciplines(int from, int to) throws ToornamentException;

    /**
     * Gets a discipline by its ID.
     *
     * @param ID the ID of the discipline.
     * @return The discipline with the given ID.
     */
    IDiscipline getDisciplineByID(String ID);

    /**
     * Gets a list of featured tournaments. Note that you can only request
     * 50 tournaments at once. The tournament numbers start at zero.
     *
     * @param from The number of the first tournament to request, inclusive.
     * @param to   The number of the last tournament to request, inclusive.
     * @return A list of featured tournaments.
     * @throws ToornamentException If the requested range was too big (> 50).
     */
    List<ITournament> getFeaturedTournaments(int from, int to) throws ToornamentException;

    /**
     * Gets a list of filtered tournaments. Note that you can only request
     * 50 tournaments at once. The tournament numbers start at zero.
     * You can pass null as filter or use {@link #getFeaturedTournaments(int, int)} if you don't
     * want to use a filter.
     *
     * @param from   The number of the first tournament to request, inclusive.
     * @param to     The number of the last tournament to request, inclusive.
     * @param filter The filter that should be applied, or null for no filter.
     * @return A list of filtered tournaments.
     * @throws ToornamentException If the requested range was too big (> 50).
     */
    List<ITournament> getFeaturedTournaments(int from, int to, FeaturedTournamentFilter filter) throws ToornamentException;

    /**
     * Gets a tournament by its ID.
     *
     * @param ID the ID of the tournament.
     * @return The tournament with the given ID.
     */
    ITournament getTournamentByID(long ID);


}
