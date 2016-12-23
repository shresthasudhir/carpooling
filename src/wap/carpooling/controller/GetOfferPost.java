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
 * Servlet implementation class GetOfferPost
 */
@WebServlet("/GetOfferPost")
public class GetOfferPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOfferPost() {
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
			String jsonData = gson.toJson(offerPostHelper.getOfferedRide25Post());
			out.println("{\"JSONDATA\":"+jsonData+"}");
			//System.out.println(message);

		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}

}
