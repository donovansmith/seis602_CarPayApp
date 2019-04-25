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
	private String output;
	
	public CarApp(int carPrice, int downPayment, double interestRate, int numMonth) {
		this.carPrice=carPrice;
		this.downPayment=downPayment;
		//Convert to APR
		this.interestRateAPR=(interestRate/100)/12;
		this.numMonth=numMonth;
		
		this.principal=carPrice-downPayment;
		this.moPayment=moPay();
		this.totalPaid=totalPaid();
		this.totalIntPaid=totalInterest();		
	}
		
	//Victor
	//Calculate Monthly Payments
	public double moPay() {
		double moPayment = 0.0;
		
		if (interestRateAPR != 0) {
			moPayment = ((interestRateAPR*principal)/(1-Math.pow((1+interestRateAPR),-numMonth)));
			moPayment = Math.round((moPayment+.005)*100.0)/100.0;
		}
		else if (principal%numMonth != 0){
			moPayment = principal/numMonth;
			moPayment = Math.round((moPayment+.005)*100.0)/100.0;
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
            totalPaid = moPayment*numMonth;
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
    public String Payment() {
    	double balance;
    	String output;
    	if ((this.moPayment * this.numMonth) > this.principal) {
    		balance = this.principal - (this.moPayment * (this.numMonth - 1)); 
    		output = (numMonth - 1) + " payments of " + moPayment + " and 1 payment of " + balance;
    	}
    	else {
    		output = (numMonth) + " payments of " + moPayment;
    	}
    	return output;
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
	public String getBalance() {
		return output;
	}
}
