package com.censo21.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.censo21.services.PersonService;

public class DeletePerson implements IRequestAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isDeleted = new PersonService().delete(request.getParameter("documentType"),request.getParameter("documentNumber"));
		response.getWriter().println(isDeleted?"<h1>El registro " + request.getParameter("documentType") + ": " + request.getParameter("documentNumber") + "fue borrado</h1>"
		: "<h1> El registro no se borro o no existe<h1>");

	}
}
