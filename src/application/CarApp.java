package application;

import java.math.*;

public class CarApp {
	//Instance Variables
	private int creditScore;
	private double carPrice;
	private double downPayment;
	private double interestRate;
	private int numMonth;
	private double principle;
	private double monthlyPayment;
	private double totalPay;
	private double totalInt;
	
	CarApp(){
		this.creditScore = 0;
		this.carPrice = 0;
		this.downPayment = 0;
		this.interestRate = 0;
		this.numMonth = 0;
		this.principle = 0;
		this.monthlyPayment = 0;
	}
	
	//finds monthly payment based on amortization formula https://en.wikipedia.org/wiki/Amortization_calculator
	public void MonthlyPayment() {
		this.principle = this.carPrice - this.downPayment;
		
		if(this.interestRate > 0)
			//this.monthlyPayment = (this.principle * ((this.interestRate * Math.pow(1+this.interestRate, this.numMonth)) / (Math.pow(1 + this.interestRate, this.numMonth)-1)));
			this.monthlyPayment = (this.principle * (this.interestRate + (this.interestRate/(Math.pow((1+this.interestRate),this.numMonth)-1)))); 
		else if(this.interestRate == 0)
			this.monthlyPayment = (this.principle / this.numMonth);
	}

	public void TotalPayment() {
		this.totalPay = this.monthlyPayment * this.numMonth;
	}


	public void TotalInterest() {
		this.totalInt = this.totalPay - this.principle;
	}
	

	//getters and setters
	public double getTotalInt() {
		return totalInt;
	}
	
	public double getTotalPay() {
		return totalPay;
	}
	
	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getNumMonth() {
		return numMonth;
	}

	public void setNumMonth(int numMonth) {
		this.numMonth = numMonth;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

}
