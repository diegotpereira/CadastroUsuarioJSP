package br.com.java.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.java.model.Usuario;

public class UsuarioDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/dbusermaster?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USERS_SQL = "INSERT INTO usuarios" + "  (name, email, telefone, country) VALUES " + " (?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id, nome, email, telefone, nacionalidade from usuarios where id =?";
	private static final String SELECT_ALL_USERS = "select * from usuarios";
	private static final String DELETE_USERS_SQL = "delete from usuarios where id = ?;";
	private static final String UPDATE_USERS_SQL = "update usuarios set nome = ?,email= ?, telefone =?, nacionalidade =? where id = ?;";
	
	
	public UsuarioDAO() {}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void inserirUsuario () {}
	
	public Usuario selecionarUsuario() {
		
		Usuario usuario= null;
		return usuario;}
	
	public List<Usuario> selecionarTodosUsuarios(){
		
		List<Usuario> usuarios = new ArrayList<>();
		return usuarios;}
	
	public boolean deletarUsuarios(int id) {
		
		boolean linhaDeletada = false;
		return linhaDeletada;}
	
	public boolean atualizarUsuario(Usuario usuario) {
		
		boolean linhaAtualizada = false;
		return linhaAtualizada;}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
