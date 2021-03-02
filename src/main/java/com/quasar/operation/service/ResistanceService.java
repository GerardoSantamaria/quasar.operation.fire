package com.quasar.operation.service;

import java.util.Collection;

import com.quasar.operation.dto.MessagesDTO;
import com.quasar.operation.dto.SatelliteDTO;
import com.quasar.operation.exception.LocationException;
import com.quasar.operation.exception.MessageDecoderException;
import com.quasar.operation.exception.SatelliteException;

public interface ResistanceService {
	
	MessagesDTO calculatePositionAndDecodeMessage(Collection<SatelliteDTO> satellites) throws LocationException, MessageDecoderException, SatelliteException;
	
	MessagesDTO calculatePositionAndDecodeMessage() throws LocationException, MessageDecoderException;
}
