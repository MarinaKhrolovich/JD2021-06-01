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
	private static final String USER = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		session.removeAttribute(USER);
		String puth = CommandName.GO_TO_MAIN_PAGE.toString();
		response.sendRedirect(CONTROLLER_COMMAND + puth);

	}

}
