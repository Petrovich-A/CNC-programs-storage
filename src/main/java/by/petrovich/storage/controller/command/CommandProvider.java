package by.petrovich.storage.controller.command;

import by.petrovich.storage.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;


public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_LOG_IN_PAGE, new GoToLogInPage());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());

		commands.put(CommandName.SAVE_CNC_PROGRAM, new SaveCncProgram());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocalCommand());
	}

	public Command findCommand(String name) {
		if (name == null || name.isBlank()) {
			name = CommandName.GO_TO_ERROR_PAGE.toString();
		}
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			commandName = CommandName.GO_TO_ERROR_PAGE;
			e.printStackTrace();
		}
		Command command = commands.get(commandName);
		return command;
	}

}
