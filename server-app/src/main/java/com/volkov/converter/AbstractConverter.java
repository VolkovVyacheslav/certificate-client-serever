package com.volkov.converter;

import com.volkov.dto.AbstractDTO;
import com.volkov.entity.AbstractEntity;

import javax.sql.RowSet;
import java.sql.ResultSet;

public interface AbstractConverter<ENTITY, DTO> {

    ENTITY convertFromResultSet(ResultSet rs);

    ENTITY convertFromDTO(DTO dto);

    DTO convertFromEntity(ENTITY entity);
}
