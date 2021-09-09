package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogOut implements Command {

	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String URL = "url";
	private static final String USER = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String puth = CommandName.GO_TO_MAIN_PAGE.toString();
		if (session == null) {
			request.getSession(true).setAttribute(URL, puth);
			response.sendRedirect(
					CONTROLLER_COMMAND + puth + "&message=You session is lost!");
			return;
		}

		session.removeAttribute(USER);
		session.setAttribute(URL, puth);
		response.sendRedirect(CONTROLLER_COMMAND + puth);

	}

}
