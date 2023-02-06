package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;

public abstract class Section <T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public abstract void printAll();

    public abstract T getSections();
}
