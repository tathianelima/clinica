package br.com.tathiane.clinica.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tathiane.clinica.domain.Paciente;
import br.com.tathiane.clinica.dto.PacienteDTO;
import br.com.tathiane.clinica.services.PacienteService;

@RestController
@RequestMapping(value="/pacientes")
public class PacienteResource {
		
	@Autowired
	private PacienteService service;
	
	//Busca paciente pelo id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Paciente> find(@PathVariable Integer id){
		Paciente pac = service.find(id);
		return ResponseEntity.ok(pac);
	}
	
	//Lista todos os pacientes (exibindo apenas id, nome e cpf pelo arquivo DTO)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PacienteDTO>> findAll() {
		List<Paciente> list = service.findAll();
		List<PacienteDTO> listDto = list.stream().map(pac -> new PacienteDTO(pac)).collect(Collectors.toList());
		//stream = percorre a lista
		//map = faz uma operação para cada elemento da lista
		//pac = apelido -> operador que realiza função anonima
		//collect = volta o stream para lista de pacientes
		return ResponseEntity.ok().body(listDto);
	}
	
	//Lista pacientes por paginação (para controlar a quantidade a ser buscada no banco)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PacienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Paciente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PacienteDTO> listDto = list.map(pac -> new PacienteDTO(pac));
		return ResponseEntity.ok().body(listDto); 
	}
	
	//Cria novo paciente
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Paciente pac){ //@RequestBody - cria um paciente com base nos dados json passados
		pac = service.insert(pac); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(pac.getId()).toUri(); //captura a uri e acrescenta o id do paciente criado
		return ResponseEntity.created(uri).build(); //atribui a uri a nova uri da resposta http 202
	}
	
	//Atualiza paciente
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Paciente pac, @PathVariable Integer id){
		pac.setId(id);
		pac = service.update(pac);
		return ResponseEntity.noContent().build();
	}
	
	//Exclui paciente
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
