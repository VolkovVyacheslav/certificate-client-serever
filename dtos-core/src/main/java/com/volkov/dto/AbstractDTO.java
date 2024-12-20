package com.volkov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDTO implements Serializable {

    private static final long serialVersionUID = 0L;

    private UUID id;

    private String name;
}
