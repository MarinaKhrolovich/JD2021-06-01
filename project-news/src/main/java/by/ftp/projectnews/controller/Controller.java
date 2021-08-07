package by.ftp.projectnews.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final CommandProvider provider = new CommandProvider();
	
	private static final String COMMAND_REQUEST_PARAM = "command";
	private static final String LOCAL = "local";
	private static final String LOCAL_BE = "be";

	public Controller() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		String local = (String) session.getAttribute(LOCAL);
		if (local == null) {
			request.getSession(true).setAttribute(LOCAL, LOCAL_BE);
		}

		String commandName = request.getParameter(COMMAND_REQUEST_PARAM);
		Command command = provider.findCommand(commandName);
		command.execute(request, response);

	}

}
