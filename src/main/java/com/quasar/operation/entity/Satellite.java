package com.quasar.operation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SATELLITE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Satellite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME", unique = true)
	private String name;
	
	@Column(name = "X")
	private Double x;
	
	@Column(name = "Y")
	private Double y;
	
	@Column(name = "LAST_DISTANCE")
	private Double lastDistance;
	
	@Column(name = "LAST_MESSAGE")
	private String[] lastMessage;
	
}
