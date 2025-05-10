package hackathon;

import java.util.List;

import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.ServiceRegistry;



public class demo {
    
	public static void main(String args[]) {
    String email="dileepmarrapu80@gmail.com";
    String pass="123456";
      //users s=new users();
        Configuration con = new Configuration().configure().addAnnotatedClass(users.class);
       // ServiceRegistry se = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory();
        
        Session session = sessionFactory.openSession();
       Transaction t=session.beginTransaction();
       @SuppressWarnings("rawtypes")
	   NativeQuery query = session.createNativeQuery( "SELECT id FROM users WHERE email = :email AND pass = :pass");
           query.setParameter("email", email);
           query.setParameter("pass", pass);


        
		int a=(Integer)query.uniqueResult();

           users s = (users)session.get(users.class,a); 
           
          t.commit();
          if(email.equals(s.getEmail())&&pass.equals(s.getPass())) {
      		System.out.println(s.getEmail()+" "+s.getPass());
      	  }
         
//          for (Tuple row : resultList) {
//        	  if(email.equals(row.get(1))&&pass.equals(row.get(3))) {
//        		System.out.println(row.get(1)+" "+row.get(3));
//        	  }
//     	    	System.out.println("hgvhgd");
//     	    }
       //  users s = (users)session.get(users.class,row.get(4)); 
       
        session.close();
        sessionFactory.close(); 
    }
}

