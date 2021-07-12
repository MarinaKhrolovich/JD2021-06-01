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
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			// redirect gotoauthorizationpage
			return;
		}
		
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			// redirect gotoauthorizationpage
			return;
		}
		
		if(!"admin".equals(user.getRole())) {
			session.removeAttribute("user");
			//log
			// redirect gotoauthorizationpage
			return;
		}
		
		
		// to do
		
		
	}
}
