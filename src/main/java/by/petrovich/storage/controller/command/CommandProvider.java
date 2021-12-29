package by.petrovich.storage.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.petrovich.storage.controller.impl.ChangeLocal;
import by.petrovich.storage.controller.impl.GoToCommandError;
import by.petrovich.storage.controller.impl.GoToMain;
import by.petrovich.storage.controller.impl.GoToRegistration;
import by.petrovich.storage.controller.impl.Registration;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.GO_TO_MAIN, new GoToMain());
		commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistration());
		
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocal());
		commands.put(CommandName.COMMAND_ERROR, new GoToCommandError());
	}

	public Command findCommand(String name) {
		if (name == null || name.isBlank()) {
			name = CommandName.COMMAND_ERROR.toString();
		}
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException e) {
			commandName = CommandName.COMMAND_ERROR;
			e.printStackTrace();
		}
		Command command = commands.get(commandName);
		return command;
	}

}
