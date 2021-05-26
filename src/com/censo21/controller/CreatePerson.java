package com.censo21.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.censo21.model.Person;
import com.censo21.services.PersonService;

public class CreatePerson implements IRequestAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateBirth"));
		Date dateBirth = new Date(date.getTime());
		boolean created = new PersonService().create(new Person(
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
		response.getWriter().println(created?"<h1>El registro Creo con exito! </h1>":"<h1> El registro no se pudo crear<h1>" );
}

}
