/**
 * @author PAULO CESAR F. DE MELLO
 * @version 1.0
 */

package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//classe que monta a conexão com o banco de dados
public class ConnectionFactory {
	
	/**
	 * @param dbHost Endereço do banco de dados
	 * @param dbName Nome do banco de dados
	 * @param dbUser Nome do usuário do banco de dados
	 * @param dbPass Senha de acesso ao banco de dados
	 */
	private final String dbHost = "localhost:3306";
	private final String dbName = "fj21";
	private final String dbUser = "root";
	private final String dbPass = "";
	
	/**	
	 * @return Conexão com o banco de dados ou exceção se algo der errado
	 */
	public Connection getConnection() {
		try {			
			return DriverManager.getConnection(
					"jdbc:mysql://" + dbHost + "/" + dbName, dbUser, dbPass);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
