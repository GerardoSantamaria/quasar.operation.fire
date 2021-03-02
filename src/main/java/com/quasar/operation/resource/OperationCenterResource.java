package com.quasar.operation.resource;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.quasar.operation.dto.MessagesDTO;
import com.quasar.operation.dto.SatelliteDTO;
import com.quasar.operation.dto.StandardRequestDTO;
import com.quasar.operation.exception.LocationException;
import com.quasar.operation.exception.MessageDecoderException;
import com.quasar.operation.exception.SatelliteException;
import com.quasar.operation.service.ResistanceService;
import com.quasar.operation.service.SatelliteServie;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/quasar-operation/v1")
@AllArgsConstructor
@Slf4j
public class OperationCenterResource {
	
	private ResistanceService resistenceService;
	private SatelliteServie satelliteService;

	@PostMapping("/topsecret")
	@ResponseStatus(value = HttpStatus.OK)
	public MessagesDTO topSecret(@Valid @RequestBody StandardRequestDTO satellites){
		try {
			MessagesDTO response = resistenceService.calculatePositionAndDecodeMessage(satellites.getSatellites());
			return response;			
		} catch (MessageDecoderException ex) {
			log.error("Error in decode message:",ex);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (LocationException ex) {
			log.error("Error in calculate position:",ex);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			log.error("Error in calculate position or message: ",ex);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/topsecret_split/{satellite_name}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateStatellite( @PathVariable("satellite_name") String satelliteName,
			@Valid @RequestBody SatelliteDTO satellite ) {
		try {
			satellite.setName(satelliteName);
			satelliteService.updateStatellite(satellite);
		} catch (SatelliteException ex) {
			log.error("Error in find satellite: ", ex);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			log.error("Error in update satellite: ", ex);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/topsecret_split")
	@ResponseStatus(value = HttpStatus.OK)
	public MessagesDTO topSecretSplit() {		
		try {
			MessagesDTO response = resistenceService.calculatePositionAndDecodeMessage();
			return response;	
		} catch (Exception ex) {
			log.error("Error in update satellite: ", ex);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error no hay suficiente información");
		}
	}

}
