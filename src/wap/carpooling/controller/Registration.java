package wap.carpooling.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wap.carpooling.helper.UserHelper;
import wap.carpooling.model.User;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		HttpSession session = request.getSession();
		
		User user = new User();
		
		user.setFullName(request.getParameter("txtFullName"));
		user.setGender(request.getParameter("radioGender"));
		user.setState(request.getParameter("txtState"));
		user.setCity(request.getParameter("txtCity"));
		user.setStreet(request.getParameter("txtStreet"));
		user.setZipCode(Integer.parseInt(request.getParameter("txtZipCode")));
		user.setBirthYear(Integer.parseInt(request.getParameter("txtBirthYear")));
		String email = request.getParameter("txtEmail");
		user.setEmail(email);
		user.setPassword(request.getParameter("txtPassword"));
		
		UserHelper userRegisterHelper = new UserHelper();
		
		// System.out.println(name);
		boolean resultFlag = false;
		try {
			resultFlag = userRegisterHelper.insertUser(user);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resultFlag) {
			session.setAttribute("userSession", email);
			request.getRequestDispatcher("homepage.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
		}
	}

}
