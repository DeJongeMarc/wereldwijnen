package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbon;
import be.vdab.services.BestelbonService;
import be.vdab.services.WijnService;
import be.vdab.util.StringUtils;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;
/**
 * 
 * @author marc.de.jonge
 *
 */
@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "%s/bevestiging.htm";
	private static final String MANDJE = "mandje";
	private static final String BEVESTIGD_BON_ID = "bevestigdBonId";
	private final transient WijnService wijnService = new WijnService();
	private final transient BestelbonService bestelbonService = new BestelbonService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			if (mandje != null) {
				List<Bestelbonlijn> bestelbonlijnen = new ArrayList<>();
				mandje.keySet().stream().forEach(wijnid -> bestelbonlijnen
						.add(new Bestelbonlijn(wijnService.read(wijnid).get(), mandje.get(wijnid))));
				request.setAttribute("bestelbonlijnen", bestelbonlijnen);
				BigDecimal totaal = BigDecimal.ZERO;
				for (Bestelbonlijn eenLijn : bestelbonlijnen) {
					totaal = totaal.add(eenLijn.getSubTotaal());
				}
				request.setAttribute("totaal", totaal);
			} 
		} 
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!StringUtils.isGeldigString(naam)) {
			fouten.put("naam", "Naam niet ingevuld!");
			naam="";
		}
		String straat = request.getParameter("straat");
		if (!StringUtils.isGeldigString(straat)) {
			fouten.put("straat", "straat niet ingevuld!");
			straat="";
		}
		String huisnummer = request.getParameter("huisnummer");
		if (!StringUtils.isGeldigString(huisnummer)) {
			fouten.put("huisnummer", "huisnummer niet ingevuld!");
			huisnummer="";
		}
		String postcode = request.getParameter("postcode");
		if (!StringUtils.isGeldigString(postcode)) {
			fouten.put("postcode", "postcode niet ingevuld!");
			postcode="";
		}
		String gemeente = request.getParameter("gemeente");
		if (!StringUtils.isGeldigString(gemeente)) {
			fouten.put("gemeente", "gemeente niet ingevuld!");
			gemeente="";
		}
		String bestelwijze = request.getParameter("bestelwijze");
		if (!StringUtils.isGeldigBestelwijze(bestelwijze)) {
			fouten.put("bestelwijze", "geen geldige bestelwijze");
			bestelwijze=null;
		}
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			request.setAttribute("naam", naam);
			request.setAttribute("straat", straat);
			request.setAttribute("huisnummer", huisnummer);
			request.setAttribute("postcode", postcode);
			request.setAttribute("gemeente", gemeente);
			request.setAttribute("bestelwijze", bestelwijze);
			doGet(request, response);
		} else {
			HttpSession session = request.getSession(false);
			if (session != null) {
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
				if (mandje != null) {
					List<Bestelbonlijn> bestelbonlijnen = new ArrayList<>();
					mandje.keySet().stream().forEach(wijnId -> bestelbonlijnen
							.add(new Bestelbonlijn(wijnService.read(wijnId).get(), mandje.get(wijnId))));
					Adres adres = new Adres(straat, huisnummer, postcode, gemeente);
					int bestelwijzeInt = Integer.parseInt(bestelwijze);
					Long bonId = bestelbonService
							.createBestelbon(new Bestelbon(naam, adres, bestelwijzeInt, bestelbonlijnen));
					Bestelbon bestelbon = bestelbonService.read(bonId).get();
					bestelbon.getBestelbonlijnen().stream()
							.forEach(lijn -> wijnService.incrementInBestelling(lijn.getWijn().getId(), lijn.getAantal()));
					request.getSession().setAttribute(BEVESTIGD_BON_ID, bonId);
					response.sendRedirect(
							response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
				}
			} else {
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		}

	}

}
