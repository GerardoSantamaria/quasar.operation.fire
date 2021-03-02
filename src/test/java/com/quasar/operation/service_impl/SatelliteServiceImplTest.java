package com.quasar.operation.service_impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.quasar.operation.dto.SatelliteDTO;
import com.quasar.operation.entity.Satellite;
import com.quasar.operation.exception.SatelliteException;
import com.quasar.operation.repository.SatelliteRepository;

@SpringBootTest
public class SatelliteServiceImplTest {
	
	private static final String EXPECTED_NAME = "KENOBI";

	@InjectMocks
	SatelliteServiceImpl service;
	
	@Mock
	SatelliteRepository mockRepository;
	
	@Test
	public void whenFindByNameSatelliteExpectedStatelliteWithEqualityValues() {
		Satellite sta = new Satellite();
		sta.setName("KENOBI");
		
		Mockito.when(mockRepository.findByName(sta.getName()))
	      .thenReturn(sta);
		
		String nameToSearch = "kenobi";
		
		Satellite foundSatellite = service.findByName(nameToSearch);
		
		assertEquals(EXPECTED_NAME, foundSatellite.getName());
	}
	
	@Test
	public void whenUpdatSatelliteWithDTOExpectedStatelliteWithEqualityValues() throws SatelliteException{
		Satellite sta = new Satellite();
		sta.setName("KENOBI");
		
		Mockito.when(mockRepository.findByName(sta.getName()))
	      .thenReturn(sta);
		Mockito.when(mockRepository.save(sta)).thenReturn(sta);
		
		SatelliteDTO dto = SatelliteDTO
							.builder()
							.name("kenobi")
							.build();
		
		Satellite returnSatellite = service.updateStatellite(dto);
		
		assertEquals(EXPECTED_NAME, returnSatellite.getName());
	}
	
}
