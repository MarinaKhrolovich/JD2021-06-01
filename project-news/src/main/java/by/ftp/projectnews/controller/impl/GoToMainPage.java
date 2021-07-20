package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command {

	private static final String MAIN_JSP = "/WEB-INF/jsp/main.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newses = new ArrayList<News>();
		
		newses.add(new News("title1"," News №1"));
		newses.add(new News("title2"," News №2"));
		newses.add(new News("title3"," News №3"));
		newses.add(new News("title4"," News №4"));
		newses.add(new News("title5"," News №5"));
		newses.add(new News("title6"," News №6"));
		newses.add(new News("title7"," News №7"));
		newses.add(new News("title8"," News №8"));
		newses.add(new News("title9"," News №9"));
		newses.add(new News("title10"," News №10"));
		
		request.setAttribute("newses", newses);
		
		String path = MAIN_JSP;
		request.getSession(true).setAttribute("url", path);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
