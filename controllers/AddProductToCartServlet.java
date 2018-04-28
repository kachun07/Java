package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartLine;
import model.Product;
import model.Cart;
import model.User;
import dao.CartDAO;
import dao.CartLineDAO;
import dao.ProductDAO;

/**
 * Servlet implementation class GrabEverythingServlet
 */
@WebServlet("/AddProductToCartServlet")
public class AddProductToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductToCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		int userId = ((User) session.getAttribute("user")).getId();

		Cart cart = CartDAO.instance.getFromUserId(userId);

		String id = request.getParameter("id");
		String numberItems = request.getParameter("numberItems");

		Product p = ProductDAO.instance.get(Integer.parseInt(id));

		CartLine line = new CartLine(p, Integer.parseInt(numberItems));
		CartLineDAO.instance.save(line);
		cart.addLine(line);

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
