package br.com.stoom.apiEndereco.service;

import java.io.IOException;
import java.util.Optional;

//import org.json.JSONArray;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stoom.apiEndereco.data.model.Endereco;
import br.com.stoom.apiEndereco.data.repository.EnderecoRepository;
import br.com.stoom.apiEndereco.dto.EnderecoDTO;

@Service
@PropertySource(value = "classpath:application.yml") 
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Value("${url.maps}")
    private String url;
	
	@Value("${key.maps}")
    private String key;
	
	private Endereco getGeocodingApi(Endereco endereco) throws IOException  { 
		
		String address = endereco.getNumber().toString()+" "+
						 endereco.getStreetName()+", "+
						 endereco.getNeighbourhood()+", "+
						 endereco.getState();
		
		RestTemplate restTemplate = new RestTemplate();
		
		String googleUrl = url+address+"\"&key="+key;
		
		ResponseEntity<String> response = restTemplate.getForEntity(googleUrl, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(response.getBody());

		/* Não foi possivel dar continuidade, pois não foi fornecido uma key valida */
		
		return endereco;
	}
	
	private Endereco mapping(EnderecoDTO dto) {
		
		Endereco endereco = new Endereco();
		
		endereco.setId(dto.getId());
		endereco.setStreetName(dto.getStreetName());
		endereco.setNumber(dto.getNumber());
		endereco.setComplement(dto.getComplement());
		endereco.setNeighbourhood(dto.getNeighbourhood());
		endereco.setCity(dto.getCity());
		endereco.setState(dto.getState());
		endereco.setCountry(dto.getCountry());
		endereco.setZipcode(dto.getZipcode());
		endereco.setLatitude(dto.getLatitude());
		endereco.setLongitude(dto.getLongitude());
		
		try {
			return getGeocodingApi(endereco);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return endereco;
	}
	

	public Long add(EnderecoDTO enderecoDTO) {
		Endereco endereco  = enderecoRepository.saveAndFlush(mapping(enderecoDTO));
		return endereco.getId();
	}
	
	public Endereco update(Long id,EnderecoDTO endereco) {
		endereco.setId(id);
		return enderecoRepository.saveAndFlush(mapping(endereco));
	}
	
	public Optional<Endereco> find(Long id){
		return enderecoRepository.findById(id);
	}
	
	public void delete(Long id) {
		enderecoRepository.deleteById(id);
	}
	
}
