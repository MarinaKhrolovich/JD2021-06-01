package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import by.ftp.projectnews.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignIn implements Command{

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = userService.authorization("", "");
			
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			
			
			//request.setAttribute("message", "fgfgfgfg");
			
			response.sendRedirect("Controller?command=go_to_auth_user_page");
			
			
		} catch (ServiceException e) {
			// log
			// path = "error.jsp";
		}
		
		
	}
}
