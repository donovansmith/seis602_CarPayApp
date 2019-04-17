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
	public double moPay() {
		double moPayment = 0.0;
		double remainingBalance=principal;
		
		if (interestRateAPR != 0) {
			moPayment = ((interestRateAPR*principal)/(1-Math.pow((1+interestRateAPR),-numMonth)));
			moPayment = Math.round((moPayment+.005)*100.0)/100.0;
			if (moPayment > remainingBalance)
				moPayment = remainingBalance;
		}
		else if (principal%numMonth != 0){
			moPayment = Math.round((moPayment+.005)*100.0)/100.0;
			if (moPayment > remainingBalance)
				moPayment = remainingBalance;
		}
		else {
			moPayment = principal/numMonth;
		}
		
		
		return moPayment;
	}
	
	//Isha
    public double totalPaid(){
        double totalPaid = 0.0;
        if (interestRateAPR !=0) {
        	double tempPayment = ((interestRateAPR*principal)/(1-Math.pow((1+interestRateAPR),-numMonth)));
            totalPaid = tempPayment*numMonth;
        }
        else {
            totalPaid = carPrice;
        }
        
        return totalPaid;
    }
    
    //Dan
    public double totalInterest() {
    		return this.totalPaid - this.principal;
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
}
