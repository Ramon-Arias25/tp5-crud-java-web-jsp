package com.censo21.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.censo21.services.PersonService;
/**
 * @author ramon.arias
 * date: 25/05/2021
 * current version: 1
 */
public class GetOnePerson implements IRequestAction {
	/**
	 * Metodo para organizar las peticiones del servlet en el proceso de actualizacion de datos de las personas
	 * ya que primero presenta los datos a actualiza
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("person.jsp");
		request.setAttribute("person", new PersonService().getOne(request.getParameter("documentType"),request.getParameter("documentNumber")));
		dispatcher.forward(request, response);
	}

}
