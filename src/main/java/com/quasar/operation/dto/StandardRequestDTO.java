package com.quasar.operation.dto;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Lista de satelites con sus respectivos valores.")
public class StandardRequestDTO implements Serializable {

	private static final long serialVersionUID = -7728909529352461992L;
	
	@NotNull
	@NotEmpty
	private Collection<SatelliteDTO> satellites;
}
