package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.LandService;
import be.vdab.services.SoortService;
import be.vdab.util.StringUtils;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final String MANDJE = "mandje";
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gekozenLandString = request.getParameter("landId");
		String gekozenSoortString = request.getParameter("soortId");
		HttpSession session = request.getSession(false);
		if (session != null) {
			request.setAttribute("mandje",session.getAttribute(MANDJE) );
		}
		if (StringUtils.isLong(gekozenLandString)) {
			long gekozenLand = Long.parseLong(gekozenLandString);
			landService.read(gekozenLand).ifPresent(land -> request.setAttribute("land", land));
			if (StringUtils.isLong(gekozenSoortString)
					&& request.getAttribute("land") != null) {
				long gekozenSoort = Long.parseLong(gekozenSoortString);
				soortService.read(gekozenSoort).ifPresent(soort -> request.setAttribute("soort", soort));
			} 
		} 
		request.setAttribute("landen", landService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
