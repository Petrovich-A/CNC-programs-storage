package by.petrovich.storage.controller.command;

import by.petrovich.storage.controller.command.impl.*;
import by.petrovich.storage.controller.command.impl.goTo.GoToAdminPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToAdminUsersPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToAuthorizationPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToCncMachineUpdatePage;
import by.petrovich.storage.controller.command.impl.goTo.GoToCncMachinesPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToCncProgramUpdatePage;
import by.petrovich.storage.controller.command.impl.goTo.GoToCncProgramViewPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToDetailUpdatePage;
import by.petrovich.storage.controller.command.impl.goTo.GoToDetailsPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToDetailsCncProgramsPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToErrorPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToMainPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToRegistrationPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToUpdateUserPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToUserInfoPage;
import by.petrovich.storage.controller.command.impl.goTo.GoToUsersProgramPage;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	private static CommandProvider instance;

	private CommandProvider() {
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
		commands.put(CommandName.GO_TO_USER_INFO_PAGE, new GoToUserInfoPage());
		commands.put(CommandName.GO_TO_UPDATE_USER_PAGE, new GoToUpdateUserPage());
		commands.put(CommandName.GO_TO_ADMIN_USERS_PAGE, new GoToAdminUsersPage());
		commands.put(CommandName.GO_TO_CNC_PROGRAM_VIEW_PAGE, new GoToCncProgramViewPage());
		commands.put(CommandName.GO_TO_CNC_PROGRAM_UPDATE_PAGE, new GoToCncProgramUpdatePage());
		commands.put(CommandName.GO_TO_USERS_PROGRAM_PAGE, new GoToUsersProgramPage());
		commands.put(CommandName.GO_TO_DETAILS_PAGE, new GoToDetailsPage());
		commands.put(CommandName.GO_TO_CNC_MACHINES_PAGE, new GoToCncMachinesPage());
		commands.put(CommandName.GO_TO_DETAIL_UPDATE_PAGE, new GoToDetailUpdatePage());
		commands.put(CommandName.GO_TO_CNC_MACHINE_UPDATE_PAGE, new GoToCncMachineUpdatePage());
		commands.put(CommandName.GO_TO_DETAILS_CNC_PROGRAMS_PAGE, new GoToDetailsCncProgramsPage());

		commands.put(CommandName.CNC_PROGRAM_SAVE, new CncProgramSaveCommand());
		commands.put(CommandName.CNC_PROGRAM_UPDATE, new CncProgramUpdateCommand());
		commands.put(CommandName.CNC_MACHINE_UPDATE, new CncMachineUpdateCommand());
		commands.put(CommandName.DETAIL_UPDATE, new DetailUpdateCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocalCommand());
		commands.put(CommandName.USER_UPDATE, new UserUpdateCommand());
		commands.put(CommandName.LOG_OUT, new LogOutCommand());
		commands.put(CommandName.SEARCH, new SearchCommand());
	}

	public synchronized static CommandProvider getInstamce() {
		if (instance == null) {
			instance = new CommandProvider();
		}
		return instance;
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
