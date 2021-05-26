package com.censo21.controller;

import java.io.IOException;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.censo21.model.Person;
import com.censo21.services.PersonService;

public class GetAllPersons implements IRequestAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listPerson", new PersonService().getAll());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
