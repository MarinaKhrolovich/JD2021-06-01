package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocal implements Command {

	private static final String URL = "url";
	private static final String LOCAL = "local";
	private static final String NEWSES = "newses";
	private static final String CONTROLLER_COMMAND = "Controller?command=";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setAttribute(LOCAL, request.getParameter(LOCAL));
		String path = (String) session.getAttribute(URL);
		response.sendRedirect(CONTROLLER_COMMAND + path);

	}

}
