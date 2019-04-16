package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("mainTheme.fxml"));
			Scene scene = new Scene(root,800,745);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Car Payment Calculator");
			primaryStage.setScene(scene);
		    primaryStage.setWidth(800);
		    primaryStage.setHeight(745);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		CarApp car = new CarApp();
		car.setCarPrice(20000.00);
		car.setDownPayment(4000.00);
		car.setInterestRate(.045);
		car.setNumMonth(36);
		car.MonthlyPayment();
		System.out.println(car.getMonthlyPayment());
		car.TotalPayment();
		car.TotalInterest();
		System.out.println(car.getTotalPay());
		System.out.println(car.getTotalInt());
	}
}
