package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import by.ftp.projectnews.bean.RegistrationInfo;
import by.ftp.projectnews.controller.Command;
import by.ftp.projectnews.controller.CommandName;
import by.ftp.projectnews.controller.message.MessageLocal;
import by.ftp.projectnews.controller.message.MessageResourceManager;
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
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String YEAR_BIRTHDAY = "yearBirthday";
	private static final String SEX = "sex";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String CONTROLLER_COMMAND = "Controller?command=";
	private static final String PARAM_MESSAGE = "&message=";
	private static final String EMPTY_STRING = "";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String msg = EMPTY_STRING;
		MessageResourceManager localManager = MessageResourceManager.getInstance();

		HttpSession session = request.getSession(true);
		String path = (String) session.getAttribute(URL);
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

			if ((yearBirthday != null) && (!yearBirthday.isEmpty())) {
				regInfo.setYearBirthday(Integer.parseInt(yearBirthday));
			} else {
				regInfo.setYearBirthday(0);
			}

			USER_SERVICE.registration(regInfo);
			String commandName = CommandName.AUTHORIZATION.toString();
			msg = localManager.getValue(MessageLocal.USER_REG_SUCCESS);
			response.sendRedirect(CONTROLLER_COMMAND + commandName + PARAM_MESSAGE + msg);

		} catch (ServiceException e) {
			// log
			msg = localManager.getValue(MessageLocal.USER_REG_EXISTS);

			String codeLogin = URLEncoder.encode(login, StandardCharsets.UTF_8);
			String codeName = URLEncoder.encode(name, StandardCharsets.UTF_8);
			String codeSurname = URLEncoder.encode(surname, StandardCharsets.UTF_8);
			response.sendRedirect(CONTROLLER_COMMAND + path + "&login=" + codeLogin + "&name=" + codeName + "&surname="
					+ codeSurname + "&yearBirthday=" + yearBirthday + "&sex=" + sex + PARAM_MESSAGE + msg);
		}

	}
}
