package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import by.ftp.projectnews.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String YEAR_BIRTHDAY = "yearBirthday";
	private static final String SEX = "sex";
	private static final String URL = "url";
	private static final String USER = "user";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RegistrationInfo info = new RegistrationInfo();
		try {
			
			PrintWriter out  = response.getWriter();
			String login 	 = request.getParameter(LOGIN);
			String password  = request.getParameter(PASSWORD);
			String name 	 = request.getParameter(NAME);
			String surname   = request.getParameter(SURNAME);
			String yearBirthday = request.getParameter(YEAR_BIRTHDAY);
			String sex 			= request.getParameter(SEX);
			
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setName(name);
			user.setSurName(surname);

			//todo сформировать список ошибок. И если ошибки, то перенаправить на error.jsp
			if (yearBirthday!="") {  
				user.setYearBirthday(Integer.parseInt(yearBirthday));
			}
			if (sex!=null) {  
				user.setSex(sex);
			}
			request.setAttribute(USER, user);
			
			userService.registration(user);
			
			//request.setAttribute("message", "hello");
			//out.println("Registration completed successfully!");
			//out.println("<br />Your login: " + login);
			//out.println("<br />Your password: " + password);
			request.getSession(true).setAttribute(URL, CommandName.AUTHORIZATION.toString());
			response.sendRedirect("Controller?command=AUTHORIZATION&message='Registration completed successfully!'");
			
			
		} catch (ServiceException e) {
			// log
			// path = "error.jsp";
			String path = ERROR_JSP;
			request.setAttribute("message", "Error in the registration");
			request.getSession(true).setAttribute(URL, CommandName.UNKNOWN_COMMAND.toString());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
			requestDispatcher.forward(request, response);
		}
		

		
	}
}
