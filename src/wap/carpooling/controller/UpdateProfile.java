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
 * Servlet implementation class UserProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfile() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		// System.out.println("INSIDE GET");
		HttpSession session = request.getSession();
		String userSession = (String) session.getAttribute("userSession");

		UserHelper userHelper = new UserHelper();
		User user = userHelper.getUserInformation(userSession);

		request.setAttribute("fullname", user.getFullName());
		request.setAttribute("gender", user.getGender());
		request.setAttribute("state", user.getState());
		request.setAttribute("city", user.getCity());
		request.setAttribute("street", user.getStreet());
		request.setAttribute("zipcode", user.getZipCode());
		request.setAttribute("birthyear", user.getBirthYear());
		request.setAttribute("email", user.getEmail());

		request.getRequestDispatcher("update.jsp").forward(request, response);

		// response.sendRedirect(request.getContextPath() + "/rides.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// System.out.println("INSIDE POST");

		HttpSession session = request.getSession();
		String sessionMail = (String) session.getAttribute("userSession");
		
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

		UserHelper userProfileUpdateHelper = new UserHelper();
		
		boolean resultFlag = false;
		try {
			resultFlag = userProfileUpdateHelper.updateUserProfile(user,sessionMail);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resultFlag) {
			session.setAttribute("userSession", email);
			request.setAttribute("updateSuccess", "Profile Update Successful.");
			doGet(request, response);
//			request.getRequestDispatcher("update.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
		}

		// response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
