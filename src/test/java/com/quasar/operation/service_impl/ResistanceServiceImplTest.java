package com.quasar.operation.service_impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.quasar.operation.dto.MessagesDTO;
import com.quasar.operation.dto.PositionDTO;
import com.quasar.operation.entity.Satellite;
import com.quasar.operation.service.LocationService;
import com.quasar.operation.service.MessageDecoderService;
import com.quasar.operation.service.SatelliteServie;

@SpringBootTest
public class ResistanceServiceImplTest {
	
	private static MessagesDTO EXPECTED_MSG_DTO;
	private static PositionDTO POSITION_DTO;
	private static Satellite KENOBI;
	private static Satellite SKYWALKER;
	private static Satellite SATO;
	private static final String[] KENOBI_MSG = {"este", "", "", "mensaje", ""};
	private static final String[] SKYWALKER_MSG = {"", "es", "", "", "secreto"};
	private static final String[] SATO_MSG= {"este", "", "un", "", ""};
	private static final double[] DISTANCES = {100.0, 115.5, 142.7};
	private static final double[][] POSITIONS = {{-500.0,-200.0}, {100.0,-100.0}, {500.0,100.0}};
	
	@InjectMocks
	ResistanceServiceImpl service;
	
	@Mock
	SatelliteServie satelliteService;
	
	@Mock
	LocationService locationService;
	
	@Mock
	MessageDecoderService messageService;
	
	@BeforeAll
	public static void setUp() {
		POSITION_DTO = PositionDTO
								.builder()
								.x(-58.315252587138595)
								.y(-69.55141837312165)
								.build();
		
		EXPECTED_MSG_DTO = MessagesDTO
							.builder()
							.message("este es un mensaje secreto")
							.position(POSITION_DTO)
							.build();
		
		
		KENOBI = new Satellite();
		KENOBI.setName("KENOBI");
		KENOBI.setX(-500.0);
		KENOBI.setY(-200.0);
		KENOBI.setLastDistance(100.0);
		KENOBI.setLastMessage(KENOBI_MSG);
		
		SKYWALKER = new Satellite();
		SKYWALKER.setName("SKYWALKER");
		SKYWALKER.setX(100.0);
		SKYWALKER.setY(-100.0);
		SKYWALKER.setLastDistance(115.5);
		SKYWALKER.setLastMessage(SKYWALKER_MSG);
		
		SATO = new Satellite();
		SATO.setName("SATO");
		SATO.setX(500.0);
		SATO.setY(100.0);
		SATO.setLastDistance(142.7);
		SATO.setLastMessage(SATO_MSG);
		
	}
	
	@Test
	public void whenMessageDtoWithoutParamReturnedIsEqualityToExpected() throws Exception {
		List<Satellite> satellites = new ArrayList<>();
		satellites.add(KENOBI);
		satellites.add(SKYWALKER);
		satellites.add(SATO);
		
		List<String[]> listMessages = new ArrayList<String[]>();
		listMessages.add(KENOBI_MSG);
		listMessages.add(SKYWALKER_MSG);
		listMessages.add(SATO_MSG);
		
		Mockito.when(locationService.getLocation(DISTANCES, POSITIONS)).thenReturn(POSITION_DTO);
		Mockito.when(satelliteService.getAll()).thenReturn(satellites);
		Mockito.when(messageService.getMessage(listMessages)).thenReturn("este es un mensaje secreto");
		
		MessagesDTO returnMessage = service.calculatePositionAndDecodeMessage();
		assertEquals(EXPECTED_MSG_DTO.getMessage(), returnMessage.getMessage());
	}
	
}
