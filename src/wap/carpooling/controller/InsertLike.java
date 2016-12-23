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

import wap.carpooling.helper.CommentHelper;
import wap.carpooling.helper.LikeHelper;

/**
 * Servlet implementation class InsertLike
 */
@WebServlet("/InsertLike")
public class InsertLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertLike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String userSession = (String) session.getAttribute("userSession");

		//int postId = Integer.parseInt(request.getParameter("postId"));
		
		String postId = request.getParameter("postId");

		boolean flag = false;
		LikeHelper likeHelper = new LikeHelper();
		
		try {
			flag = likeHelper.insertLike(userSession, Integer.parseInt(postId));

			if (flag == true) {
				Gson gson = new Gson();
				String jsonData = gson.toJson("PostLiked");
				out.println("{\"JSONDATA6\":" + jsonData + "}");
				//System.out.println("{\"JSONDATA6\":" + jsonData + "}");
			} else {
				response.sendRedirect("errorpage.jsp");
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
	}

}
