package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import by.ftp.projectnews.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthorizationUser implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();

	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String MESSAGE = "message";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			PrintWriter out = response.getWriter();
			String login = request.getParameter(LOGIN);
			String password = request.getParameter(PASSWORD);
			
		
			User user = userService.authorization(login, password);
			HttpSession session = request.getSession(true);
			session.setAttribute(USER, user);
		
			request.getSession(true).setAttribute(URL, CommandName.GO_TO_USER_PAGE.toString());
			response.sendRedirect("Controller?command=go_to_user_page&message='Autorization completed successfully!'");
	
		} catch (ServiceException e) {
			// log
			String path = ERROR_JSP;
			request.setAttribute(MESSAGE, "Error in the autorization");
			request.getSession(true).setAttribute(URL, CommandName.UNKNOWN_COMMAND.toString());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			
			
		}
	}
}
