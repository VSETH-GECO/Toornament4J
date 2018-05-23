package ch.ethz.geco.t4j.impl;

import ch.ethz.geco.t4j.obj.IParticipant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Participant implements IParticipant {
    private Long id;
    private String name;
    private Map<String, Object> customFields;

    public Participant(Long id, String name, Map<String, Object> customFields) {
        this.id = id;
        this.name = name;

        if (this.customFields == null) {
            this.customFields = new HashMap<>();
        } else {
            this.customFields = customFields;
        }
    }

    @Override
    public Optional<Long> getID() {
        return Optional.ofNullable(id);
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
}
