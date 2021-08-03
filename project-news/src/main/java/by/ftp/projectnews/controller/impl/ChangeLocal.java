package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocal implements Command{

	private static final String URL = "url";
	private static final String LOCAL = "local";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		session.setAttribute(LOCAL, request.getParameter(LOCAL));
		String path = (String)session.getAttribute(URL);
		//request.getRequestDispatcher(path).forward(request, response);
		
		response.sendRedirect("Controller?command="+path);
	
		
	}
	

}
