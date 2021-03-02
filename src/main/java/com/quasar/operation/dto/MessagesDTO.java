package com.quasar.operation.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class MessagesDTO implements Serializable {

	private static final long serialVersionUID = -6622889120748500244L;
	
	private PositionDTO position;
	private String message;
}
