package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	//-------------------------------------------------------------------------------------
	private String hostName;
	private int port;
	private String dataBase;
	private String userName;
	private String password;
	private Connection connection;
	//-------------------------------------------------------------------------------------
	/**
	 * Inicializa uma nova Conexão ao Banco de Dados.
	 **/
	
	public DataSource() {
		hostName = "localhost";
		port = 3306;
		dataBase = "bd_frequencia";
		userName = "root";
		password = "32612421";
		
		try {
			String url = "jdbc:mysql://" + hostName + ":" + port + "/" + dataBase+"?useSSL=false";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Conexão Efetuada");
		} catch (SQLException e) {
			System.err.println("Não Foi Possivel Conectar ao Banco de dados: "+e);
		} catch (Exception e) {
			System.err.println("Não foi possivel conectar! ERRO GERAL: "+e);
		}
	}
	
	/**
	 * Retorna uma conexão com o banco de dados.
	 * @return connection
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Fecha conexão com o banco de dados.
	 */
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Conexão Encerrada!");
		} catch (SQLException e) {
			System.err.println("Não fechou " + e.getMessage());
		} catch (Exception e) {
			System.err.println("ERRO GERAL:  " + e.getMessage());
		}
	}
//-------------------------------------------------------------------------------------
}