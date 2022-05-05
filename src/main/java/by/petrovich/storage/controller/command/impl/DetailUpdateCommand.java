package by.petrovich.storage.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.Command;
import by.petrovich.storage.controller.command.PathToPage;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.Detail;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DetailUpdateCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String NO_DETAIL_ID = "No detail id.";
	private static final String DETAIL_UPDATE_SUCCESSFUL = "Detail updating is successful.";
	private static final String DETAIL_UPDATE_FAILD = "Detail updating is faild.";
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private final CncProgramService cncProgramService = serviceProvider.getCncProgramService();

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Detail detailFromUpdateForm = buildDetail(request);
		int id = (Integer) session.getAttribute("detail_id");
		if (id == 0) {
			request.setAttribute("error_message", NO_DETAIL_ID);
			return new Router(PathToPage.ERROR, RouterType.FORWARD);
		} else {
			try {
				cncProgramService.updateDetail(detailFromUpdateForm, id);
				request.setAttribute("admin_details_message", DETAIL_UPDATE_SUCCESSFUL);
				logger.log(Level.INFO, "Detail with id: {} is updated.", id);
				return new Router(PathToPage.GO_TO_DETAILS, RouterType.FORWARD);
			} catch (ServiceException e) {
				request.setAttribute("detail_update_message", DETAIL_UPDATE_FAILD);
				logger.log(Level.ERROR, "Can't update detail with id: {}, detail: {}", id,
						detailFromUpdateForm.toString(), e);
				return new Router(PathToPage.GO_TO_DETAIL_UPDATE_PAGE, RouterType.FORWARD);
			}
		}
	}

	private Detail buildDetail(HttpServletRequest request) {
		Detail detail = new Detail();
		detail.setName(getParameterToCheck("name", request));
		return detail;
	}

	private String getParameterToCheck(String name, HttpServletRequest request) {
		final String parameter = request.getParameter(name);
		if (parameter == null) {
			throw new IllegalArgumentException("Request doesn't have parameter with name: {}" + name);
		}
		return parameter;
	}

}
