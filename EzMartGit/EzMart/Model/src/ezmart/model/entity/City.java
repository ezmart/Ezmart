package ezmart.model.entity;

import ezmart.model.base.BaseEntity;

public class City extends BaseEntity {

    private String name;
    private String codeIbge;
    private State state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeIbge() {
        return codeIbge;
    }

    public void setCodeIbge(String codeIbge) {
        this.codeIbge = codeIbge;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
