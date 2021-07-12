package by.ftp.projectnews.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;

import by.ftp.projectnews.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Unknown command!");
	}
	
}
