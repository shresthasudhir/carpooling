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
import wap.carpooling.helper.PostHelper;

/**
 * Servlet implementation class DeletePost
 */
@WebServlet("/DeletePost")
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePost() {
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
		
		HttpSession session=request.getSession();
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		PostHelper postHelper = new PostHelper();
		LikeHelper likeHelper = new LikeHelper();
		CommentHelper commentHelper = new CommentHelper();
		boolean flag = false;
		try {
			flag = likeHelper.deleteLike(postId);

			if (flag == true) {
				flag = commentHelper.deleteComment(postId);
				
				if(flag == true){
					flag = postHelper.deletePost(postId);
					
					if(flag == true){
						Gson gson = new Gson();
						String jsonData = gson.toJson("DeleteSuccess");
						out.println("{\"JSONMESSAGE\":" + jsonData + "}");
						System.out.println("{\"JSONMESSAGE\":" + jsonData + "}");
						//session.setAttribute("userSession", session);
						//request.getRequestDispatcher("homepage.jsp").forward(request, response);
					}else{
						response.sendRedirect("errorpage.jsp");
					}
					
				}else{
					response.sendRedirect("errorpage.jsp");
				}
				
			} else {
				response.sendRedirect("errorpage.jsp");
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
	}

}
