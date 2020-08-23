package com.dbmi.seeds.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CropRepository extends CrudRepository<Crop, Long> {
    Optional<Crop> findByCropName(String cropName);
} // INTERFACE
