package com.dbmi.seeds.model;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> { }
