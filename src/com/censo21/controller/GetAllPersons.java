package com.censo21.controller;

import java.io.IOException;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.censo21.model.Person;
import com.censo21.services.PersonService;
/**
 * @author ramon.arias
 * date: 24/05/2021
 * current version: 1
 */
public class GetAllPersons implements IRequestAction {
	/**
	 * Metodo para organizar las peticiones del servlet llamado a todas las personas y mostrandolo en el allPerson.jsp
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listPerson", new PersonService().getAll());
		request.getRequestDispatcher("allPerson.jsp").forward(request, response);
	}
}
