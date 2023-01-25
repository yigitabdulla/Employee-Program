package application;

public class HourlyEmployee extends Employee {
	
	private double wage;
	private int hours;
	
	public HourlyEmployee(String firstName, String lastName, double wage, int hours) {
		super(firstName, lastName);
		
		if(wage < 0) {
			throw new ArithmeticException("Wage must be greater than or equal to 0!");
		}
		
		if(hours < 0 || hours >= 168) {
			throw new ArithmeticException("Hours must be between 0 and 168!");
		}
		
		this.wage = wage;
		this.hours = hours;
	}
	
	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		if(wage < 0) {
			throw new ArithmeticException("Wage must be greater than or equal to 0!");
		}
		else {
			this.wage = wage;
		}
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		if(hours < 0 || hours >= 168) {
			throw new ArithmeticException("Hours must be between 0 and 168!");
		}
		else {
			this.hours = hours;
		}
	}

	@Override
	public double getPaymentAmount() {
		
		if(hours <= 40) {
			return wage * hours;
		}
		else if(hours > 40) {
			return 40 * wage + (hours - 40) * wage * 1.5;
		}
		
		return -1;
	}
	
	public String toString() {
		return "Hourly employee: " + super.toString() + "\nHourly wage: " + wage + " Hours worked: " + hours
				+ "\nPayment amount: " + getPaymentAmount();
	}
	
	public String fields() {
		return super.fields() + "," + wage+","+ hours;
	}

	
	public int getSSN() {
		return getSocialSecurityNumber();
	}
	
	public void setSSN(int SSN) {
		socialSecurityNumber = SSN;
	}
}
