package by.ftp.projectnews.controller;

import java.util.HashMap;
import java.util.Map;

import by.ftp.projectnews.controller.impl.AddNews;
import by.ftp.projectnews.controller.impl.AuthorizationUser;
import by.ftp.projectnews.controller.impl.ChangeLocal;
import by.ftp.projectnews.controller.impl.GoToAuthorizationPage;
import by.ftp.projectnews.controller.impl.GoToMainPage;
import by.ftp.projectnews.controller.impl.GoToRegistrationPage;
import by.ftp.projectnews.controller.impl.GoToUserPage;
import by.ftp.projectnews.controller.impl.RegistrationNewUser;
import by.ftp.projectnews.controller.impl.GoToNewsPage;
import by.ftp.projectnews.controller.impl.UnknownCommand;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new GoToAuthorizationPage());
		commands.put(CommandName.REGISTRATION, new GoToRegistrationPage());
		commands.put(CommandName.REGISTRATION_NEW_USER, new RegistrationNewUser());
		commands.put(CommandName.AUTHORIZATION_USER, new AuthorizationUser());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_USER_PAGE, new GoToUserPage());
		commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocal());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.GO_TO_PAGE_NEWS, new GoToNewsPage());
		commands.put(CommandName.GET_LAST_NEWSES, new GoToNewsPage());
	}

	public Command findCommand(String name) {
		if (name == null || name.isEmpty()) {
			name = CommandName.UNKNOWN_COMMAND.toString();
		}

		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			// logging
			commandName = CommandName.UNKNOWN_COMMAND;
		}

		Command command = commands.get(commandName);
		return command;
	}

}
