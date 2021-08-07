package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import by.ftp.projectnews.service.UserService;
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

		try {
			String login = request.getParameter(LOGIN);
			String password = request.getParameter(PASSWORD);
			String name = request.getParameter(NAME);
			String surname = request.getParameter(SURNAME);
			String yearBirthday = request.getParameter(YEAR_BIRTHDAY);
			String sex = request.getParameter(SEX);

			RegistrationInfo regInfo = new RegistrationInfo();
			regInfo.setLogin(login);
			regInfo.setPassword(password);
			regInfo.setName(name);
			regInfo.setSurName(surname);
			regInfo.setRole(USER);

			if (yearBirthday != null) {
				regInfo.setYearBirthday(Integer.parseInt(yearBirthday));
			}
			if ((sex != null) || (!sex.isEmpty())) {
				regInfo.setSex(sex);
			}

			userService.registration(regInfo);

			request.getSession(true).setAttribute(URL, CommandName.AUTHORIZATION.toString());
			response.sendRedirect("Controller?command=AUTHORIZATION&message=Registration completed successfully!");

		} catch (ServiceException e) {
			// log
			String path = (String) request.getSession(true).getAttribute(URL);
			response.sendRedirect(
					"Controller?command=" + path + "&message=Something wrong in the registration! Try again!");
			// or go to page of errors
		}

	}
}
