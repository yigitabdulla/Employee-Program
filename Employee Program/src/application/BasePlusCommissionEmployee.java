package application;

public class BasePlusCommissionEmployee extends CommissionEmployee{
	
	private double baseSalary;
	
	public BasePlusCommissionEmployee(String firstName, String lastName, int grossSales,
			double commissionRate, double baseSalary) {
		super(firstName, lastName, grossSales, commissionRate);
		
		if(baseSalary < 0) {
			throw new ArithmeticException("Base salary must be equal or greater than 0");
			
		}
		
		this.baseSalary = baseSalary;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public double getPaymentAmount() {
		return super.getPaymentAmount() + baseSalary;
	}
	
	public String fields() {
		return super.fields() + "," + baseSalary;
	}
	
	public int getSSN() {
		
		return getSocialSecurityNumber();
	}
	
	public void setSSN(int SSN) {
		socialSecurityNumber = SSN;
		
	}
	
	public String toString() {
		
		return "Base-salaried commission employee: " +
				getFirstName() + " " + getLastName() + "\nSocial securty number: " + 
				getSocialSecurityNumber() + "\nGross sales:" + getGrossSales() +
				" Commission rate:" + getCommissionRate() + " Base Salary:" + baseSalary + " \nPayment Amount:"
				+ getPaymentAmount();
	}
}
