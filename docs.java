package hackathon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/doc")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB threshold
                 maxFileSize = 1024 * 1024 * 5,   // 5MB max file size
                 maxRequestSize = 1024 * 1024 * 10) // 10MB max request size
public class docs extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Directory where uploaded files will be saved
    private static final String UPLOAD_DIRECTORY = "uploads"; // Adjusted to relative path

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the absolute path of the web app upload directory
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        // Create upload directory if it doesn't exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // List to store uploaded file information
        List<FileUpload> uploadedFiles = new ArrayList<>();

        try {
            // Loop through each part to upload files
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);

                // If a file was selected, save it
                if (fileName != null && !fileName.isEmpty()) {
                    String filePath = uploadPath + File.separator + fileName;
                    part.write(filePath); // Write file to upload directory

                    // Add file info to list
                    uploadedFiles.add(new FileUpload(fileName, filePath));
                }
            }

            // Only insert into the database if files were uploaded successfully
            if (!uploadedFiles.isEmpty()) {
                saveFilesToDatabase(uploadedFiles);
               // response.getWriter().println("All files uploaded and saved to database successfully!");
                response.setContentType("text/html");
//           	 RequestDispatcher rd=request.getRequestDispatcher("/successful.html");
//            	 rd.include(request, response);
                response.sendRedirect("http://localhost:8082/hackathon/successful.html");
            } else {
               // response.getWriter().println("No files were uploaded.");
            	  response.setContentType("text/html");
//                	 RequestDispatcher rd=request.getRequestDispatcher("/documents.html");
//                 	 rd.forward(request, response);       
            	  response.sendRedirect("http://localhost:8082/hackathon/documents.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
          //  response.getWriter().println("Error: Unable to save file details to the database.");
            response.setContentType("text/html");
//          	 RequestDispatcher rd=request.getRequestDispatcher("/documents.html");
//           	 rd.forward(request, response);
            response.sendRedirect("http://localhost:8082/hackathon/documents.html");
        }
       
    }

    // Utility method to get file name from HTTP header
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // Method to save file information to the database using Hibernate
    private void saveFilesToDatabase(List<FileUpload> files) {
    	Configuration con= new Configuration().configure().addAnnotatedClass(filedao.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction transaction = null;

        try {
            // Start a transaction
            transaction = session.beginTransaction();

            // Iterate over uploaded files and save each one
            for (FileUpload file : files) {
                filedao document = new filedao();
                document.setDocumentName(file.getFileName());
                document.setFilePath(file.getFilePath());

                // Save the document
                session.save(document);
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Inner class to store file information
    private static class FileUpload {
        private final String fileName;
        private final String filePath;

        public FileUpload(String fileName, String filePath) {
            this.fileName = fileName;
            this.filePath = filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public String getFilePath() {
            return filePath;
        }
    }
}
