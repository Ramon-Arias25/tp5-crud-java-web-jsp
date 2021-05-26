package com.censo21.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.censo21.services.PersonService;
/**
 * @author ramon.arias
 * date: 25/05/2021
 * current version: 1
 */
public class ReportForLastName implements IRequestAction {
	/**
	 * Metodo para organizar las peticiones del servlet llamado para generar reporte personas_por_orden_de_apellido.txt
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean generated = new PersonService().ReportFile("reportForLastName");
		response.getWriter().println(generated?"<h1>El reporte fue generado con exito: </h1>"
		: "<h1> ERROR - El reporte - NO - fue generado con exito: <h1>");
	}

}
