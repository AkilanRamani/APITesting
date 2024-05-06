package TestAutomation;

import java.util.List;



public class Students {
	
	  public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Student> students;  
	  public String status;
	  public String message;
	    // getter and setter  
	    public List<Student> getStudent() {  
	        return students;  
	    }  
	    public void setStudent(List<Student> students) {  
	        this.students = students;  
	    }  
	    
	    

}
