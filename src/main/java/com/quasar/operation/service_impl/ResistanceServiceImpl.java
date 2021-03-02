package com.quasar.operation.service_impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quasar.operation.dto.MessagesDTO;
import com.quasar.operation.dto.PositionDTO;
import com.quasar.operation.dto.SatelliteDTO;
import com.quasar.operation.entity.Satellite;
import com.quasar.operation.exception.LocationException;
import com.quasar.operation.exception.MessageDecoderException;
import com.quasar.operation.exception.SatelliteException;
import com.quasar.operation.service.LocationService;
import com.quasar.operation.service.MessageDecoderService;
import com.quasar.operation.service.ResistanceService;
import com.quasar.operation.service.SatelliteServie;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResistanceServiceImpl implements ResistanceService {

	private LocationService locationService;
	private MessageDecoderService messageService;
	private SatelliteServie satelliteService;

	@Override
	public MessagesDTO calculatePositionAndDecodeMessage(Collection<SatelliteDTO> satellitesDTO)
			throws LocationException, MessageDecoderException, SatelliteException {
		List<Satellite> satellites = updateSatellite(satellitesDTO);
		return getMessageDTO(satellites);
	}

	@Override
	public MessagesDTO calculatePositionAndDecodeMessage() throws LocationException, MessageDecoderException {
		List<Satellite> satellites = satelliteService.getAll();
		return getMessageDTO(satellites);
	}

	private MessagesDTO getMessageDTO(List<Satellite> satellites)
			throws LocationException, MessageDecoderException {
		PositionDTO position = locationService.getLocation(getDistances(satellites),
				getPositions(satellites));
		
		String finalMessages = messageService.getMessage(getListMessages(satellites));

		return MessagesDTO.builder().position(position).message(finalMessages).build();
	}

	private double[][] getPositions(List<Satellite> satellites) {
		return satellites.stream()
				.map(statellite -> new double[] { statellite.getX(), statellite.getY() })
				.toArray(double[][]::new);
	}

	private double[] getDistances(Collection<Satellite> satellites) {
		return satellites.stream()
				.mapToDouble(satellite -> { return satellite.getLastDistance(); })
				.toArray();
	}

	private Collection<String[]> getListMessages(Collection<Satellite> satellites) {
		return satellites.stream()
				.map(satellite -> { return satellite.getLastMessage(); })
				.collect(Collectors.toList());
	}

	private List<Satellite> updateSatellite(Collection<SatelliteDTO> satellitesDTO) throws SatelliteException{
		List<Satellite> satellites = new ArrayList<Satellite>();
		
		for (SatelliteDTO dto : satellitesDTO) {
			satellites.add(satelliteService.updateStatellite(dto));
		}
		return satellites;
	}

}
