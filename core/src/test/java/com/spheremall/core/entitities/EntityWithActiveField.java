package com.spheremall.core.entitities;

import com.spheremall.core.entities.Entity;

public class EntityWithActiveField extends Entity {

    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
