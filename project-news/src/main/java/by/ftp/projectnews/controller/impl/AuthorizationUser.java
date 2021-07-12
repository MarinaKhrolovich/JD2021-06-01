package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationUser implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		
		//найти в списке пользователей
		//1. Если не найден, то перевести на страницу регистрации с сообщением
		//2. Если найден, то сообщить, что пользователь успешно авторизовался
		
		
		out.println("Autorization completed successfully!");

	}
}
