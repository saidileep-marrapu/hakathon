package hackathon;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class registeration {
  @Id
  private int id;
  private String name;
  private String dob;
  private String adress;
  public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
private String statename;
  private String fathername;
  private String mothername;
  private long mobilenumber;
  private long aadharnumber;
  private String collegename;
  private int academicyear;
  
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
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getStatename() {
	return statename;
}
public void setStatename(String statename) {
	this.statename = statename;
}
public String getFathername() {
	return fathername;
}
public void setFathername(String fathername) {
	this.fathername = fathername;
}
public String getMothername() {
	return mothername;
}
public void setMothername(String mothername) {
	this.mothername = mothername;
}
public long getMobilenumber() {
	return mobilenumber;
}
public void setMobilenumber(long mobilenumber) {
	this.mobilenumber = mobilenumber;
}
public long getAadharnumber() {
	return aadharnumber;
}
public void setAadharnumber(long aadharnumber) {
	this.aadharnumber = aadharnumber;
}
public String getCollegename() {
	return collegename;
}
public void setCollegename(String collegename) {
	this.collegename = collegename;
}
public int getAcademicyear() {
	return academicyear;
}
public void setAcademicyear(int academicyear) {
	this.academicyear = academicyear;
}
}
