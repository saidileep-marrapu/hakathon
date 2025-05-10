package hackathon;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class detailsdao {
//  public static void main(String args[]) {
//	  detailsdao d= new detailsdao();
//	  d.fun("name", "adress", "state","father", "mother",6753432436l, 652376532456l, "college", 3);
//  }
	public void fun(String name,String adress,String statename,String fathername,String mothername,long mobilenumber,long aadharnumber,String college,int academicyear) {
    	
      registeration s=new registeration();
      s.setName(name);
      s.setAdress(adress);
      s.setStatename(statename);
      s.setFathername(fathername);
      s.setMothername(mothername);
      s.setMobilenumber(mobilenumber);
      s.setAadharnumber(aadharnumber);
      s.setCollegename(college);
      s.setAcademicyear(academicyear);
    
        Configuration con = new Configuration().configure().addAnnotatedClass(registeration.class);
       // ServiceRegistry se = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory();
        
        Session session = sessionFactory.openSession();
       Transaction t=session.beginTransaction();
         session.save(s);
          
         //s = (students)session1.get(students.class,s.getId()); 
 
        t.commit();
        session.close();
        sessionFactory.close(); 
    }
}
