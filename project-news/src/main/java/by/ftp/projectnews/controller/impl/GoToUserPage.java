package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToUserPage implements Command {

	private static final String USERPROFILE_JSP = "/WEB-INF/jsp/userprofile.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = USERPROFILE_JSP;
		request.getSession(true).setAttribute("url", path);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	
}
