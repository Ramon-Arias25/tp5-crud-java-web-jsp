package com.censo21.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.censo21.services.PersonService;

public class GetOnePerson implements IRequestAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("person.jsp");
		request.setAttribute("person", new PersonService().getOne(request.getParameter("documentType"),request.getParameter("documentNumber")));
		dispatcher.forward(request, response);
	}

}
