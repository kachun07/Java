package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import dao.ProductDAO;

/**
 * Servlet implementation class HelloControllerServlet
 */
@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProductServlet() {
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
		String artist = request.getParameter("artist");
		String name = request.getParameter("name");
		String yearString = request.getParameter("year");
		String genre = request.getParameter("genre");
		String origin = request.getParameter("origin");
		String priceString = request.getParameter("price");

		if (artist.equals("") || name.equals("") || yearString.equals("") || genre.equals("") || origin.equals("")
				|| priceString.equals("")) {
			request.getRequestDispatcher("createProduct.jsp").forward(request, response);
		} else {

			int year = Integer.parseInt(yearString);
			Double price = Double.parseDouble(priceString);
			Product product = new Product(artist, name, year, genre, origin, price);
			ProductDAO.instance.save(product);

			request.getRequestDispatcher("index.jsp").forward(request, response);

		}
	}

}
