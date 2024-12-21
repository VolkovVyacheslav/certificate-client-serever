package com.volkov.provider;

import java.io.Serializable;

public interface IGRUDEnumProvider<E> {


    String getSelectQuery();

    String getSaveQuery();

    String getUpdateQuery();

    String getDeleteQuery();
}
