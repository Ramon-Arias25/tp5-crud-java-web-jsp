package com.censo21.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersonaServlet
 */
@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, IRequestAction> requestActionMap = new HashMap<>();

    public PersonServlet () {
        super();
		requestActionMap.put("create", new CreatePerson());
		requestActionMap.put("update", new UpdatePerson());
		requestActionMap.put("delete", new DeletePerson());
		requestActionMap.put("getAll", new GetAllPersons());
		requestActionMap.put("getOne", new GetOnePerson());
		requestActionMap.put("get", new GetPersons());
		////comandos para generar los reportes
		requestActionMap.put("reportForLastName", new ReportForLastName());
		requestActionMap.put("reportAdultList", new ReportAdultList());
		requestActionMap.put("reportPovertyLine", new ReportPovertyLine());
		requestActionMap.put("reportCountByGender", new ReportCountByGender());
    }
	
	@Override
	public void init() throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionRequest = request.getParameter("requestAction") != null?request.getParameter("requestAction"):"getAll";
		try {
			requestActionMap.get(actionRequest).execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionRequest = request.getParameter("requestAction");
		try {
			requestActionMap.get(actionRequest).execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
