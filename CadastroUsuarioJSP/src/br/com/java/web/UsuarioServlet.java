package br.com.java.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.java.model.Usuario;
import br.com.java.model.dao.UsuarioDAO;

public class UsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/novo":
				mostrarNovoForm(request, response);
				break;
			case "/adcionar":
				inserirUsuario(request, response);
				break;
			case "/excluir":
				deletarUsuario(request, response);
				break;
			case "/editar":
				mostrarEditForm(request, response);
				break;
			case "/atualizar":
				atualizarUsuario(request, response);
				break;
			default:
				listarUsuario(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void mostrarNovoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		int telefone = Integer.parseInt (request.getParameter("telefone"));
		String nacionalidade = request.getParameter("nacionalidade");
		Usuario novoUsuario = new Usuario(nome, email, telefone, nacionalidade);
		dao.inserirUsuario(novoUsuario);
		response.sendRedirect("listar");
	}

	private void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		dao.deletarUsuario(id);
		response.sendRedirect("listar");
	}

	private void mostrarEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario existingUser = dao.selecionarUsuario(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-form.jsp");
		request.setAttribute("usuario", existingUser);
		dispatcher.forward(request, response);
	}

	private void atualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("name");
		String email = request.getParameter("email");
		int telefone = Integer.parseInt (request.getParameter("telefone"));
		String nacionalidade = request.getParameter("nacionalidade");

		Usuario book = new Usuario(id, nome, email, telefone, nacionalidade);
		dao.atualizarUsuario(book);
		response.sendRedirect("list");
		
	}

	private void listarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		List<Usuario> listarUsuario = dao.selecionarTodosUsuarios();
		request.setAttribute("listarUsuario", listarUsuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-lista.jsp");
		dispatcher.forward(request, response);
	}

}
