package com.microservice.utils.core.objects;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class IdentifierValueObject implements Serializable {

    protected final UUID value;

    protected IdentifierValueObject(UUID value) {
        this.value = value;
    }

    protected IdentifierValueObject() {
        this.value = null;
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IdentifierValueObject instance) {
            return Objects.equals(value, instance.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
