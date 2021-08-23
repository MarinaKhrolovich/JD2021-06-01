package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetLastNewses implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String MESSAGE = "message";
	private static final String URL = "url";
	private static final String MESSAGE_ERROR = "Coudn't get the list of newses";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			NEWS_SERVICE.getListOfNews();

		} catch (ServiceException e) {
			request.setAttribute(MESSAGE, MESSAGE_ERROR);
			request.getSession(true).setAttribute(URL, CommandName.UNKNOWN_COMMAND.toString());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

	}

}
