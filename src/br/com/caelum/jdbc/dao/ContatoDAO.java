package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;


public class ContatoDAO {
	
	//Atributos
	private Connection connection;
	private final String tableName = "contatos"; 
	
	//construtor
	public ContatoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	
	//métodos
	
	//método que adiciona o contato no banco de dados
	/**
	 * 
	 * @param contato Objeto do tipo contato a ser inserido na tabela do bd
	 */
	public void adiciona(Contato contato) {
		String sqlQry = "INSERT INTO " + this.tableName + "(nome, email, endereco, telefone) " +
				"VALUES(?, ?, ?, ?)";
		try {
			//prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sqlQry);
			//seta os valores
			stmt.setString(1, contato.getNome().toUpperCase());
			stmt.setString(2, contato.getEmail().toLowerCase());
			stmt.setString(3, contato.getEndereco().toUpperCase());
			stmt.setString(4, contato.getTelefone());
			
			//executa
			stmt.execute();
			stmt.close();
			System.out.println("Dados gravados com sucesso");
			
		} catch(SQLException e) {			
			throw new DAOException();
		}
	}
	
	//método que gera a lista dos contatos persistidos no bd
	/**
	 * 
	 * @return Lista de contatos da tabela contatos do bd
	 * @since 26/10/2013
	 */
	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement(
					"SELECT * FROM " + this.tableName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				contato.setTelefone(rs.getString("telefone"));
				
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch(SQLException e) {
			throw new DAOException();
		}			
	}
	
	//método que retorna um contato
	/**
	 * 
	 * @param id Código do contato na tabela
	 * @return Contato solicitado
	 * @throws SQLException Lança exceção caso haja algum erro no SQL
	 * @since 26/10/2013
	 */
	public Contato pesquisar(Long id) throws SQLException {
		//query à executar
		String sqlQry = "SELECT * FROM " + this.tableName + " WHERE id = " + id;
		//prepared statement 
		PreparedStatement stmt = this.connection.prepareStatement(sqlQry);
		//retorna o resultado da pesquisa
		ResultSet rs = stmt.executeQuery();
		rs.next();
		//cria um novo contato
		Contato contato = new Contato();		
		contato.setId(rs.getLong("id"));
		contato.setNome(rs.getString("nome"));
		contato.setEmail(rs.getString("email"));
		contato.setEndereco(rs.getString("endereco"));
		contato.setTelefone(rs.getString("telefone"));
		
		return contato;
	}
	
	//método que altera o contato
	/**
	 * 
	 * @param contato Objeto contato a ser alterado
	 * @throws SQLException Lança a exceção caso haja algum problema no SQL
	 * @since 26/10/2013
	 */
	public void altera(Contato contato) throws SQLException {
		String sqlQry = "UPDATE " + this.tableName + " SET nome = ?, email = ?," +
				"endereco = ?, telefone = ? WHERE id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sqlQry);
			stmt.setString(1, contato.getNome().toUpperCase());
			stmt.setString(2, contato.getEmail().toLowerCase());
			stmt.setString(3, contato.getEndereco().toUpperCase());
			stmt.setString(4, contato.getTelefone());
			stmt.setLong(5, contato.getId());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			System.out.println(e);
			throw new DAOException();
		}		
	}
	
	//método para deletar o contato
	/**
	 * 
	 * @param contato Objeto do tipo Contato a ser excluido
	 * @throws SQLException Caso haja algum problema no SQL, lança uma exceção
	 * @since 26/10/2013
	 */
	public void remove(Contato contato) throws SQLException {
		String sqlQry = "DELETE FROM " + this.tableName + " WHERE id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sqlQry);
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			System.out.println(e);
			throw new DAOException();
		}		
	} 	
}
