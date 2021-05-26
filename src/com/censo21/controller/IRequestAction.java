package com.censo21.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IRequestAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
