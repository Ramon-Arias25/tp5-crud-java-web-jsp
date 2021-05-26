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
 * @author ramon.arias
 * date: 22/05/2021
 * current version: 1
 */
@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Variable Map para generar una interfaz de comando para extender la funcionalidad del servlet 
	 * y organizarla en clases indivudales
	 */
	private Map<String, IRequestAction> requestActionMap = new HashMap<>();
	/**
	 * en el constructor agrega los comandos a HashMap requestActionMap 
	 */
    public PersonServlet () {
        super();
		requestActionMap.put("create", new CreatePerson());
		requestActionMap.put("update", new UpdatePerson());
		requestActionMap.put("delete", new DeletePerson());
		requestActionMap.put("getAll", new GetAllPersons());
		requestActionMap.put("getOne", new GetOnePerson());
		////comandos para generar los reportes
		requestActionMap.put("reportForLastName", new ReportForLastName());
		requestActionMap.put("reportAdultList", new ReportAdultList());
		requestActionMap.put("reportPovertyLine", new ReportPovertyLine());
		requestActionMap.put("reportCountByGender", new ReportCountByGender());
    }
	/**
	 * metodo que recibe el parametro por get y segun el map requestActionMap llama a la clase segun sea el caso
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionRequest = request.getParameter("requestAction") != null?request.getParameter("requestAction"):"getAll";
		try {
			requestActionMap.get(actionRequest).execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * metodo que recibe el parametro por post y segun el map requestActionMap llama a la clase segun sea el caso
	 */
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
