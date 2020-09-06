package br.com.stoom.apiEndereco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.apiEndereco.data.model.Endereco;
import br.com.stoom.apiEndereco.data.repository.EnderecoRepository;
import br.com.stoom.apiEndereco.dto.EnderecoDTO;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
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
		
		return endereco;
	}
	

	public Long add(EnderecoDTO enderecoDTO) {
		Endereco endereco  = enderecoRepository.saveAndFlush(mapping(enderecoDTO));
		return endereco.getId();
	}
	
	public Endereco update(EnderecoDTO endereco) {
		return enderecoRepository.saveAndFlush(mapping(endereco));
	}
	
	public Optional<Endereco> find(Long id){
		return enderecoRepository.findById(id);
	}
	
	public void delete(Long id) {
		enderecoRepository.deleteById(id);
	}
	
}
