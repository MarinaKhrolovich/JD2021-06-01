package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.service.NewsService;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddNews implements Command{

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//может использоваться эта команда, только если сессия создана
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			request.getSession(true).setAttribute("url", "AUTHORIZATION");
			response.sendRedirect("Controller?command=AUTHORIZATION&message='You session is lost.You must sign in to the system!'");
			
			return;
		}
		
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			request.getSession(true).setAttribute("url", "AUTHORIZATION");
			response.sendRedirect("Controller?command=AUTHORIZATION&message='You must sign in to the system!'");
			
			return;
		}
		
		if(!"admin".equals(user.getRole())) {
			session.removeAttribute("user");
			//log
			request.getSession(true).setAttribute("url", "AUTHORIZATION");
			response.sendRedirect("Controller?command=AUTHORIZATION&message='You must sign as an administrator!'");
			
			return;
		}
		
		

			News news = new News("Title","Brief","Content");
			try {
				newsService.add(news);
			} catch (ServiceException e) {
				e.printStackTrace();
			}

		
		
	}
}
