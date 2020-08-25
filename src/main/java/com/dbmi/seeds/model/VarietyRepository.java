package com.dbmi.seeds.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VarietyRepository extends CrudRepository<Variety, Long> {
    Optional<Variety> findByVarietyName(String varietyName);
} // INTERFACE
