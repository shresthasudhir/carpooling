package wap.carpooling.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import wap.carpooling.helper.PostHelper;

/**
 * Servlet implementation class GetTakePost
 */
@WebServlet("/GetTakePost")
public class GetTakePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTakePost() {
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
		PostHelper offerPostHelper = new PostHelper();
		
		try{
			Gson gson=new Gson();
			String jsonData = gson.toJson(offerPostHelper.getTakeRide25Post());
			//System.out.println(jsonData);
			out.println("{\"JSONDATA2\":"+jsonData+"}");
			

		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
		
	}

}
