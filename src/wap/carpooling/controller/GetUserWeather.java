package wap.carpooling.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import wap.carpooling.helper.UserHelper;
import wap.carpooling.model.User;

/**
 * Servlet implementation class GetUserWeather
 */
@WebServlet("/GetUserWeather")
public class GetUserWeather extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserWeather() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String userSession = (String) session.getAttribute("userSession");

		UserHelper userHelper = new UserHelper();

		try {
			User user = userHelper.getUserWeather(userSession);
			
			request.setAttribute("state", user.getState());
			request.setAttribute("city", user.getCity());
			request.setAttribute("zipcode", user.getZipCode());
			
			System.out.println(user.getState());
			System.out.println(user.getCity());
			System.out.println(user.getZipCode());
			
			request.getRequestDispatcher("weather.jsp").forward(request, response);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
