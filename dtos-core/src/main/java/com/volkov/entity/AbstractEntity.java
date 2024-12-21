package com.volkov.entity;


import com.volkov.enums.EntityTypeEnum;
import com.volkov.provider.IEntity;
import com.volkov.provider.IGRUDEnumProvider;
import com.volkov.provider.INameProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity<ID extends Serializable>
        implements IEntity<ID>, INameProvider, IGRUDEnumProvider {

    protected ID id;

    protected String name;

    protected EntityTypeEnum typeEnum;

    public String getName(){
        return String.valueOf(getId());
    }

    EntityTypeEnum getTypeEnum(){
        return typeEnum;
    }
}
