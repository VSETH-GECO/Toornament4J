package ch.ethz.geco.t4j.impl;

import ch.ethz.geco.t4j.internal.Endpoints;
import ch.ethz.geco.t4j.internal.FeaturedTournamentFilter;
import ch.ethz.geco.t4j.internal.Requests;
import ch.ethz.geco.t4j.internal.ToornamentUtils;
import ch.ethz.geco.t4j.internal.json.objects.DisciplineObject;
import ch.ethz.geco.t4j.internal.json.objects.TournamentObject;
import ch.ethz.geco.t4j.obj.IDiscipline;
import ch.ethz.geco.t4j.obj.IToornamentClient;
import ch.ethz.geco.t4j.obj.ITournament;
import ch.ethz.geco.t4j.util.ToornamentException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ToornamentClient implements IToornamentClient {
    /**
     * The API key used to access the API
     */
    private final String API_KEY;

    /**
     * The client ID used to authorize at some endpoints.
     */
    private final String CLIENT_ID;

    /**
     * The client secret used to authorize at some endpoints.
     */
    private final String CLIENT_SECRET;

    /**
     * The requests holder object.
     */
    public final Requests REQUESTS = new Requests(this);

    /**
     * Simple access login.
     *
     * @param apiKey the API key.
     */
    public ToornamentClient(String apiKey) {
        this.API_KEY = apiKey;
        this.CLIENT_ID = null;
        this.CLIENT_SECRET = null;
    }

    /**
     * Full access login.
     *
     * @param apiKey       the API key.
     * @param clientID     the client ID.
     * @param clientSecret the client secret.
     */
    public ToornamentClient(String apiKey, String clientID, String clientSecret) {
        this.API_KEY = apiKey;
        this.CLIENT_ID = clientID;
        this.CLIENT_SECRET = clientSecret;
    }

    @Override
    public String getAPIToken() {
        return API_KEY;
    }

    @Override
    public String getClientID() {
        return CLIENT_ID;
    }

    @Override
    public String getClientSecret() {
        return CLIENT_SECRET;
    }

    @Override
    public List<IDiscipline> getDisciplines(int from, int to) throws ToornamentException {
        if (to - from >= 50)
            throw new ToornamentException("Range too big, you can only request 50 disciplines at once.");

        List<DisciplineObject> disciplineObjects = REQUESTS.GET.makeRequest(Endpoints.VIEWER + "/disciplines",
                new TypeReference<List<DisciplineObject>>() {
                }, new BasicNameValuePair("Range", "disciplines=" + from + "-" + to));

        if (disciplineObjects == null)
            return null;

        List<IDiscipline> disciplines = new ArrayList<>();

        disciplineObjects.forEach(disciplineObject -> disciplines.add(ToornamentUtils.getDisciplineFromJSON(disciplineObject)));

        return disciplines;
    }

    @Override
    public IDiscipline getDisciplineByID(String ID) {
        return ToornamentUtils.getDisciplineFromJSON(REQUESTS.GET.makeRequest(Endpoints.VIEWER + "/disciplines/" + ID, DisciplineObject.class));
    }

    @Override
    public List<ITournament> getFeaturedTournaments(int from, int to) throws ToornamentException {
        return getFeaturedTournaments(from, to, null);
    }

    @Override
    public List<ITournament> getFeaturedTournaments(int from, int to, FeaturedTournamentFilter filter) throws ToornamentException {
        if (to - from >= 50)
            throw new ToornamentException("Range too big, you can only request 50 disciplines at once.");

        String queryString = "";

        if (filter != null)
            queryString = filter.getQueryString();

        List<TournamentObject> tournamentObjects = REQUESTS.GET.makeRequest(Endpoints.VIEWER + "/tournaments/featured" + queryString,
                new TypeReference<List<TournamentObject>>() {
                }, new BasicNameValuePair("Range", "tournaments=" + from + "-" + to));

        if (tournamentObjects == null)
            return null;

        List<ITournament> tournaments = new ArrayList<>();

        tournamentObjects.forEach(tournamentObject -> tournaments.add(ToornamentUtils.getTournamentFromJSON(this, tournamentObject)));

        return tournaments;
    }

    @Override
    public ITournament getTournamentByID(long ID) {
        return ToornamentUtils.getTournamentFromJSON(this, REQUESTS.GET.makeRequest(Endpoints.VIEWER + "/tournaments/" + ID, TournamentObject.class));
    }
}
