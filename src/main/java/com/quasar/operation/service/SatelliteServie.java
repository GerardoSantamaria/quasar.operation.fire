package com.quasar.operation.service;

import java.util.List;

import com.quasar.operation.dto.SatelliteDTO;
import com.quasar.operation.entity.Satellite;
import com.quasar.operation.exception.SatelliteException;

public interface SatelliteServie {
	
	Satellite findByName(String name);
	
	Satellite save(Satellite satellite);
	
	Satellite updateStatellite(SatelliteDTO satellite) throws SatelliteException;
	
	List<Satellite> getAll();
	
}
