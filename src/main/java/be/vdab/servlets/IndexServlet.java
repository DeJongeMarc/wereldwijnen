package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.LandService;
import be.vdab.services.SoortService;


@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp"; 
	private static final String MANDJE = "mandje";
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String gekozenLandString = request.getParameter("landId");
		String gekozenSoortString = request.getParameter("soortId");
		long gekozenLand;
		long gekozenSoort;
		HttpSession session = request.getSession(false);
		
		if (gekozenLandString != null) {
			try {
				gekozenLand = Long.parseLong(gekozenLandString);
				landService.read(gekozenLand).ifPresent(land -> request.setAttribute("land", land));
			} catch (Exception e) {
				fouten.put("land", "Gekozen land bestaat niet.");
			}
		}
		
		
		request.setAttribute("landen", landService.findAll());
		request.setAttribute("fouten", fouten);
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
