package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocal implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		String path = (String)session.getAttribute("url");
		//request.getRequestDispatcher(path).forward(request, response);
		
		response.sendRedirect("Controller?command="+path);
	
		
	}
	

}
