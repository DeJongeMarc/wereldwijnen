package be.vdab.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.WijnService;
import be.vdab.util.StringUtils;

@WebServlet("/wijn/toevoegen.htm")
public class WijnToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/wijntoevoegen.jsp";
	private static final String MANDJE = "mandje";
	private final transient WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gekozenWijnString = request.getParameter("wijnId");
		if (StringUtils.isLong(gekozenWijnString)) {
			long gekozenWijn = Long.parseLong(gekozenWijnString);
			wijnService.read(gekozenWijn).ifPresent(wijn -> request.setAttribute("wijn", wijn));
			HttpSession session = request.getSession(false);
			if (session != null) {
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
				if (mandje != null) {
					request.setAttribute("aantal", mandje.get(gekozenWijn));
				}
			}
		} else {
			request.setAttribute("fouten", Collections.singletonMap("wijn", "Gekozen wijn bestaat niet."));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String wijnIdString = request.getParameter("wijnId");
		String aantalString = request.getParameter("aantal");
		if (StringUtils.isLong(wijnIdString)) {
			long gekozenWijn = Long.parseLong(wijnIdString);
			wijnService.read(gekozenWijn).ifPresent(wijn -> request.setAttribute("wijn", wijn));
			if (StringUtils.isGeldigAantal(aantalString) && request.getAttribute("wijn") != null) {
				int aantal = Integer.parseInt(aantalString);
				HttpSession session = request.getSession();
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
				if (mandje == null) {
					mandje = new LinkedHashMap<>();
				}
				mandje.put(gekozenWijn, aantal);
				session.setAttribute(MANDJE, mandje);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
			} else {
				request.setAttribute("fouten", Collections.singletonMap("aantal", "Geen correct aantal."));
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		} else {
			request.setAttribute("fouten", Collections.singletonMap("wijn", "Gekozen wijn bestaat niet."));
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
