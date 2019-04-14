package application;

import java.text.DecimalFormat;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.*;

public class mainController {
	//Output Variables
	@FXML
	private TextField intPaidTF;
	@FXML
	private TextField moPaymentTF;
	@FXML
	private TextField totalPaidTF;
	@FXML
	private Text creditComment1TF;
	@FXML
	private Text creditComment2TF;
	@FXML
	private Text creditComment3TF;
	
	//Input Variables
	@FXML
	private TextField carPriceTF;
	@FXML
	private TextField tradeInPaymentTF;
	@FXML
	private TextField intRateTF;
	@FXML
	private TextField noMonthsTF;
	@FXML
	private ChoiceBox<String> creditScoreCB = new ChoiceBox<String>();

	//Main Button
	@FXML
	private Button carValueButton;
	
	@FXML
	public void initialize() {
		//Credit Score Dropdown Creation
		creditScoreCB.setItems(FXCollections.observableArrayList(
				"Super prime (781-850)", 
				"Prime (661-780)", 
				"Nonprime (601-660)",
				"Subprime (501-600)", 
				"Deep subprime (300-500)"
				));
		
		//Main Button
		carValueButton.setOnAction((event) -> {
		    //INSERT METHODS TO BE CALLED HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//convert TextFields to correct variable types
			double carPrice = Double.parseDouble(carPriceTF.getText());
			double tradeInPayment = Double.parseDouble(tradeInPaymentTF.getText());
			double intRate = Double.parseDouble(intRateTF.getText())/1200;
			int noMonths = Integer.parseInt(noMonthsTF.getText());
			if (noMonths>84) {
				noMonths=84;
				noMonthsTF.setText("84");
			}
			
			//setup new variables
			DecimalFormat decimal = new DecimalFormat("0.00");
			double monthlyPay;
			double principal = carPrice - tradeInPayment;
			double remainingBalance = principal;
			double totalPaid;
			double intPaid;
			
			//determine monthlyPay
			if (intRate != 0) {
				monthlyPay = ((intRate*principal)/(1-Math.pow((1+intRate),-noMonths)));
			}
			else if (principal%noMonths != 0){
				monthlyPay = principal/noMonths+.005;
				if (monthlyPay > remainingBalance)
					monthlyPay = remainingBalance;
			}
			else {
				monthlyPay = principal/noMonths;
			}
			
			//determine total paid
			if (intRate !=0) {
				totalPaid = monthlyPay*noMonths;
			}
			else {
				totalPaid = principal;
			}
			
			//determine interest paid
			intPaid = totalPaid - principal;
			
			//output values
			moPaymentTF.setText(decimal.format(monthlyPay));
		    totalPaidTF.setText(decimal.format(totalPaid));
		    intPaidTF.setText(decimal.format(intPaid));
		    
		    creditComment1TF.setText("Can you pay in Apples & Milk?");
		});
		
		
	}	
}
