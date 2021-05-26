package com.censo21.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.censo21.model.Person;
import com.censo21.services.PersonService;
/**
 * @author ramon.arias
 * date: 24/05/2021
 * current version: 1
 */
public class UpdatePerson implements IRequestAction {
	/**
	 * Metodo para organizar las peticiones del servlet llamado para la actualizacion de datos de las personas
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateBirth"));
		Date dateBirth = new Date(date.getTime());
		boolean updated =new PersonService().update(new Person(
				request.getParameter("documentType"),
				request.getParameter("documentNumber"),
				request.getParameter("firstName"),
				request.getParameter("lastName"),
				dateBirth,
				request.getParameter("sex"),
				request.getParameter("address"),
				request.getParameter("phone"),
				request.getParameter("occupation"),
				Float.parseFloat(request.getParameter("monthlyIncome"))));
		response.getWriter().println(updated?"<h1>El registro se actualizo con exito! </h1>" : "<h1> El registro no se pudo crear<h1>" );
	}
}
