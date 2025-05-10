package hackathon;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup") // Ensure this line is uncommented
public class signup extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the signup.html page
    	  String name=request.getParameter("name");
    	  String email=request.getParameter("email");
    	  String pass=request.getParameter("password");
    	  String date=request.getParameter("date");
    		
			try {
				
				conn c=new conn();
				c.fun(name,email,pass,date);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 response.setContentType("text/html");
	            // RequestDispatcher rd = request.getRequestDispatcher("/signup.html");
				 //rd.forward(request,response);
	             response.sendRedirect("http://localhost:8082/hackathon/signup.html");
			}
    		   response.setContentType("text/html");
//    	          RequestDispatcher rdr = request.getRequestDispatcher("/login.html");
//    	          rdr.include(request,response);  
    		   response.sendRedirect("http://localhost:8082/hackathon/login.html");
    }
}

