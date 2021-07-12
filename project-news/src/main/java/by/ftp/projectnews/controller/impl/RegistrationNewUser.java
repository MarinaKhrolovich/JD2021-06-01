package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {

	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out  = response.getWriter();
		String login 	 = request.getParameter("login");
		String password  = request.getParameter("password");
		String name 	 = request.getParameter("name");
		String surname   = request.getParameter("surname");
		String yearBirthday = request.getParameter("yearBirthday");
		String sex 			= request.getParameter("sex");
		
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);
		user.setSurName(surname);


		if (yearBirthday!="") {  
			user.setYearBirthday(Integer.parseInt(yearBirthday));
		}
		if (sex!=null) {  
			user.setSex(sex);
		}
		request.setAttribute("user", user);

		out.println("Registration completed successfully!");
		out.println("<br />Your login: " + login);
		out.println("<br />Your password: " + password);
		
	}
}
