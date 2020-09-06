package br.com.stoom.apiEndereco.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.stoom.apiEndereco.data.model.Endereco;
import br.com.stoom.apiEndereco.dto.EnderecoDTO;
import br.com.stoom.apiEndereco.error.ErrorMessage;
import br.com.stoom.apiEndereco.service.EnderecoService;
import br.com.stoom.apiEndereco.validators.EnderecoValidator;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EnderecoController.class)
public class EnderecoControllerTests {

	private static final Long ID = 1L;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ObjectMapper objectMapper;
	
	@MockBean
	private EnderecoValidator enderecoValidator;
	
	@MockBean
	private EnderecoService endService;
	
	private Endereco getEndereco() {
        
		Endereco endereco = new Endereco();
        endereco.setStreetName("Rua da Maria");
        endereco.setNumber(123L);
        endereco.setComplement("ND");
        endereco.setNeighbourhood("Bairro Maria");
        endereco.setCity("Campinas");
        endereco.setState("SP");
        endereco.setCountry("Brasil");
        endereco.setZipcode(13013000L);
        endereco.setLatitude(null);
        endereco.setLongitude(null);
        
        return endereco;
    }	
	
	private EnderecoDTO mappingDTO(Endereco endereco) {
		
		EnderecoDTO dto = new EnderecoDTO();
		
		dto.setId(endereco.getId());
		dto.setStreetName(endereco.getStreetName());
		dto.setNumber(endereco.getNumber());
		dto.setComplement(endereco.getComplement());
		dto.setNeighbourhood(endereco.getNeighbourhood());
		dto.setCity(endereco.getCity());
		dto.setState(endereco.getState());
		dto.setCountry(endereco.getCountry());
		dto.setZipcode(endereco.getZipcode());
		dto.setLatitude(endereco.getLatitude());
		dto.setLongitude(endereco.getLongitude());
		
		return dto;
	}		
	
	@Test
	public void findValido() throws Exception{
		
		Endereco endereco = getEndereco();
		endereco.setId(ID);
		
		doReturn(Optional.of(endereco)).when(endService).find(ID);
		
		mockMvc.perform( MockMvcRequestBuilders
				.get("/endereco/{id}", "1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void findInvalido() throws Exception {
		
		mockMvc.perform( MockMvcRequestBuilders
				.get("/endereco/{id}","1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteValido() throws Exception {
		
		Endereco endereco = getEndereco();	
		endereco.setId(ID);
		
		doReturn(Optional.of(endereco)).when(endService).find(ID);
		
		mockMvc.perform( MockMvcRequestBuilders
				.delete("/endereco/{id}", "1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteInvalido() throws Exception {
		
		mockMvc.perform( MockMvcRequestBuilders
				.delete("/endereco/{id}", "1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void PatchValido() throws Exception {
		
		Endereco endereco = getEndereco();
		endereco.setId(ID);
		endereco.setStreetName("Rua da Maria e João");
		EnderecoDTO dto = new EnderecoDTO();
		dto = mappingDTO(endereco);
		
		List<ErrorMessage> listErrors = Lists.newArrayList();
		doReturn(listErrors).when(enderecoValidator).validatePatchEndereco(ID, dto);
		doReturn(endereco).when(endService).update(ID, dto);
		
		mockMvc.perform(patch("/beer/{idBeer}", "1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void PatchInvalido() throws Exception {
		
		Endereco endereco = getEndereco();
		endereco.setId(ID);
		endereco.setStreetName("Rua da Maria e João");
		EnderecoDTO dto = new EnderecoDTO();
		dto = mappingDTO(endereco);

		List<ErrorMessage> listErrors = Lists.newArrayList();
		ErrorMessage erro = new ErrorMessage("1", "erro");
		listErrors.add(erro);

		doReturn(listErrors).when(enderecoValidator).validatePatchEndereco(ID, dto);

		MvcResult mvcResult = mockMvc.perform(patch("/endereco/{id}", "1")						
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(endereco)))
				.andExpect(status().isPreconditionFailed())
				.andReturn();

		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		String expectedResponseBody =
				objectMapper.writeValueAsString(listErrors);
		assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
	}
	
	
	@Test
	public void addValido() throws JsonProcessingException, Exception {
	
		Endereco endereco = getEndereco();
		
		mockMvc.perform(post("/endereco").accept("application/json")
		        .contentType("application/json")
				.content(objectMapper.writeValueAsString(endereco)))
				.andExpect(status().isCreated());
	}
	
	
}
