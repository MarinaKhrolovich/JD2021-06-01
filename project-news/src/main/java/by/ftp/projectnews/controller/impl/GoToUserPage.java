package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToUserPage implements Command {

	private static final String USERPROFILE_JSP = "/WEB-INF/jsp/userprofile.jsp";
	private static final String URL = "url";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = USERPROFILE_JSP;
		request.getSession(true).setAttribute(URL, CommandName.GO_TO_USER_PAGE.toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	
}
