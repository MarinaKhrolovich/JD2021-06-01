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
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {

	private static final String MAIN_JSP = "/WEB-INF/jsp/main.jsp";
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();
	private static final String URL = "url";
	private static final String NEWSES = "newses";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		try {
			
			session.setAttribute(NEWSES, NEWS_SERVICE.getListOfNews());
			session.setAttribute(URL, CommandName.GO_TO_MAIN_PAGE.toString());
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_JSP);
			requestDispatcher.forward(request, response);

		} catch (ServiceException e) {

			session.setAttribute(URL, CommandName.GO_TO_MAIN_PAGE.toString());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}

	}
}
