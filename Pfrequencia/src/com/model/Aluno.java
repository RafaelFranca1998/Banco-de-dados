package com.model;

/**
 * @author Rafael_Cruz
 *
 */
public class Aluno {
	private int id;
	private String nome;
	private String email;
	private int frequencia;
// GETTERS E SETTERS	
//-------------------------------------------------------------------------------
	/**
	 * Retorna o ID
	 * @return id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Modifica o ID.
	 * @param id a ser Modificado
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Retorna o Nome.
	 * @return nome.
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Modifica o nome.
	 * @param nome a ser Modificado.
	 */
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Retorna o Email.
	 * @return email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Modifica o Email.
	 * @param email a ser Modificado.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Retorna a Frequencia.
	 * @return frequencia.
	 */
	public int getFrequencia() {
		return frequencia;
	}
	/**
	 * Modifica a Frequencia.
	 * @param frequencia a ser Modificada.
	 */
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
//-------------------------------------------------------------------------------	
	@Override
	/**
	 * Retorna Todos os cmpos da tabela.
	 */
	public String toString() {
		return "<<<<<Tabela Teste>>>>>"+"\n"+" id= " + id + "\n nome= " + nome + "\n email= " + email + "\n frequencia= " + frequencia ;
	}
	


}
