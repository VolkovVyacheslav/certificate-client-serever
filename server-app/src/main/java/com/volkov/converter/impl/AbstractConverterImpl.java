package com.volkov.converter.impl;

import com.sun.rowset.internal.Row;
import com.volkov.converter.AbstractConverter;
import com.volkov.dto.AbstractDTO;
import com.volkov.entity.AbstractEntity;

import javax.sql.RowSet;
import javax.sql.RowSetInternal;
import javax.sql.RowSetReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractConverterImpl<ENTITY extends AbstractEntity, DTO extends AbstractDTO>
        implements AbstractConverter<ENTITY, DTO> {

    @Override
    public ENTITY convertFromResultSet(ResultSet rs) {
        ENTITY entity = (ENTITY) new AbstractEntity(){};
        try {
            if (null != rs && rs.first()) {
                entity.setId(UUID.fromString(rs.getString("id")));
                entity.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public ENTITY convertFromDTO(DTO dto) {
        ENTITY entity = (ENTITY) new AbstractEntity(){};
        entity.setId(null != dto.getId() ? dto.getId() : null);
        entity.setName(null != dto.getName() ? dto.getName() : null);
        return entity;
    }

    @Override
    public DTO convertFromEntity(ENTITY entity) {
        DTO dto = (DTO) new AbstractDTO(){};
        if( null != entity ) {
            dto.setId(null != entity.getId() ? entity.getId() : null);
            dto.setName(null != entity.getName() ? entity.getName() : null);
        }
        return dto;
    }

    List<ENTITY> convertListFromResultSet(ResultSet rs) {
        List<ENTITY> entities = new ArrayList<>();
        try {
            if (null != rs && rs.first()) {
                for (int i = 0; i < rs.getFetchSize(); i++) {
                   entities.add(convertFromResultSet(rs.getArray(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
