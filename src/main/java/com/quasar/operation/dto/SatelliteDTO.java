package com.quasar.operation.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Representa el nombre del satelite, el mensaje y la distancia que envia el emisor.")
public class SatelliteDTO implements Serializable {

	private static final long serialVersionUID = 5908707485733726561L;
	
	private String name;
	
	@NotNull
	private Double distance;
	
	@NotNull
	@NotEmpty
	private String[] message;
}
