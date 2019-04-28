package application;

public class CarApp {
	//Instance Variables
	private int creditScore;
	private double carPrice;
	private double downPayment;
	private double interestRateAPR;
	private int numMonth;
	
	private double principal;	
	private double moPayment;
	private double totalPaid;
	private double totalIntPaid;
	private double remainingBalance;
	
	public CarApp(int carPrice, int downPayment, double interestRate, int numMonth) {
		this.carPrice=carPrice;
		this.downPayment=downPayment;
		//Convert to APR
		this.interestRateAPR=(interestRate/100)/12;
		this.numMonth=numMonth;
		
		this.principal=carPrice-downPayment;
		this.totalPaid=totalPaid();
		this.moPayment=moPay();
		this.totalIntPaid=totalInterest();
		this.remainingBalance=remainingBalance();
	}
		
	//Victor
	//Calculate Monthly Payments
	public double moPay() {
		double moPayment = 0.0;
		
		if (interestRateAPR != 0) {
			moPayment = ((interestRateAPR*principal)/(1-Math.pow((1+interestRateAPR),-numMonth)));
			moPayment = Math.round((moPayment)*100.0)/100.0;
		}
		else if (principal%numMonth != 0){
			moPayment = principal/numMonth;
			moPayment = Math.round((moPayment)*100.0)/100.0;
		}
		else {
			moPayment = principal/numMonth;
		}		
		
		return moPayment;
	}
	
	//Isha
	//Calculate Total Paid
    public double totalPaid(){ //Total Paid doesn't take into effect trade in value
        double totalPaid = 0.0;
        if (interestRateAPR !=0) {
        	totalPaid = ((interestRateAPR*principal)/(1-Math.pow((1+interestRateAPR),-numMonth)));
        	totalPaid = Math.round((moPayment+.005)*100.0)/100.0;
        }
        else {
            totalPaid = principal;
        }
        
        return totalPaid;
    }
    
    //Dan
    //Calculate Total Interest
    public double totalInterest() {
    		return this.totalPaid - this.principal;
    }
    
    //Vic
    //Calculate Remaining Balance
    public double remainingBalance() {
    	double remainingBalance;
    	if ((this.moPayment * this.numMonth) != this.principal) {
    		remainingBalance = this.principal - (this.moPayment * (this.numMonth - 1));
    	}
    	else {
    		remainingBalance = 0.0;
    	}
    	return remainingBalance;
    }
    
    //Getters
	public double getCarPrice() { 
		return carPrice; 
	}  
	public double getDownPayment() { 
		return downPayment; 
	}  
	public double getInterestRate() { 
		return interestRateAPR; 
	}  
	public int getNumMonth() { 
		return numMonth; 
	}  
	public double getMoPayment() { 
		return moPayment; 
	} 
	public double getTotalPaid() { 
		return totalPaid; 
	} 	 
	public double getTotalIntPaid() { 
		return totalIntPaid; 
	}
	public double getBalance() {
		return remainingBalance;
	}
	public double getRemainingBalance() {
		return remainingBalance;
	}
}
