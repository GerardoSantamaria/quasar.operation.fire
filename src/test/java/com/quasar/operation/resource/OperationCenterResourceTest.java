package com.quasar.operation.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasar.operation.dto.MessagesDTO;
import com.quasar.operation.dto.PositionDTO;
import com.quasar.operation.dto.SatelliteDTO;
import com.quasar.operation.dto.StandardRequestDTO;
import com.quasar.operation.service.ResistanceService;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationCenterResourceTest {
	
	private static MessagesDTO EXPECTED_MSG_DTO;
	
	@InjectMocks
	private OperationCenterResource operationCenterResource;
	
	@Mock
	private ResistanceService resistenceService;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll
	public static void setUp() {
		PositionDTO position = PositionDTO
				.builder()
				.x(-58.315252587138595)
				.y(-69.55141837312165)
				.build();

		EXPECTED_MSG_DTO = MessagesDTO
					.builder()
					.message("este es un mensaje secreto")
					.position(position)
					.build();
	}
	
	@Test
	public void whenReturnResponseJsonWhithPositionAndMessageIsCorrect() throws Exception {
		String [] msg1 = {"este", "", "", "mensaje", ""};
		String [] msg2 = {"", "es", "", "", "secreto"};
		String [] msg3 = {"este", "", "un", "", ""};
		
		SatelliteDTO dto1 = SatelliteDTO.builder().distance(100.0).name("kenobi").message(msg1).build();
		SatelliteDTO dto2 = SatelliteDTO.builder().distance(115.5).name("skywalker").message(msg2).build();
		SatelliteDTO dto3 = SatelliteDTO.builder().distance(142.7).name("sato").message(msg3).build();
		
		List<SatelliteDTO> satellitesDTO = new ArrayList<>();
		StandardRequestDTO requestDTO = new StandardRequestDTO();
		
		satellitesDTO.add(dto1);
		satellitesDTO.add(dto2);
		satellitesDTO.add(dto3);
		requestDTO.setSatellites(satellitesDTO);
		
		doReturn(EXPECTED_MSG_DTO).when(resistenceService).calculatePositionAndDecodeMessage(Mockito.<SatelliteDTO>anyList());
		
		MvcResult result = mockMvc
							.perform(post("/quasar-operation/v1/topsecret")
								.contentType(MediaType.APPLICATION_JSON)
								.content(toString(requestDTO)))
							.andExpect(status().isOk())
							.andReturn();
		
		assertEquals(toString(EXPECTED_MSG_DTO), result.getResponse().getContentAsString());
				
	}
	
	private String toString(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
