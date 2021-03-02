package com.quasar.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quasar.operation.entity.Satellite;

public interface SatelliteRepository extends JpaRepository<Satellite, Long>{
	
	Satellite findByName(String name);
}
