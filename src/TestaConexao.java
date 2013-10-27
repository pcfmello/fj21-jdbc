
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.modelo.Contato;


public class TestaConexao {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		/*Contato contato = new Contato();
		contato.setNome("paulo cesar");
		contato.setEmail("pcfmello@gmail.com");
		contato.setEndereco("Vitor Konder, 321 - Centro - Florianopolis - SC");
		contato.setTelefone("96742674");*/
		
		ContatoDAO contatoDao = new ContatoDAO();
		//contatoDao.adiciona(contato);
		/*List<Contato> contatos = contatoDao.getLista();
		
		for(Contato contato: contatos) {
			System.out.println(
					"ID:       " + contato.getId() + "\n" +
					"Nome:     " + contato.getNome() + "\n" +
					"E-mail:   " + contato.getEmail() + "\n" +
					"Endereço: " + contato.getEndereco() + "\n" +
					"Telefone: " + contato.getTelefone() + "\n"
					);					
		}*/
		
		/*Contato ct1 = contatoDao.pesquisar(1L);
		System.out.println(
				"Contato encontrado: \n" +
				"ID:   " + ct1.getId() + "\n" +
				"Nome: " + ct1.getNome() + "\n" +
				"E-mail: " + ct1.getEmail() + "\n" +
				"Endereço: " + ct1.getEndereco() + "\n" +
				"Telefone: " + ct1.getTelefone() + "\n"
				);
		
		System.out.println("Alterando o contato:");
		ct1.setNome("claudia regina rodrigues");
		ct1.setEmail("CLAUDIA@HOTMAIL.COM");
		ct1.setEndereco("RUA DOS TESTES PESADOS, 321");
		ct1.setTelefone("4896024800");
		contatoDao.altera(ct1);*/
		
		Contato ct1 = contatoDao.pesquisar(2L);
		System.out.println("\n\n");
		System.out.println(
				"Contato encontrado: \n" +
				"ID:   " + ct1.getId() + "\n" +
				"Nome: " + ct1.getNome() + "\n" +
				"E-mail: " + ct1.getEmail() + "\n" +
				"Endereço: " + ct1.getEndereco() + "\n" +
				"Telefone: " + ct1.getTelefone() + "\n"
				);
		
		contatoDao.remove(ct1);
		
		List<Contato> contatos = contatoDao.getLista();
		
		for(Contato contato: contatos) {
			System.out.println(
					"ID:       " + contato.getId() + "\n" +
					"Nome:     " + contato.getNome() + "\n" +
					"E-mail:   " + contato.getEmail() + "\n" +
					"Endereço: " + contato.getEndereco() + "\n" +
					"Telefone: " + contato.getTelefone() + "\n"
					);					
		}
	}
}
