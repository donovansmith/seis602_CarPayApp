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
		double remainingBalance=principal; //fake value, would keep track of Principal-PaymentsMade
		
		if (principal <= 0) { //if you don't owe any money, you don't owe any money.
			moPayment = 0;
		}
		else if (interestRateAPR != 0) { //if the interest rate is not zero, run standard monthly payment function
			moPayment = ((interestRateAPR*principal)/(1-Math.pow((1+interestRateAPR),-numMonth)));
		}
		else if (principal%numMonth != 0){ //if remainder is not zero, round up payment to next cent
			moPayment = principal/numMonth+.005;
			if (moPayment > remainingBalance) //if remainingBalance is less then moPayment, pay off remainingBalance
				moPayment = remainingBalance;
		}
		else {
			moPayment = principal/numMonth; //if remainder is zero, divide principal by number of months
		}
		return moPayment;
	}
	
	//Isha
    public double totalPaid(){ //Total Paid doesn't take into effect trade in value
        double totalPaid = 0.0;
        if (interestRateAPR !=0) {
            totalPaid = moPayment*numMonth; //set to principal (safer)
        }
        else {
            totalPaid = carPrice; //set to moPayment*numMonth
        }
        
        return totalPaid;
    }
    
    //Dan
    public double totalInterest() { //Total Interest doesn't take into effect trade in value
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
