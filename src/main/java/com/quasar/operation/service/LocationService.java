package com.quasar.operation.service;

import com.quasar.operation.dto.PositionDTO;
import com.quasar.operation.exception.LocationException;

public interface LocationService {
	
	PositionDTO getLocation(double[] distances, double[][] positions) throws LocationException;
	
}
