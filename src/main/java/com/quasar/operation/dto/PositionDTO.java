package com.quasar.operation.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ApiModel(description = "Tiene el valor de las posiciones de X e Y.")
public class PositionDTO implements Serializable {

	private static final long serialVersionUID = 202810949472057123L;
	
	private Double x;
	private Double y;
}
