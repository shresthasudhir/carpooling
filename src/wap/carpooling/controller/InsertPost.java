package wap.carpooling.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wap.carpooling.helper.PostHelper;
import wap.carpooling.model.Post;

/**
 * Servlet implementation class InsertPost
 */
@WebServlet("/InsertPost")
public class InsertPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPost() {
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
		
		Post post = new Post();
		
		post.setPost(request.getParameter("txtBoxPost"));
		post.setPostType(request.getParameter("radioType"));
		post.setSource(request.getParameter("txtBoxSource"));
		post.setDestination(request.getParameter("txtBoxDestination"));
		post.setDestinationLatitude(Double.parseDouble(request.getParameter("destinationLat")));
		post.setDestinationLongitude(Double.parseDouble(request.getParameter("destinationLong")));
		
		System.out.println("Destination Latitude : "+post.getDestinationLatitude());
		System.out.println("Destination Longitude : "+post.getDestinationLongitude());
		
		HttpSession session=request.getSession();
		String userSession = (String)session.getAttribute("userSession");
		
		boolean flag=false;
		PostHelper pihelper = new PostHelper();
		try{
			flag = pihelper.insertPost(userSession, post);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		if(flag==true){
			request.setAttribute("successMessage", "Post Added Successfully");
			request.getRequestDispatcher("rides.jsp").forward(request, response);
			//response.sendRedirect("rides.jsp");
		}
		else{
			response.sendRedirect("errorpage.jsp");
		}
	}

}
