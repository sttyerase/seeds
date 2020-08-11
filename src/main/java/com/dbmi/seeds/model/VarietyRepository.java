package com.dbmi.seeds.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarietyRepository extends CrudRepository<Variety, Long> { }
