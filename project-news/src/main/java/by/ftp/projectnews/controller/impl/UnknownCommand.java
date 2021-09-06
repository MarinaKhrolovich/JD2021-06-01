package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command {

	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String URL = "url";
	private static final String MESSAGE = "message";
	private static final String MESSAGE_ERROR = "Unknown command!";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = ERROR_JSP;
		request.setAttribute(MESSAGE, MESSAGE_ERROR);
		request.getSession(true).setAttribute(URL, CommandName.UNKNOWN_COMMAND.toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
