package application;

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
		    moPaymentTF.setText("TEST");
		    totalPaidTF.setText("TEST2");
		    intPaidTF.setText("TEST3");
		    
		    creditComment1TF.setText("Can you pay in Apples & Milk?");
		});
		
		
		
	}	
}
