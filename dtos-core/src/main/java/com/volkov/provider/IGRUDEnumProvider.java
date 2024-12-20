package com.volkov.provider;

import java.io.Serializable;

public interface IGRUDEnumProvider<> {

    String selectQuery = "";

    String saveQuery = "";

    String getSelectQuery();

    String getSaveQuery();

    String getUpdateQuery();

    String getDeleteQuery();
}
