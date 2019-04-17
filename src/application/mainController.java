/*
 * Sources:
 * 	JavaFX ChoiceBox: https://o7planning.org/en/11087/javafx-choicebox-tutorial
 */

package application;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
	@FXML
	private Text carPriceCommentTF;
	@FXML
	private Text tradeInCommentTF;
	@FXML
	private Text interestRateCommentTF;
	@FXML
	private Text numMonthsCommentTF;
	

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

	//Others
	boolean proceedCalculation;
	NumberFormat currencyFormat;
	URI CarValueURI;

	//Main CarApp Object
	private CarApp CarAppObj;

	@FXML
	public void initialize() {
		//Set Instance Variables
		proceedCalculation = true;
		//Currency Formatter
		currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); 
		try {
			CarValueURI = new URI("https://www.nerdwallet.com/l/manage-car-expenses-save");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		//Run First Time
		runCarApp();

		//Choicebox Values
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

		//Associate Credit Score Choicebox to Listener
		creditScoreCB.getSelectionModel().selectedItemProperty().addListener(changeListener);

		//Car Price
		carPriceTF.textProperty().addListener((observable, oldValue, newValue) -> {
			carPriceCommentTF.setText("");
			tradeInCommentTF.setText("");
			try {
				int carPrice = Integer.parseInt(carPriceTF.getText());
				if (carPrice<0) {
					carPriceCommentTF.setText("Car price must be positive.");
					proceedCalculation = false;
				}
				else if (Integer.parseInt(tradeInPaymentTF.getText()) >=  Integer.parseInt(carPriceTF.getText())) {
					carPriceCommentTF.setText("Car Price must be greater than trade in payment");
					proceedCalculation = false;
				}	
				else {
					proceedCalculation = true;
				}					
			}
			catch(Exception e) {
				carPriceCommentTF.setText("Make sure this is a valid number.");
				proceedCalculation = false;
			}
			runCarApp();
		});

		//TradeInPayment
		tradeInPaymentTF.textProperty().addListener((observable, oldValue, newValue) -> {
			tradeInCommentTF.setText("");
			carPriceCommentTF.setText("");
			try {
				int tradeInPayment = Integer.parseInt(tradeInPaymentTF.getText());
				if (tradeInPayment<0) {
					tradeInCommentTF.setText("Trade-in payment must be positive.");
					proceedCalculation = false;
				}
				else if (Integer.parseInt(tradeInPaymentTF.getText()) >=  Integer.parseInt(carPriceTF.getText())) {
					tradeInCommentTF.setText("Car Price must be greater than trade in payment");
					proceedCalculation = false;
				}	
				else {
					proceedCalculation = true;
				}
			}
			catch(Exception e) {
				tradeInCommentTF.setText("Make sure this is a positive integer.");
				proceedCalculation = false;
			}
			runCarApp();
		});

		//Int Rate
		intRateTF.textProperty().addListener((observable, oldValue, newValue) -> {
			interestRateCommentTF.setText("");
			try {
				double intRate = Double.parseDouble(intRateTF.getText());
				if (intRate<0) {
					interestRateCommentTF.setText("Interest rate must be >= 0.");
					proceedCalculation = false;
				}
				else {
					proceedCalculation = true;
				}
			}
			catch(Exception e) {
				interestRateCommentTF.setText("Interest rate must be >= 0.");
				proceedCalculation = false;
			}
			runCarApp();
		});

		//Number of Months
		noMonthsTF.textProperty().addListener((observable, oldValue, newValue) -> {
			numMonthsCommentTF.setText("");
			try {
				int noMonths = Integer.parseInt(noMonthsTF.getText());
				if (noMonths<1) {
					numMonthsCommentTF.setText("Number of months must be > 0.");
					proceedCalculation = false;
				}
				else if (noMonths>84) {
					numMonthsCommentTF.setText("Number of months must be <= 84.");
					proceedCalculation = false;
				}
				else {
					proceedCalculation = true;
				}
			}
			catch(Exception e) {
				numMonthsCommentTF.setText("Make sure this is between 0 and 84.");
				proceedCalculation = false;
			}
			runCarApp();
		});
		
		carValueButton.setOnAction((event) -> {
			try {
				java.awt.Desktop.getDesktop().browse(CarValueURI);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}	

	//Actually Runs Car App
	public void runCarApp() {
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
	}
}
