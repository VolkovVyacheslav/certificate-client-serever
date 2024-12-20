package com.volkov.enums;

import com.volkov.provider.IGRUDEnumProvider;

import java.io.Serializable;

public enum EntityTypeEnum implements IGRUDEnumProvider {
    CERTIFICATE("SELECT * FROM certificate", "INSERT INTO certificate VALUES(?,?)" ,"UPDATE certificate SET name =? WHERE id=?", "DELETE FROM certificate WHERE id=?"),

    ;

    String select ;
    String insert ;
    String update ;
    String delete ;

    EntityTypeEnum(String select, String insert, String update, String delete) {
      this.select = select;
      this.insert = insert;
      this.update = update;
      this.delete = delete;
    }

    @Override
    public String getSelectQuery() {
        return this.select;
    }

    @Override
    public String getSaveQuery() {
        return this.insert;
    }

    @Override
    public String getUpdateQuery() {
        return this.update;
    }

    @Override
    public String getDeleteQuery() {
        return this.delete;
    }


}
