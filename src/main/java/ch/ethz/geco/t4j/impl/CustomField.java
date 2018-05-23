package ch.ethz.geco.t4j.impl;

import ch.ethz.geco.t4j.obj.ICustomField;
import ch.ethz.geco.t4j.obj.IToornamentClient;

public class CustomField implements ICustomField {
    private Long id;
    private String internalName;
    private String name;
    private Target target;
    private String type;
    private Object defaultValue;
    private Boolean isRequired;
    private Boolean isPublic;
    private Integer position;

    private IToornamentClient client;

    public CustomField(IToornamentClient client, Long id, String internalName, String name, Target target, String type, Object defaultValue, Boolean isRequired, Boolean isPublic, Integer position) {
        this.client = client;
        this.id = id;
        this.internalName = internalName;
        this.name = name;
        this.target = target;
        this.type = type;
        this.defaultValue = defaultValue;
        this.isRequired = isRequired;
        this.isPublic = isPublic;
        this.position = position;
    }

    @Override
    public Target getTarget() {
        return target;
    }

    @Override
    public String getFieldType() {
        return type;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Boolean isRequired() {
        return isRequired;
    }

    @Override
    public Boolean isPublic() {
        return isPublic;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public String getInternalName() {
        return internalName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getPosition() {
        return position;
    }
}
