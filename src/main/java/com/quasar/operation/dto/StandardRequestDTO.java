package com.quasar.operation.dto;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardRequestDTO implements Serializable {

	private static final long serialVersionUID = -7728909529352461992L;
	
	@NotNull
	@NotEmpty
	private Collection<SatelliteDTO> satellites;
}
