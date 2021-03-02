package com.quasar.operation.service_impl;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.quasar.operation.dto.PositionDTO;
import com.quasar.operation.exception.LocationException;
import com.quasar.operation.service.LocationService;

@Service
public class TrilaterationServiceImpl implements LocationService {

	@Override
	public PositionDTO getLocation(double[] distances, double[][] positions) throws LocationException{
		
		validateDistances(distances);
		validatePositions(positions);
		
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
												new TrilaterationFunction(positions, distances), 
												new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();
		
		double[] result = optimum.getPoint().toArray();

		return PositionDTO.builder()
					.x(result[0])
					.y(result[1])
					.build();
	}


	private void validatePositions(double[][] positions) throws LocationException{
		if(positions.length != 3) {
			throw new LocationException("Three positions are expected");
		}
		
		for (double[] position : positions) {
			if (position.length != 2) {				
				throw new LocationException("Positions expect X and Y");
			}
		}
	}

	private void validateDistances(double[] distances) throws LocationException{
		if (distances.length != 3) {
			throw new LocationException("Three distances are expected");
		}
	}

}
