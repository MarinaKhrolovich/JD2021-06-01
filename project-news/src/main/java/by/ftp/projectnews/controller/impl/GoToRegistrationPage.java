package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPage implements Command {

	private static final String REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";
	private static final String URL = "url";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(URL, CommandName.REGISTRATION.toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(REGISTRATION_JSP);

		requestDispatcher.forward(request, response);
	}

}
