package com.censo21.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.censo21.services.PersonService;
/**
 * @author ramon.arias
 * date: 24/05/2021
 * current version: 1
 */
public class DeletePerson implements IRequestAction {
	/**
	 * Metodo para organizar las peticiones del servlet llamado para borrado de datos de una persona
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isDeleted = new PersonService().delete(request.getParameter("documentType"),request.getParameter("documentNumber"));
		response.getWriter().println(isDeleted?"<h1>El registro " + request.getParameter("documentType") + ": " + request.getParameter("documentNumber") + "fue borrado</h1>"
		: "<h1> El registro no se borro o no existe<h1>");

	}
}
