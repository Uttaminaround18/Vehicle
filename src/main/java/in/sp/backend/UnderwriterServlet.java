package in.sp.backend;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.bean.Underwriter;
import in.sp.dao.UnderwriterDao;

/**
 * Servlet implementation class UnderwriterServlet
 */
@WebServlet("/")
public class UnderwriterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UnderwriterDao underwriterdao;
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
	public void init() throws ServletException {
		underwriterdao = new UnderwriterDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Underwriter> listUser = underwriterdao.selectAllUnderwriters();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Underwriter existingUser = underwriterdao.selectUnderwriter(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String doj = request.getParameter("doj");
		String dob = request.getParameter("dob");
		LocalDateTime dojj = LocalDateTime.parse(doj, df);
		LocalDateTime dobb = LocalDateTime.parse(dob, df);
		Underwriter newUser = new Underwriter(name, password, dojj, dobb);
		underwriterdao.insertUnderwriter(newUser);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String doj = request.getParameter("doj");
		String dob = request.getParameter("dob");
		LocalDateTime dojj = LocalDateTime.parse(doj, df);
		LocalDateTime dobb = LocalDateTime.parse(dob, df);

		Underwriter book = new Underwriter(id, name, password, dojj, dobb);
		underwriterdao.updateUnderwriter(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		underwriterdao.deleteUnderwriter(id);
		response.sendRedirect("list");

	}
		
	}

	


