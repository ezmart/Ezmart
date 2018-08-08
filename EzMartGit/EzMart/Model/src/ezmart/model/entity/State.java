package ezmart.model.entity;

import ezmart.model.base.BaseEntity;

public class State extends BaseEntity {

    private String name;
    private String initials;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
