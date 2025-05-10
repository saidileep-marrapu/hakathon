package hackathon;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class conn {
    
	public void fun(String name,String email,String pass,String date) {
    	
      users s=new users();
      s.setName(name);
      s.setEmail(email);
      s.setPass(pass);
      s.setDate(date);
        Configuration con = new Configuration().configure().addAnnotatedClass(users.class);
       // ServiceRegistry se = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory();
        
        Session session = sessionFactory.openSession();
       Transaction t=session.beginTransaction();
         session.save(s);
          
         //s = (students)session1.get(students.class,s.getId()); 
       
        System.out.println(s);
        t.commit();
        session.close();
        sessionFactory.close(); 
    }
}
