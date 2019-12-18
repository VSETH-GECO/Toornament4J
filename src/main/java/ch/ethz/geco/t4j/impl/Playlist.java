package ch.ethz.geco.t4j.impl;

import ch.ethz.geco.t4j.internal.Endpoints;
import ch.ethz.geco.t4j.internal.Requests;
import ch.ethz.geco.t4j.internal.JsonMapper;
import ch.ethz.geco.t4j.internal.json.TournamentObject;
import ch.ethz.geco.t4j.obj.IPlaylist;
import ch.ethz.geco.t4j.obj.IToornamentClient;
import ch.ethz.geco.t4j.obj.ITournament;
import ch.ethz.geco.t4j.util.Toornament4JException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements IPlaylist {
    private final long id;
    private final String name;
    private final String description;
    private final IToornamentClient client;

    /**
     * The requests holder object.
     */
    public final Requests REQUESTS;

    public Playlist(IToornamentClient client, long id, String name, String description) {
        this.client = client;
        this.id = id;
        this.name = name;
        this.description = description;

        REQUESTS = new Requests(client);
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
    public String getDescription() {
        return description;
    }

    @Override
    public List<ITournament> getTournaments(int from, int to) throws Toornament4JException {
        if (to - from >= 50)
            throw new Toornament4JException("Range too big, you can only request 50 disciplines at once.");

        List<TournamentObject> tournamentObjects = REQUESTS.GET.makeRequest(Endpoints.VIEWER + "/playlist/" + id + "/tournaments",
                new TypeReference<List<TournamentObject>>() {
                }, new BasicNameValuePair("Range", "tournaments=" + from + "-" + to));

        if (tournamentObjects == null)
            return null;

        List<ITournament> tournaments = new ArrayList<>();

        tournamentObjects.forEach(tournamentObject -> tournaments.add(JsonMapper.getTournamentFromJSON(client, tournamentObject)));

        return tournaments;
    }

    @Override
    public IToornamentClient getClient() {
        return client;
    }
}
