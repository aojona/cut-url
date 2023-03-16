package ru.kirill.cuturl.mapper;

public interface Mapper<E, R1, R2> {

    R1 mapToResponse(E entity);

    E mapToEntity(R2 request);
}
