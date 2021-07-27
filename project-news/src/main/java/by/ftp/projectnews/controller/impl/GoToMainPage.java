package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command {

	private static final String MAIN_JSP = "/WEB-INF/jsp/main.jsp";
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//List<News> newses = new ArrayList<News>();
		
		try {
			newsService.add(new News("title1"," News №1"));
			newsService.add(new News("title2"," News №2"));
			newsService.add(new News("title3"," News №3"));
			newsService.add(new News("title4"," News №4"));
			newsService.add(new News("title5"," News №5"));
			newsService.add(new News("title6"," News №6"));
			newsService.add(new News("title7"," News №7"));
			newsService.add(new News("title8"," News №8"));
			newsService.add(new News("title9"," News №9"));
			newsService.add(new News("title10"," News №10"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		//request.setAttribute("newses", newses);
		try {
			//request.setAttribute("newses", newsService.getListOfNews());
			request.getSession(true).setAttribute("newses", newsService.getListOfNews());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		String path = MAIN_JSP;
		request.getSession(true).setAttribute("url", path);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
