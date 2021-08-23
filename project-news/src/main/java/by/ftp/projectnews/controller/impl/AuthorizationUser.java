package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import by.ftp.projectnews.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthorizationUser implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();

	private static final String URL = "url";
	private static final String USER = "user";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		
		try {

			
			if (login == null || login.isEmpty()) {
				String path = (String)session.getAttribute(URL);
				response.sendRedirect("Controller?command=" + path + "&message=Invalid login! Try again!");
				return;
			}
			User user = USER_SERVICE.authorization(login, password);
			if (user != null) {
				session.setAttribute(USER, user);

				session.setAttribute(URL, CommandName.GO_TO_USER_PAGE.toString());
				response.sendRedirect(
						"Controller?command=go_to_user_page&message=Autorization completed successfully!");
			} else {
				String path = (String) session.getAttribute(URL);
				response.sendRedirect("Controller?command=" + path + "&message=This login/password is wrong! Try again!");
			}

		} catch (ServiceException e) {
			// log

			String path = (String) session.getAttribute(URL);
			response.sendRedirect("Controller?command=" + path + "&login="+login+"&message=Something wrong at the authorization!");

		}
	}
}
