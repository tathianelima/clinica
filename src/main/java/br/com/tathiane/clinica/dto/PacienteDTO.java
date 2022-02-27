package br.com.tathiane.clinica.dto;

import java.io.Serializable;

import br.com.tathiane.clinica.domain.Paciente;


// Data Transfer Object
// Transferir somente id, nome e CPF dos pacientes para formar a lista de todos os pacientes

public class PacienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String cpf;
	private String nome;
	
	public PacienteDTO() {
	}
	
	public PacienteDTO(Paciente pac) {
		id = pac.getId();
		cpf = pac.getCpf();
		nome = pac.getNome();
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
