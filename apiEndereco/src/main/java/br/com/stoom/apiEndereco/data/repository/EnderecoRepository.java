package br.com.stoom.apiEndereco.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stoom.apiEndereco.data.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	public Optional<Endereco> findById(Long id);
	
}
