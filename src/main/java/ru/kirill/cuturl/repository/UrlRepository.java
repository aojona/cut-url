package ru.kirill.cuturl.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.cuturl.entity.Url;

@Repository
public interface UrlRepository extends KeyValueRepository<Url,String> {
}
