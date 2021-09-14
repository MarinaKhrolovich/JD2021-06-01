package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageLocal;
import by.ftp.projectnews.controller.message.MessageResourceManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogOut implements Command {

	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String PARAM_MESSAGE = "&message=";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageResourceManager localManager = MessageResourceManager.getInstance();
		
		HttpSession session = request.getSession(false);
		String puth = CommandName.GO_TO_MAIN_PAGE.toString();
		if (session == null) {
			String msg = localManager.getValue(MessageLocal.SESSION_LOST);
			
			request.getSession(true).setAttribute(URL, puth);
			response.sendRedirect(
					CONTROLLER_COMMAND + puth + PARAM_MESSAGE+msg);
			return;
		}

		session.removeAttribute(USER);
		session.setAttribute(URL, puth);
		response.sendRedirect(CONTROLLER_COMMAND + puth);

	}

}
