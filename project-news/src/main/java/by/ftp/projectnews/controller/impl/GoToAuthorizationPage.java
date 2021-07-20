package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAuthorizationPage implements Command{

	private static final String AUTHORIZATION_JSP = "/WEB-INF/jsp/authorization.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = AUTHORIZATION_JSP;
		request.getSession(true).setAttribute("url", CommandName.AUTHORIZATION.name());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
