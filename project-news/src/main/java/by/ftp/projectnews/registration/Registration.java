package by.ftp.projectnews.registration;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Registration() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
		
		
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
		
		
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		    
		String login = request.getParameter("login");
			String password = request.getParameter("password");
			String command = request.getParameter("command");
						
			PrintWriter out = response.getWriter();
			
			
			switch (command) {
			case "reg":
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
				requestDispatcher.forward(request, response);
				break;
			case "autho":
				RequestDispatcher requestDispatcherAuto = request.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp");
				requestDispatcherAuto.forward(request, response);
				break;
			case "didreg":
				out.println("Registration completed successfully!");
				out.println("<br />Your login: " + login);
				out.println("<br />Your password: " + password);
				break;
			case "didautho":
				out.println("Autorization completed successfully!");
				break;
		
			default:
				break;
			}
			
	}

}
