package org.ovirtChina.enginePlugin.isoUploaderPlugin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetIsoDomain
 */
public class GetIsoDomain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String optionIsoName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetIsoDomain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		optionIsoName = request.getParameter("selectname");
		System.out.println("why :" + optionIsoName);
	}

}
