package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
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
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			PrintWriter out = response.getWriter();
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			
		
			User user = userService.authorization(login, password);
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			
			
			//request.setAttribute("message", "fgfgfgfg");
			//out.println("Autorization completed successfully!");
			
			response.sendRedirect("Controller?command=go_to_user_page");
	
		} catch (ServiceException e) {
			// log
			String path = ERROR_JSP;
			request.setAttribute("message", "Error in the autorization");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
			
			
		}
	}
}
