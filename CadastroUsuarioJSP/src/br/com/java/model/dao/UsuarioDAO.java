package br.com.java.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.java.model.Usuario;

public class UsuarioDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/dbusermaster?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERIR_USUARIO_SQL = "INSERT INTO usuarios" + "  (nome, email, telefone, nacionalidade) VALUES " + " (?, ?, ?, ?);";
	private static final String SELECIONE_USUARIO_POR_ID = "select id, nome, email, telefone, nacionalidade from usuarios where id =?";
	private static final String SELECIONE_TODOS_USUARIO = "select * from usuarios";
	private static final String DELETAR_USUARIO_SQL = "delete from usuarios where id = ?;";
	private static final String ATUALIZAR_USUARIO_SQL = "update usuarios set nome = ?,email= ?, telefone =?, nacionalidade =? where id = ?;";
	
	
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
	
	public void inserirUsuario (Usuario usuario) {
		
		System.out.println(INSERIR_USUARIO_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_USUARIO_SQL)) {
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setInt   (3, usuario.getTelefone());
			preparedStatement.setString(4, usuario.getNacionalidade());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Usuario selecionarUsuario (int id) {
		
		Usuario usuario= null;
		try (Connection connection = getConnection();
		// Step 2:Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECIONE_USUARIO_POR_ID);) {
		preparedStatement.setInt(1, id);
		System.out.println(preparedStatement);
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();

		// Step 4: Process the ResultSet object.
		while (rs.next()) {
			
			String name = rs.getString("name");
			String email = rs.getString("email");
			int telefone = rs.getInt("telefone");
			String nacionalidade = rs.getString("nacionalidade");
			usuario = new Usuario(id, name, email, telefone, nacionalidade);
			
		}
		
		} catch (SQLException e) {
			
			printSQLException(e);
		
		}
		return usuario;
		
	}
	
	public List<Usuario> selecionarTodosUsuarios(){
		
		List<Usuario> usuarios = new ArrayList<>();
		
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECIONE_TODOS_USUARIO);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("name");
				String email = rs.getString("email");
				int telefone = rs.getInt("telefone");
				String nacionalidade = rs.getString("nacionalidade");
				usuarios.add(new Usuario(id, nome, email, telefone, nacionalidade));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuarios;
		
	}
	
	public boolean deletarUsuario(int id) throws SQLException {
		
		boolean linhaDeletada;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETAR_USUARIO_SQL);) {
			statement.setInt(1, id);
			linhaDeletada = statement.executeUpdate() > 0;
		}
		return linhaDeletada;
		
	}
	
	public boolean atualizarUsuario(Usuario usuario) throws SQLException {
		
		boolean linhaAtualizada;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(ATUALIZAR_USUARIO_SQL);) {
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setInt   (3, usuario.getTelefone());
			statement.setString(4, usuario.getNacionalidade());
			statement.setInt   (5, usuario.getId());

			linhaAtualizada = statement.executeUpdate() > 0;
		}
		return linhaAtualizada;
		
	}
	
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
