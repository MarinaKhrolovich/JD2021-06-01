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


	public Controller() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(COMMAND_REQUEST_PARAM);
		Command command = provider.findCommand(commandName);
		command.execute(request, response);

	}

}
