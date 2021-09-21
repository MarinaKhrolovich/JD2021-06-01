package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageLocal;
import by.ftp.projectnews.controller.message.MessageResourceManager;
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

	private final static Logger LOG = LogManager.getLogger(AuthorizationUser.class);
	
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String PARAM_MESSAGE = "&message=";
	private static final String EMPTY_STRING = "";
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = EMPTY_STRING;
		MessageResourceManager localManager = MessageResourceManager.getInstance();

		HttpSession session = request.getSession(true);
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);

		try {

			if (checkNullEmpty(login) || checkNullEmpty(password)) {
				msg = localManager.getValue(MessageLocal.USER_AUTHO_INVALID_LOGIN);
				String path = (String) session.getAttribute(URL);
				response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
				return;
			}
			User user = USER_SERVICE.authorization(login, password);
			if (user != null) {
				msg = localManager.getValue(MessageLocal.USER_AUTHO_SUCCESS);
				session.setAttribute(USER, user);
				String commandName = CommandName.GO_TO_USER_PAGE.toString();
				response.sendRedirect(CONTROLLER_COMMAND + commandName + PARAM_MESSAGE + msg);
			} else {
				msg = localManager.getValue(MessageLocal.USER_AUTO_LOGIN_WRONG);
				String path = (String) session.getAttribute(URL);
				response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);
			}

		} catch (ServiceException e) {
			
			LOG.error(e);
			msg = localManager.getValue(MessageLocal.USER_AUTO_WRONG);

			String path = (String) session.getAttribute(URL);
			response.sendRedirect(CONTROLLER_COMMAND + path + PARAM_MESSAGE + msg);

		}
	}

	public boolean checkNullEmpty(String field) {

		return field == null || field.isEmpty();

	}
}
