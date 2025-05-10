package hackathon;

import java.io.IOException;
import java.io.PrintWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static SessionFactory sessionFactory;

    // Initialize the SessionFactory once
    static {
        try {
            Configuration config = new Configuration().configure().addAnnotatedClass(users.class);
            sessionFactory = config.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace(); // Logging the error for debugging purposes
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response encoding and content type
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        try (PrintWriter pw = response.getWriter(); Session session = sessionFactory.openSession()) {
            Transaction t = session.beginTransaction();

            // Using parameterized query to prevent SQL injection
            //@SuppressWarnings("unchecked")
            @SuppressWarnings("rawtypes")
			NativeQuery query = session.createNativeQuery("SELECT id FROM users WHERE email = :email AND pass = :pass");
            query.setParameter("email", email);
            query.setParameter("pass", pass);

            Integer userId = (Integer) query.uniqueResult();
            t.commit();

            if (userId != null) {
                RequestDispatcher r = request.getRequestDispatcher("/welcome.html");
                r.forward(request, response);
            } else {
                pw.println("<p style='color:red;'>Invalid email or password.</p>");
                RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                rd.include(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();  // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred. Please try again later.");
        }
    }

    

    @Override
    public void destroy() {
        // Close the SessionFactory when the servlet is destroyed
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
