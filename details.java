package hackathon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class details extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // JDBC driver name and database 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 String name = request.getParameter("student-name");
         //String gender = request.getParameter("gender");
         //String dob = request.getParameter("dob");
         String address = request.getParameter("address");
         String state = request.getParameter("state");
         String fatherName = request.getParameter("father-name");
         String motherName = request.getParameter("mother-name");
         long mobile = Long.parseLong(request.getParameter("mobile"));
         long aadhar = Long.parseLong(request.getParameter("aadhaar"));
         String college = request.getParameter("college");
         int academicYear = Integer.parseInt(request.getParameter("academic-year"));
         
        // Set the response message's MIME type
        response.setContentType("text/html;charset=UTF-8");
        
       // out.print(name+""+address+" "+state+" "+fatherName+" "+motherName+" "+mobile+""+aadhar+""+college+" "+academicYear);
        try {
            // Register JDBC driver
        	
           detailsdao d= new detailsdao();
           d.fun(name,address,state,fatherName,motherName,mobile,aadhar,college,academicYear);
           
        } catch (Exception e) {
        	System.out.println(e);
        	response.setContentType("text/html");
//        	 RequestDispatcher rd=request.getRequestDispatcher("/register.html");
//         	 rd.forward(request, response);
        	 response.sendRedirect("http://localhost:8082/hackathon/register.html");
        }
        response.setContentType("text/html");
//      	 RequestDispatcher rd=request.getRequestDispatcher("/documents.html");
//      	 rd.include(request, response);
        response.sendRedirect("http://localhost:8082/hackathon/documents.html");
    }
}
