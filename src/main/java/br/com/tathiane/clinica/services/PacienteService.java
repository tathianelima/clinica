package br.com.tathiane.clinica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.tathiane.clinica.domain.Paciente;
import br.com.tathiane.clinica.repositories.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	public Paciente find(Integer id) {
		Optional<Paciente> pac = repo.findById(id);
		return pac.orElse(null);
	}
	
	public List<Paciente> findAll(){
		return repo.findAll();
	}
	
	public Paciente insert(Paciente pac) {
		pac.setId(null);
		return repo.save(pac); 
	}
	
	public Paciente update(Paciente pac) {
		find(pac.getId()); // verifica se esse paciente existe
		return repo.save(pac);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	
	//Page: do sprig Data - controla a quantidade de registros a ser retornado do banco
	public Page<Paciente> findPage(Integer page,Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
}
