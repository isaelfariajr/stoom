package br.com.stoom.apiEndereco.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.stoom.apiEndereco.data.model.Endereco;
import br.com.stoom.apiEndereco.data.repository.EnderecoRepository;
import br.com.stoom.apiEndereco.dto.EnderecoDTO;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTests {
	
	private static final Long ID = 1L;
	
	@Mock
	private EnderecoService endService;
	
	@Mock
	private EnderecoRepository endRepository; 
	
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
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test()
	public void addEnderecoError() {
		Endereco endereco = getEndereco();
		endereco.setId(ID);
		
		given(endRepository.save(endereco)).willReturn(endereco);
		
		Assert.assertNotNull(endereco.getId());
		Assert.assertEquals(ID,endereco.getId()); 		
	}
	
	@Test
	public void findEndereco() {
		Endereco endereco = getEndereco();
		endereco.setId(ID);
		
		given(endService.find(anyLong())).willReturn(Optional.of(endereco));
		
		Assert.assertNotNull(endereco.getId());
		Assert.assertEquals(ID,endereco.getId());
	}
	
	@Test
	public void deleteEndereco(){
		
		Endereco endereco = getEndereco();
		endereco.setId(ID);
		given(endRepository.findById(anyLong())).willReturn(Optional.of(endereco));
		
		endService.delete(ID);
	}	
	
	@Test
	public void UpdateEndereco() {
		Endereco endereco = getEndereco();
		endereco.setId(ID);
		
		EnderecoDTO dto = mappingDTO(endereco);
		endereco.setStreetName("Rua da Maria e João");
		
		doReturn(endereco).when(endService).update(ID, dto);
		
		Assert.assertNotNull(endereco.getStreetName());
		Assert.assertEquals("Rua da Maria e João",endereco.getStreetName()); 
	}
	
	
}
