package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import by.ftp.projectnews.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command{

	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = ERROR_JSP;
		request.setAttribute("message", "Unknown command!");
		request.getSession(true).setAttribute("url", "UNKNOWN_COMMAND");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
	
}
