package ch.ethz.geco.t4j.obj;

import ch.ethz.geco.t4j.obj.base.IToornamentObject;
import ch.ethz.geco.t4j.obj.base.Identifiable;
import ch.ethz.geco.t4j.util.ToornamentException;

import java.util.List;

public interface IPlaylist extends IToornamentObject, Identifiable {
    int MAX_NAME_LENGTH = 30;
    int MAX_DESCRIPTION_LENGTH = 1500;

    /**
     * Gets the description of the playlist.
     *
     * @return The description of the playlist.
     */
    String getDescription();

    /**
     * Gets the tournaments of the playlist.
     *
     * @param from The number of the first tournament to request, inclusive.
     * @param to   The number of the last tournament to request, inclusive.
     * @return The tournaments of the playlist.
     * @throws ToornamentException If the requested range was too big (> 50).
     */
    List<ITournament> getTournaments(int from, int to) throws ToornamentException;
}
