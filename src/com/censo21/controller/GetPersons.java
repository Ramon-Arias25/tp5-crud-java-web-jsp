package com.censo21.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.censo21.services.PersonService;

public class GetPersons  implements IRequestAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("persons", new PersonService().getAllOrderByAtribute(request.getParameter("atributeOrder")));

	}

}
