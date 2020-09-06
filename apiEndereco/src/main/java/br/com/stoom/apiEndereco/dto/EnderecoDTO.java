package br.com.stoom.apiEndereco.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

	
private Long id;
	
	private String streetName;
	private Long number;
	private String complement;
	private String neighbourhood;
	private String city;
	private String state;
	private String country;		
	private Long zipcode;	
	private Long latitude;	
	private Long longitude;		
}
