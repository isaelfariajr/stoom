package br.com.stoom.apiEndereco.validators;

import static br.com.stoom.apiEndereco.error.MessageCodes.REQUIRED_FIELD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.google.common.base.Strings;

import br.com.stoom.apiEndereco.config.Dictionary;
import br.com.stoom.apiEndereco.dto.EnderecoDTO;
import br.com.stoom.apiEndereco.error.ErrorMessage;

@Component
public class EnderecoValidator {

	 private final Dictionary dictionary;
	 
	 @Autowired
	 public EnderecoValidator (final Dictionary dictionary) {
		 this.dictionary = dictionary;
	 }
	 
	 public List<ErrorMessage> validatePost(final EnderecoDTO enderecoDto){
		 
		 final List<ErrorMessage> listErrors = Lists.newArrayList();
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getStreetName())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "streetName"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getNumber().toString())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "number"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getNeighbourhood())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "neighbourhood"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getCity())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "city"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getState())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "state"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getCountry())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "country"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getZipcode().toString())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "zipcode"));
	     }
		 
		 return listErrors;
	 }
	 
	 public List<ErrorMessage> validatePatchEndereco(final EnderecoDTO enderecoDto){
		 
		 final List<ErrorMessage> listErrors = Lists.newArrayList();
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getId().toString())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "id"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getStreetName())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "streetName"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getNumber().toString())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "number"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getNeighbourhood())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "neighbourhood"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getCity())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "city"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getState())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "state"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getCountry())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "country"));
	     }
		 
		 if(Strings.isNullOrEmpty(enderecoDto.getZipcode().toString())) {
	            listErrors.add(dictionary.getMessage(REQUIRED_FIELD, "zipcode"));
	     }
		 
		 return listErrors;
	 }
	 
}
