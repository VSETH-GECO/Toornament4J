package ch.ethz.geco.t4j.impl;

import ch.ethz.geco.t4j.obj.IParticipant;
import ch.ethz.geco.t4j.obj.IToornamentClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Participant implements IParticipant {
    private final IToornamentClient client;
    private final long id;
    private final String name;
    private final Map<String, Object> customFields;

    public Participant(IToornamentClient client, long id, String name, Map<String, Object> customFields) {
        this.id = id;
        this.name = name;

        if (customFields == null) {
            this.customFields = new HashMap<>();
        } else {
            this.customFields = customFields;
        }

        this.client = client;
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
    public Map<String, Object> getCustomFields() {
        return customFields;
    }

    @Override
    public Optional<List<IParticipant>> getLineup() {
        return Optional.empty();
    }

    @Override
    public IToornamentClient getClient() {
        return null;
    }
}
