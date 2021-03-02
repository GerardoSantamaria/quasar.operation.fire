package com.quasar.operation.service_impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quasar.operation.dto.SatelliteDTO;
import com.quasar.operation.entity.Satellite;
import com.quasar.operation.exception.SatelliteException;
import com.quasar.operation.repository.SatelliteRepository;
import com.quasar.operation.service.SatelliteServie;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SatelliteServiceImpl implements SatelliteServie {
	
	SatelliteRepository satelliteRepository;

	@Override
	public Satellite findByName(String name) {
		return satelliteRepository.findByName(normalizeName(name));
	}
	
	@Override
	public Satellite save(Satellite satellite) {
		return satelliteRepository.save(satellite);
	}
	
	@Override
	public Satellite updateStatellite(SatelliteDTO satelliteDTO) throws SatelliteException{
		Satellite satellite = findByName(satelliteDTO.getName());
		
		if(satellite == null) {
			throw new SatelliteException("Not found satellite with name: " + satelliteDTO.getName());
		}
		
		satellite.setLastDistance(satelliteDTO.getDistance());
		satellite.setLastMessage(satelliteDTO.getMessage());
		
		return save(satellite);
	}

	@Override
	public List<Satellite> getAll() {
		return satelliteRepository.findAll();
	}

	private String normalizeName(String name) {
		return name.toUpperCase();
	}
}
