package br.com.stoom.apiEndereco.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.stoom.apiEndereco.data.model.Endereco;
import br.com.stoom.apiEndereco.dto.EnderecoDTO;
import br.com.stoom.apiEndereco.error.ErrorMessage;
import br.com.stoom.apiEndereco.service.EnderecoService;
import br.com.stoom.apiEndereco.validators.EnderecoValidator;

@RestController
@RequestMapping("/endereco") 
public class EnderecoController {
	
	@Autowired
	private EnderecoService endService;
	
	@Autowired
	EnderecoValidator endValidator;
	
	@RequestMapping(method = RequestMethod.POST )
	private ResponseEntity<Object> postEndereco(@RequestBody EnderecoDTO enderecoDto){
		
		final ResponseEntity<Object> response;
		final List<ErrorMessage> errorBody = endValidator.validatePost(enderecoDto); 
		
		
		if (!errorBody.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorBody);
		}else{
			Long id = endService.add(enderecoDto);
			
			final URI location = ServletUriComponentsBuilder
		            .fromCurrentServletMapping().path("/{id}").build()
		            .expand(id).toUri();
			
			final HttpHeaders headers = new HttpHeaders();
		    headers.setLocation(location);
		    
		    response = new ResponseEntity<Object>(headers,
			          HttpStatus.CREATED);
		}
		
	    return response;
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Object> findEndereco(@PathVariable("id") Long id){

		ResponseEntity<Object> response;		

		Optional<Endereco> endereco = endService.find(id);
		
		if (!endereco.isPresent()){
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
		}else {
			response = ResponseEntity.ok().body(endereco);
		}
		
		return response;
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Object> deleteEndereco(@PathVariable("id") Long id){
	
		ResponseEntity<Object> response;
		
		Optional<Endereco> endereco = endService.find(id);
		if (!endereco.isPresent()){
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			endService.delete(id);
			response = ResponseEntity.ok().body(null);
		}
		
		return response; 
	}
	
	@PatchMapping("/{id}")
	private ResponseEntity<Object> patchEndereco(@PathVariable("id") Long id,
			 @RequestBody final EnderecoDTO enderecoDto){
		
		final ResponseEntity<Object> response;
		final List<ErrorMessage> errorBody = endValidator.validatePatchEndereco(id,enderecoDto);
	
		if(!errorBody.isEmpty()) {
            response = ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorBody);
		}else {
			Endereco endereco = endService.update(id,enderecoDto);
			response = ResponseEntity.status(HttpStatus.CREATED).body(endereco);
		}
		
		return response;
	}
	


}
