package pt.unl.fct.di.apdc.firstwebapp.util;


public class RegisterData {
	
	
	public String username;
	public String email;
	public String name;
	public String phoneNumb;
	public String password;
	
	
	public RegisterData() {}
	
	public RegisterData(String username, String email, String name, String phoneNumb, String password) {
		this.username = username;
		this.email = email;
		this.name = name;
		this.phoneNumb = phoneNumb;
		this.password = password;
	}
	
	public Boolean validRegistration() {
		return username != null 
				&& email != null
				&& name != null
				&& phoneNumb != null
				&& password != null;
	}
	
	
	
	
	 

}
