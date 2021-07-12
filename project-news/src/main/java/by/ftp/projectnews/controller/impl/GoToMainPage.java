package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.ftp.projectnews.bean.News;
import by.ftp.projectnews.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newses = new ArrayList<News>();
		
		newses.add(new News("t1"," b1 b1 b1 b1"));
		newses.add(new News("t2"," b2 b2 b2 b2"));
		
		request.setAttribute("newses", newses);
		
		String path = "/WEB-INF/jsp/main.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
