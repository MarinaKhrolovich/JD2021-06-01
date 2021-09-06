package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import javax.swing.DefaultRowSorter;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.service.ServiceException;
import by.ftp.projectnews.service.ServiceProvider;
import by.ftp.projectnews.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegistrationNewUser implements Command {

	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final UserService USER_SERVICE = PROVIDER.getUserService();
	private static final String ERROR_JSP = "/WEB-INF/jsp/error.jsp";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String YEAR_BIRTHDAY = "yearBirthday";
	private static final String SEX = "sex";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String MESSAGE = "message";
	private static final String CONTROLLER_COMMAND = "Controller?command=";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		String path = (String) session.getAttribute(URL);
		String message = (String) session.getAttribute(MESSAGE);
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String name = request.getParameter(NAME);
		String surname = request.getParameter(SURNAME);
		String yearBirthday = request.getParameter(YEAR_BIRTHDAY);
		String sex = request.getParameter(SEX);
		
		try {
		
			RegistrationInfo regInfo = new RegistrationInfo();
			regInfo.setLogin(login);
			regInfo.setPassword(password);
			regInfo.setName(name);
			regInfo.setSurName(surname);
			regInfo.setRole(USER);
			regInfo.setSex(sex);
			
			if ((yearBirthday!= null) && (!yearBirthday.isEmpty())) {
				regInfo.setYearBirthday(Integer.parseInt(yearBirthday));
			}
			else {
				regInfo.setYearBirthday(0);
			}

			USER_SERVICE.registration(regInfo);
			String commandName = CommandName.AUTHORIZATION.toString();
			request.getSession(true).setAttribute(URL, commandName);
			response.sendRedirect(CONTROLLER_COMMAND+commandName+"&message=Registration completed successfully!");

		} catch (ServiceException e) {
			// log
			response.sendRedirect(
					CONTROLLER_COMMAND + path + "&login="+login+"&name="+name+"&surname="+surname
						+"&yearBirthday="+yearBirthday+"&sex="+sex+"&message=This user has already exists. Try again!");
		}

	}
}
