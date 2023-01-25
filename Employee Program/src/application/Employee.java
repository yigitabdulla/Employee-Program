package application;

public abstract class Employee implements Payable {
	
	private String firstName;
	private String lastName;
	int socialSecurityNumber = 0;
	
	
	
	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	
	
	public String toString() {
		return firstName + " " + lastName + "\nSocial security number: " + socialSecurityNumber;
	}
	
	public String fields() {
		return firstName + "," + lastName + "," + socialSecurityNumber;
	}
	public void setSSN(int SSN) {
		socialSecurityNumber = SSN;
	}
	public int getSSN() {
		
		return socialSecurityNumber;
	}
	
}
