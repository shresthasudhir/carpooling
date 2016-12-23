package wap.carpooling.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wap.carpooling.helper.UserHelper;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		HttpSession session=request.getSession();
		String logInEmail=request.getParameter("txtUserLoginEmail");
		String logInPassword=request.getParameter("txtLoginPassword");
		UserHelper userLoginHelper = new UserHelper();
		//System.out.println(logInEmail+logInPassword);
		boolean flag=false;
		try {
			flag = userLoginHelper.checkUserLogin(logInEmail, logInPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			session.setAttribute("userSession", logInEmail);
			request.getRequestDispatcher("homepage.jsp").forward(request, response);
		}
		else{
			request.setAttribute("msgLoginFailed", "Username and Password Invalid.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
