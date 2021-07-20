package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPage implements Command {

	private static final String REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = REGISTRATION_JSP;
		request.getSession(true).setAttribute("url", path);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		
		requestDispatcher.forward(request, response);
	}

}
