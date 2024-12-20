package com.volkov.provider;

import java.io.Serializable;

public interface IdProvider <ID extends Serializable> {

    ID getId();

    void setId(ID id);
}
