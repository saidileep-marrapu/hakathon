package hackathon;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class users {
  @Id
  private int id;
  private String name;
  private String email;
  private String pass;
  private String date;
  
@Override
public String toString() {
	return "users [id=" + id + ", name=" + name + ", email=" + email + ", pass=" + pass + ", date=" + date + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
  
}
