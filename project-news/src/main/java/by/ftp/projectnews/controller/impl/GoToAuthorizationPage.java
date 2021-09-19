package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAuthorizationPage implements Command {

	private static final String AUTHORIZATION_JSP = "/WEB-INF/jsp/authorization.jsp";
	private static final String URL = "url";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(URL, CommandName.AUTHORIZATION.toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(AUTHORIZATION_JSP);
		requestDispatcher.forward(request, response);
	}
}
