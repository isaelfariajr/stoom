package br.com.stoom.apiEndereco.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="ENDERECO")
@NamedQuery(name="Endereco.findAll", query="SELECT i FROM Endereco i")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -3612945587555643297L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String streetName;
	
	@Column
	private Long number;
	
	@Column
	private String complement;
	
	@Column
	private String neighbourhood;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private String country;		
	
	@Column
	private Long zipcode;	
	
	@Column
	private Long latitude;	
	
	@Column
	private Long longitude;		
	
}
