package by.petrovich.storage.controller.command;

import by.petrovich.storage.controller.command.impl.*;
import by.petrovich.storage.controller.command.impl.goTo.GoToAdminPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToAdminUsersPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToAuthorizationPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToCncMachineUpdatePage;
import by.petrovich.storage.controller.command.impl.goTo.GoToCncMachines;
import by.petrovich.storage.controller.command.impl.goTo.GoToCncProgramUpdate;
import by.petrovich.storage.controller.command.impl.goTo.GoToCncProgramView;
import by.petrovich.storage.controller.command.impl.goTo.GoToDetailUpdatePage;
import by.petrovich.storage.controller.command.impl.goTo.GoToDetails;
import by.petrovich.storage.controller.command.impl.goTo.GoToErrorPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToMainPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToRegistrationPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToUpdateUserPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToUserInfo;
import by.petrovich.storage.controller.command.impl.goTo.GoToUsersProgram;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
		commands.put(CommandName.GO_TO_USER_INFO, new GoToUserInfo());
		commands.put(CommandName.GO_TO_UPDATE_USER_PAGE, new GoToUpdateUserPage());
		commands.put(CommandName.GO_TO_ADMIN_USERS_PAGE, new GoToAdminUsersPage());
		commands.put(CommandName.GO_TO_CNC_PROGRAM_VIEW, new GoToCncProgramView());
		commands.put(CommandName.GO_TO_CNC_PROGRAM_UPDATE, new GoToCncProgramUpdate());
		commands.put(CommandName.GO_TO_USERS_PROGRAM, new GoToUsersProgram());
		commands.put(CommandName.GO_TO_DETAILS, new GoToDetails());
		commands.put(CommandName.GO_TO_CNC_MACHINES, new GoToCncMachines());
		commands.put(CommandName.GO_TO_DETAIL_UPDATE_PAGE, new GoToDetailUpdatePage());
		commands.put(CommandName.GO_TO_CNC_MACHINE_UPDATE_PAGE, new GoToCncMachineUpdatePage());

		commands.put(CommandName.CNC_PROGRAM_SAVE, new CncProgramSave());
		commands.put(CommandName.CNC_PROGRAM_UPDATE, new CncProgramUpdate());
		commands.put(CommandName.CNC_MACHINE_UPDATE, new CncMachineUpdate());
		commands.put(CommandName.DETAIL_UPDATE, new DetailUpdate());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocalCommand());
		commands.put(CommandName.USER_UPDATE, new UserUpdate());
		commands.put(CommandName.LOG_OUT, new LogOut());
		commands.put(CommandName.SEARCH_BY_CNC_PROGRAM_NAME, new SearchByCncProgramName());
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
