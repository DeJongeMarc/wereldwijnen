package be.vdab.servlets;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbon;
import be.vdab.services.BestelbonService;
import be.vdab.services.WijnService;

@WebServlet("/bevestiging.htm")
public class BevestigingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";
	private static final String BEVESTIGD_BON_ID = "bevestigdBonId";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Long bonid = (Long) session.getAttribute(BEVESTIGD_BON_ID);
			if (bonid != null) {
				request.setAttribute("bevestigdBonId", bonid);
				session.invalidate();
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
