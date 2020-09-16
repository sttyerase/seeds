package com.dbmi.seeds.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Long> {
    Optional<Producer> findByProducerShortName(String producerShortName);
} // INTERFACE
