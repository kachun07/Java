package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.User;
import dao.CartDAO;
import dao.UserDAO;

/**
 * Servlet implementation class HelloControllerServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String email = request.getParameter("email");

		if (name.equals("") || password.equals("") || address.equals("") || email.equals("")) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
			
		} else {
			
			User user = new User(name, password, address, email);
			UserDAO.instance.save(user);

			Cart cart = new Cart("UserCart");
			CartDAO.instance.save(cart);

			UserDAO.instance.linkCart(user, cart);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
	}

}
