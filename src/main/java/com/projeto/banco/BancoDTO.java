package com.projeto.banco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BancoDTO {
	
	private String id;
	private String banco;
	private String agencia;
	private String conta;
	
	public BancoDTO() {
		
	}

	public BancoDTO(String id, String banco, String agencia, String conta) {
		this.id = id;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
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
