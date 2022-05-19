package by.petrovich.storage.controller.command.impl;

import static by.petrovich.storage.controller.command.PathToPage.ERROR_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.GO_TO_DETAILS_PAGE;
import static by.petrovich.storage.controller.command.PathToPage.GO_TO_DETAIL_UPDATE_PAGE;
import static by.petrovich.storage.controller.command.RequestAttributeNames.ERROR_MESSAGE;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.petrovich.storage.controller.command.AbstractCommand;
import by.petrovich.storage.controller.command.Router;
import by.petrovich.storage.controller.command.Router.RouterType;
import by.petrovich.storage.entity.Detail;
import by.petrovich.storage.service.CncProgramService;
import by.petrovich.storage.service.ServiceException;
import by.petrovich.storage.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @author Petrovich A.V.
 *
 */
public class DetailUpdateCommand extends AbstractCommand {
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
			request.setAttribute(ERROR_MESSAGE, NO_DETAIL_ID);
			return new Router(ERROR_PAGE, RouterType.FORWARD);
		} else {
			try {
				cncProgramService.updateDetail(detailFromUpdateForm, id);
				logger.log(Level.INFO, "Detail with id: {} is updated.", id);
				return createRouterWithAttribute(request, GO_TO_DETAILS_PAGE, "admin_details_message",
						DETAIL_UPDATE_SUCCESSFUL);
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "Can't update detail with id: {}, detail: {}", id,
						detailFromUpdateForm.toString(), e);
				return createRouterWithAttribute(request, GO_TO_DETAIL_UPDATE_PAGE, "detail_update_message",
						DETAIL_UPDATE_FAILD);
			}
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private Detail buildDetail(HttpServletRequest request) {
		Detail detail = new Detail();
		detail.setName(getParameterToCheck("name", request));
		return detail;
	}

}
