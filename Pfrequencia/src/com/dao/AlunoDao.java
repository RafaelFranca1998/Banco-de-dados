package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Aluno;

public class AlunoDao {

	private DataSource dataSource;
	public Connection connection;
	public PreparedStatement stmt;

	// -------------------------------------------------------------------
	/**
	 * Cria uma nova instância da Classe AlunoDao.
	 * @param datasource os dados da conexão.
	 */
	public AlunoDao(DataSource datasource) {
		this.dataSource = datasource;
	}
	// -------------------------------------------------------------------
	/**
	 * Metodo que retorna um Arraylist contendo os dados dos alunos no Banco de Dados.
	 * @return Lista com ID, Nome, Email e Frequência.
	 */
	public ArrayList<Aluno> listarAluno() {
		try {
			String SQL = "SELECT * FROM tb_aluno";
			PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			ArrayList<Aluno> Lista = new ArrayList<Aluno>();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setEmail(rs.getString("email"));
				aluno.setFrequencia(rs.getInt("frequencia"));
				Lista.add(aluno);
			}
			return Lista;
		} catch (SQLException e) {
			System.err.println("Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("ERRO GERAL: " + e.getMessage());
		}
		return null;
	}
	// -------------------------------------------------------------------
	/**
	 * Insere dados do Usuário no banco de dados.
	 * @param U os dados do usuário.
	 */
	public void create(Aluno U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("INSERT INTO tb_aluno(id,nome,email,frequencia) VALUES (?,?,?,?)");
			stmp.setInt(1, U.getId());
			stmp.setString(2, U.getNome());
			stmp.setString(3, U.getEmail());
			stmp.setInt(4, U.getFrequencia());
			stmp.executeUpdate();
			System.out.println("Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// -------------------------------------------------------------------
	/**
	 * Deleta dados baseado no ID passado como parâmetro.
	 * @param U ID a ser Deletado. 
	 */
	public void delete(Aluno U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("DELETE FROM tb_aluno WHERE id = ?");
			stmp.setInt(1, U.getId());
			stmp.executeUpdate();
			System.out.println("Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
