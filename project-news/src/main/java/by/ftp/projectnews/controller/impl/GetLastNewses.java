package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetLastNewses implements Command{

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewService();
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String MESSAGE = "message";
	private static final String URL = "url";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			newsService.getListOfNews();
			
		} catch (ServiceException e) {
			request.setAttribute(MESSAGE, "Coudn't get the list of newses");
			request.getSession(true).setAttribute(URL, CommandName.UNKNOWN_COMMAND.toString());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_JSP);
			requestDispatcher.forward(request, response);
		}
		
	}
	

}
