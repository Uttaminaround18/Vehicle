package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.dao.UnderwriterDao;



@WebServlet("/admin")
public class AdminAuth extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (username.equals("admin") && password.equals("admin123")) {
			System.out.println("Welcome as Admin");
			RequestDispatcher rd = req.getRequestDispatcher("/user-list.jsp");
			rd.forward(req, resp);
			UnderwriterDao obj = new UnderwriterDao();
			
		} else {

			resp.setContentType("text/html");
			RequestDispatcher rd = req.getRequestDispatcher("/Adminlogin.jsp");
			rd.include(req, resp);
			out.print("<h3 style= 'color:red' >Wrong username or password </h3>");
		}

	}
}
