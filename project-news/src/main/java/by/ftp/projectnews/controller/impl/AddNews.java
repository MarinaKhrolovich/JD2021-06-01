package by.ftp.projectnews.controller.impl;

import java.io.IOException;

import by.ftp.projectnews.bean.User;
import by.ftp.projectnews.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddNews implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//может использоваться эта команда, только если сессия создана
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			// redirect gotoauthorizationpage
			
			response.sendRedirect("Controller?command=AUTHORIZATION&message='You session is lost.You must sign in to the system!'");
			
			return;
		}
		
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			// redirect gotoauthorizationpage
			
			response.sendRedirect("Controller?command=AUTHORIZATION&message='You must sign in to the system!'");
			
			return;
		}
		
		if(!"admin".equals(user.getRole())) {
			session.removeAttribute("user");
			//log
			// redirect gotoauthorizationpage
			response.sendRedirect("Controller?command=AUTHORIZATION&message='You must sign as an administrator!'");
			
			return;
		}
		
		
		// to do
		
		
	}
}