package com.quasar.operation.service_impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.quasar.operation.dto.PositionDTO;
import com.quasar.operation.exception.LocationException;

@SpringBootTest
public class TrilaterationServiceImplTest {
	
	private static final double[] DISTANCES = {100.0, 115.5, 142.7};
	private static final double[][] POSITIONS = {{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
	private static final String EXPECTED_ERR_MSG_POSITIONS = "Three positions are expected";
	private static final String EXPECTED_ERR_MSG_DISTANCES = "Three distances are expected";
	private static final String EXPECTED_ERR_MSG_X_Y = "Positions expect X and Y";
	private static final PositionDTO EXPECTED_POINT = PositionDTO
														.builder()
														.x(-58.315252587138595)
														.y(-69.55141837312165)
														.build();
	
	@Autowired
	TrilaterationServiceImpl service;
	
	@Test
	public void whenAssertingEqualityXandYExpected() throws LocationException {
		PositionDTO resultPoint = service.getLocation(DISTANCES, POSITIONS);
		assertEquals(EXPECTED_POINT, resultPoint);
	}
	
	@Test
	public void whenAssertingEqualityErrorAndErrorMessageThreeDistances() throws LocationException {
		double[] distances = {10.0};
		Exception exception = assertThrows(LocationException.class, () -> {
			service.getLocation(distances, POSITIONS) ;
		});
		assertEquals(EXPECTED_ERR_MSG_DISTANCES, exception.getMessage());
	}
	
	@Test
	public void whenAssertingEqualityErrorAndErrorMessageThreePositions() throws LocationException {
		double[][] positions = {{10.0,12.3}, {}};
		Exception exception = assertThrows(LocationException.class, () -> {
			service.getLocation(DISTANCES, positions) ;
		});
		assertEquals(EXPECTED_ERR_MSG_POSITIONS, exception.getMessage());
	}
	
	@Test
	public void whenAssertingEqualityErrorAndErrorMessagePositionsXandY() throws LocationException {
		double[][] positions = {{10.0,12.3}, {10.2, 11.3}, {11.0} };
		Exception exception = assertThrows(LocationException.class, () -> {
			service.getLocation(DISTANCES, positions) ;
		});
		assertEquals(EXPECTED_ERR_MSG_X_Y, exception.getMessage());
	}
}
