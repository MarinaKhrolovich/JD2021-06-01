package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageLocal;
import by.ftp.projectnews.controller.message.MessageResourceManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command {

	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String URL = "url";
	private static final String MESSAGE = "message";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = ERROR_JSP;
		MessageResourceManager localManager = MessageResourceManager.getInstance();
		String msg = URLDecoder.decode(localManager.getValue(MessageLocal.UNKNOWN_COMMAND), StandardCharsets.UTF_8); 
		request.setAttribute(MESSAGE, msg);
		request.getSession(true).setAttribute(URL, CommandName.UNKNOWN_COMMAND.toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

}
