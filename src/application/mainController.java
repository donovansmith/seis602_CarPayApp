/*
 * Sources:
 * 	JavaFX ChoiceBox: https://o7planning.org/en/11087/javafx-choicebox-tutorial
 */

package application;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	
	//Main CarApp Object
	private CarApp CarAppObj;
	
	@FXML
	public void initialize() {
		ObservableList<String> creditScoreOptions = FXCollections.observableArrayList(
				"Super prime (781-850)", 
				"Prime (661-780)", 
				"Nonprime (601-660)",
				"Subprime (501-600)", 
				"Deep subprime (300-500)"
		);
		
		//Credit Score Dropdown Creation
		creditScoreCB.setItems(creditScoreOptions);
		
        //Credit Score Listener
        ChangeListener<String> changeListener = new ChangeListener<String>() {        	 
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					if (newValue == "Super prime (781-850)") {
					creditComment1TF.setText("Based on your score, the average rate is 3.08% (new) or 3.76% (used).");		
					}
					else if (newValue == "Prime (661-780)") {
					creditComment1TF.setText("Based on your score, the average rate is 3.99% (new) or 5.45% (used).");		
					}
					else if (newValue == "Nonprime (601-660)") {
					creditComment1TF.setText("Based on your score, the average rate is 6.83% (new) or 9.98% (used).");		
					}
					if (newValue == "Subprime (501-600)") {
					creditComment1TF.setText("Based on your score, the average rate is 11.11% (new) or 16.23% (used).");		
					}
					if (newValue == "Deep subprime (300-500)") {
					creditComment1TF.setText("Based on your score, the average rate is 13.95% (new) or 19.38% (used)."
							+ " Consider buying an inexpensive used car and refinancing in 6-12 months.");		
					}
				}
			}
        };
        
        //Associate Choicebox to Listener
        creditScoreCB.getSelectionModel().selectedItemProperty().addListener(changeListener);
		
		//Currency Formatter
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); 
		
		//Main Button
		carValueButton.setOnAction((event) -> {
			//Reset to defaults
			boolean proceedCalculation = true;
			//reset
			creditComment1TF.setText("");
		    
		    //Input Validate Car Price
			try {
				 int carPrice = Integer.parseInt(carPriceTF.getText());
				 if (carPrice<0) {
					 creditComment1TF.setText("Car price must be positive.");
					 proceedCalculation = false;
				 }
			}
			catch(Exception e) {
				creditComment1TF.setText("There is a problem with Car price. Please make sure this is a positive integer.");
				 proceedCalculation = false;
			}
			
			//Input Validate Trade In Payment
			try {
				 int tradeInPayment = Integer.parseInt(tradeInPaymentTF.getText());
				 if (tradeInPayment<0) {
					 creditComment1TF.setText("Trade-in payment must be positive.");
					 proceedCalculation = false;
				 }
			}
			catch(Exception e) {
				creditComment1TF.setText("There is a problem with Trade-in payment. Please make sure this is a positive integer.");
				 proceedCalculation = false;
			}
			//Input Validate Int Rate
			try {
				 double intRate = Double.parseDouble(intRateTF.getText());
				 if (intRate<0) {
					 creditComment1TF.setText("Interest rate must be greater than or equal to 0.");
					 proceedCalculation = false;
				 }	
			}
			catch(Exception e) {
				creditComment1TF.setText("There is a problem with interest rate. Please make sure this is a positive number greater than or equal to 0.");
				 proceedCalculation = false;
			}
			//Input Validate No Months
			try {
				 int noMonths = Integer.parseInt(noMonthsTF.getText());
				 if (noMonths<1) {
					 creditComment1TF.setText("Number of months must be greater than 0.");
					 proceedCalculation = false;
				 }
				 else if (noMonths>84) {
					 creditComment1TF.setText("Number of months must be less than or equal to  84.");
					 proceedCalculation = false;
				 }
			}
			catch(Exception e) {
				creditComment1TF.setText("There is a problem with Number of months. Please make sure this is a positive integer greater than 0 and no more than 84.");
				 proceedCalculation = false;
			}
			
			//Car Price must be greater than to trade in payment
			 if (Integer.parseInt(tradeInPaymentTF.getText()) >=  Integer.parseInt(carPriceTF.getText())) {
				 creditComment1TF.setText("Car Price must be greater than trade in payment");
				 proceedCalculation = false;
			 }	
			
			//Main Calculation
			if (proceedCalculation) {			
				this.CarAppObj=new CarApp(
						Integer.parseInt(carPriceTF.getText()),
						Integer.parseInt(tradeInPaymentTF.getText()),
						Double.parseDouble(intRateTF.getText()),
						Integer.parseInt(noMonthsTF.getText())
						
				);
			    moPaymentTF.setText(currencyFormat.format(CarAppObj.getMoPayment()));
			    totalPaidTF.setText(currencyFormat.format(CarAppObj.getTotalPaid()));
			    intPaidTF.setText(currencyFormat.format(CarAppObj.getTotalIntPaid()));
			}	
		});
		
	}	
}
