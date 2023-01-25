package application;

public class SalariedEmployee extends Employee {
	
	private double weeklySalary;
	
	public SalariedEmployee(String firstName, String lastName,  double weeklySalary) {
		super(firstName, lastName);
		
		if(weeklySalary < 0) {
			throw new ArithmeticException("Weekly salary must be greater than or equal to 0!");
		}
		
		this.weeklySalary = weeklySalary;
		
	}
	
	public double getWeeklySalary() {
		return weeklySalary;
	}

	public void setWeeklySalary(double weeklySalary) {
		if(weeklySalary < 0) {
			throw new ArithmeticException("Weekly salary must be greater than or equal to 0!");
		}

		else {
			this.weeklySalary = weeklySalary;
		}
	}
	
	@Override
	public double getPaymentAmount() {
		// TODO Auto-generated method stub
		return weeklySalary;
	}
	
	public String toString() {
		return "Salaried employee: " + super.toString() + "\nWeekly salary: " + weeklySalary +
				"\nPayment amount: " + getPaymentAmount();
	}
	
	public String fields() {
		return super.fields() + "," + weeklySalary;
	}

	
	public int getSSN() {
		return getSocialSecurityNumber();
	}

	@Override
	public void setSSN(int SSN) {
		socialSecurityNumber = SSN;
	}

}
