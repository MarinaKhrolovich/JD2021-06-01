package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageLocal;
import by.ftp.projectnews.controller.message.MessageResourceManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToAddPageNews implements Command {

	private static final String ADD_PAGE_NEWS_JSP = "/WEB-INF/jsp/addPageNews.jsp";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String ADMIN_ROLE = "admin";
	private static final String PARAM_MESSAGE = "&message=";
	private static final String EMPTY_STRING = "";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = EMPTY_STRING;
		MessageResourceManager localManager = MessageResourceManager.getInstance();

		HttpSession session = request.getSession(false);
		String redirectAutho = CommandName.AUTHORIZATION.toString();

		if (session == null) {
			msg = localManager.getValue(MessageLocal.SESSION_LOST);
			String puth = CommandName.GO_TO_MAIN_PAGE.toString();
			response.sendRedirect(CONTROLLER_COMMAND + puth + PARAM_MESSAGE + msg);
			return;
		}

		User user = (User) session.getAttribute(USER);

		if (user == null) {
			msg = localManager.getValue(MessageLocal.MUST_SIGN_IN);

			response.sendRedirect(CONTROLLER_COMMAND + redirectAutho + PARAM_MESSAGE + msg);

			return;
		}

		if (!ADMIN_ROLE.equals(user.getRole())) {
			session.removeAttribute(USER);
			// log
			msg = localManager.getValue(MessageLocal.MUST_SIGN_IN_AS_ADMIN);

			response.sendRedirect(CONTROLLER_COMMAND + redirectAutho + PARAM_MESSAGE + msg);

			return;
		}

		session.setAttribute(URL, CommandName.GO_TO_ADD_PAGE_NEWS.toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADD_PAGE_NEWS_JSP);
		requestDispatcher.forward(request, response);

	}

}
