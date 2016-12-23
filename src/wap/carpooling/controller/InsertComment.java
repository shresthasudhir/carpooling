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

/**
 * Servlet implementation class InsertComment
 */
@WebServlet("/InsertComment")
public class InsertComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertComment() {
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

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String userSession = (String) session.getAttribute("userSession");

		String comment = request.getParameter("txtBoxComment");
		int postId = Integer.parseInt(request.getParameter("postId"));

		boolean flag = false;
		CommentHelper insertCommentHelper = new CommentHelper();
		try {
			flag = insertCommentHelper.insertComment(userSession, postId, comment);

			if (flag == true) {
				Gson gson = new Gson();
				String jsonData = gson.toJson(insertCommentHelper.getPostComment(postId));
				out.println("{\"JSONDATA5\":" + jsonData + "}");
				System.out.println("{\"JSONDATA5\":" + jsonData + "}");
			} else {
				response.sendRedirect("errorpage.jsp");
			}

		} catch (Exception ex) {
			ex.getMessage();
		}

	}

}
