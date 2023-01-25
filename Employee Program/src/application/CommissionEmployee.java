package application;

public class CommissionEmployee extends Employee {
	
	private int grossSales;
	private double commissionRate;
	
	public CommissionEmployee(String firstName, String lastName, int grossSales, double commissionRate) {
		super(firstName, lastName);
		
		if(grossSales < 0) {
			throw new ArithmeticException("Gross sales must be equal or greater than 0");
			
		}
		if((commissionRate <0 || commissionRate >1)) {
			throw new ArithmeticException("Commision rate must be between 0 and 1");
			
		}
		
		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
		
	}
	
	
	public int getGrossSales() {
		return grossSales;
	}


	public void setGrossSales(int grossSales) {
		this.grossSales = grossSales;
	}


	public double getCommissionRate() {
		return commissionRate;
	}


	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}


	@Override
	public double getPaymentAmount() {
		// TODO Auto-generated method stub
		return commissionRate * grossSales;
	}
	
	public String toString() {
		return "Commission employee: " + super.toString() + "\nGross sales:" + grossSales +
				" Commission rate:" + commissionRate + "\nPayment amount: " + getPaymentAmount();
	}
	
	public String fields() {
		return super.fields() + "," + grossSales + "," + commissionRate;
	}

	
	public int getSSN() {
		
		return getSocialSecurityNumber();
	}
	

	public void setSSN(int SSN) {
		socialSecurityNumber = SSN;
		
	}
	
}
