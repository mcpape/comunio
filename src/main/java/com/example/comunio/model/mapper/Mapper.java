package com.example.comunio.model.mapper;

public interface Mapper<ENTITY, DTO> {

    DTO mapToDto(ENTITY entity);

    ENTITY mapToEntity(ENTITY entity);
}
