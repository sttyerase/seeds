package com.dbmi.seeds.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends CrudRepository<Crop, Long> { }
