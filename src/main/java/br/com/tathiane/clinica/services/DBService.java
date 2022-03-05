package br.com.tathiane.clinica.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tathiane.clinica.domain.Paciente;
import br.com.tathiane.clinica.repositories.PacienteRepository;

//dados que serão carregados para o banco na base teste

@Service
public class DBService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void instantiateTestDatabase() throws ParseException {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Paciente ana = new Paciente(null, "44963089831", "Ana", sdf.parse("12/01/1995"), 2.2, 5.2, "SP");
		Paciente pedro = new Paciente(null, "44963089832", "Pedro", sdf.parse("12/01/1996"), 2.3, 3.6, "MG");
		Paciente joao = new Paciente(null, "44963089831", "Joao", sdf.parse("12/01/1995"), 2.2, 5.2, "SP");
		Paciente jose = new Paciente(null, "44963089832", "Jose", sdf.parse("12/01/1996"), 2.3, 3.6, "MG");
		Paciente ivo = new Paciente(null, "44963089831", "Ivo", sdf.parse("12/01/1995"), 2.2, 5.2, "SP");
		Paciente marcos = new Paciente(null, "44963089832", "Marcos", sdf.parse("12/01/1996"), 2.3, 3.6, "MG");
		Paciente sonia = new Paciente(null, "44963089831", "Sonia", sdf.parse("12/01/1995"), 2.2, 5.2, "SP");
		Paciente izabel = new Paciente(null, "44963089832", "Izabel", sdf.parse("12/01/1996"), 2.3, 3.6, "MG");
		
		pacienteRepository.saveAll(Arrays.asList(ana, pedro, joao, jose, ivo, marcos, sonia, izabel));
		
	}
}
