package com.censo21.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author ramon.arias
 * date: 24/05/2021
 * current version: 1
 * Interfaz para construir la interfaz comando usada en el PersonServlet
 */
public interface IRequestAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
