package by.petrovich.storage.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.petrovich.storage.controller.command.impl.AuthorizationCommand;
import by.petrovich.storage.controller.command.impl.ChangeLocalCommand;
import by.petrovich.storage.controller.command.impl.CncMachineUpdateCommand;
import by.petrovich.storage.controller.command.impl.CncProgramSaveCommand;
import by.petrovich.storage.controller.command.impl.CncProgramUpdateCommand;
import by.petrovich.storage.controller.command.impl.DetailUpdateCommand;
import by.petrovich.storage.controller.command.impl.LogOutCommand;
import by.petrovich.storage.controller.command.impl.RegistrationCommand;
import by.petrovich.storage.controller.command.impl.SearchCommand;
import by.petrovich.storage.controller.command.impl.UserUpdateCommand;
import by.petrovich.storage.controller.command.impl.go.GoToAdminPage;
import by.petrovich.storage.controller.command.impl.go.GoToAdminUsersPage;
import by.petrovich.storage.controller.command.impl.go.GoToAuthorizationPage;
import by.petrovich.storage.controller.command.impl.go.GoToCncMachineUpdatePage;
import by.petrovich.storage.controller.command.impl.go.GoToCncMachinesPage;
import by.petrovich.storage.controller.command.impl.go.GoToCncProgramUpdatePage;
import by.petrovich.storage.controller.command.impl.go.GoToCncProgramViewPage;
import by.petrovich.storage.controller.command.impl.go.GoToDetailUpdatePage;
import by.petrovich.storage.controller.command.impl.go.GoToDetailsCncProgramsPage;
import by.petrovich.storage.controller.command.impl.go.GoToDetailsPage;
import by.petrovich.storage.controller.command.impl.go.GoToErrorPage;
import by.petrovich.storage.controller.command.impl.go.GoToMainPage;
import by.petrovich.storage.controller.command.impl.go.GoToRegistrationPage;
import by.petrovich.storage.controller.command.impl.go.GoToUpdateUserPage;
import by.petrovich.storage.controller.command.impl.go.GoToUserInfoPage;
import by.petrovich.storage.controller.command.impl.go.GoToUsersProgramPage;

/**
 * @author Petrovich A.V.
 *
 */
public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	private static CommandProvider instance;

	/**
	 * 
	 */
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

	/**
	 * @return
	 */
	public synchronized static CommandProvider getInstamce() {
		if (instance == null) {
			instance = new CommandProvider();
		}
		return instance;
	}

	/**
	 * @param name
	 * @return
	 */
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
