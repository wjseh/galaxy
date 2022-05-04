package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.Covid19InfStateService;
import service.GuroPointFocInfoService;
import service.SearchService;
import service.TourStnInfoService;

@WebServlet("*.do")
public class OpenAPIController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OpenAPIController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		ActionForward af = null;
		
		switch(command) {
		case "searchPage.do":
			af = new ActionForward("search/search.jsp", false);
			break;
		case "search.do":
			SearchService service = new SearchService();
			service.execute(request, response);
			break;
		case "guroPointFocInfoSvcPage.do":
			af = new ActionForward("guro/guro.jsp", false);
			break;
		case "guroPointFocInfoSvc.do":
			GuroPointFocInfoService service2 = new GuroPointFocInfoService();
			service2.execute(request, response);
			break;
		case "covid.do":
			af = new ActionForward("covid/covid.jsp", false);
			break;
		case "Covid19.do":
			Covid19InfStateService service3 = new Covid19InfStateService();
			service3.execute(request, response);
			break;
		case "tourPage.do":
			af = new ActionForward("tour/tour.jsp", false);
			break;
		case "TourStnInfo.do":
			TourStnInfoService service4 = new TourStnInfoService();
			service4.execute(request, response);
			break;
		}
	
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}