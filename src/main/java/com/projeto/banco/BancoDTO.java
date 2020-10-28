package com.projeto.banco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BancoDTO {
	
	private String id;
	private String nome;
	private String agencia;
	private String conta;
	
	public BancoDTO() {
		
	}

	public BancoDTO(String id, String nome, String agencia, String conta) {
		this.id = id;
		this.nome = nome;
		this.agencia = agencia;
		this.conta = conta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setBanco(String nome) {
		this.nome = nome;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}
	

}
