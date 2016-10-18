package ticketClient.menuresult;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ticketClient.payment.BarcodeCreator;
import ticketClient.purchase.PurchaseController;

public class MenuResultController implements Initializable{

	@FXML private Button prevBtn;
	@FXML private Button btnA;
	@FXML private Button btnB;
	
	@FXML private TextField dateTF;
	
	public static String menuSelect;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prevBtn.setOnAction(event->handleBtnACtion(event));

		btnA.setOnAction(event->handleBtnA(event));
		btnB.setOnAction(event->handleBtnB(event));
		
		dateTF.setText(PurchaseController.displayDate);
		
	}

	public void handleBtnACtion(ActionEvent event) {
		try {
			Parent prev = FXMLLoader.load(getClass().getResource("..\\purchase\\TicketPurchase.fxml"));
			Scene scene = new Scene(prev);
			Stage primaryStage = (Stage) prevBtn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		

	public void handleBtnA(ActionEvent event){
		try {
			Parent btna = FXMLLoader.load(getClass().getResource("..\\payment\\Payment.fxml"));
			Scene scene = new Scene(btna);
			Stage primaryStage = (Stage) btnA.getScene().getWindow();
			primaryStage.setScene(scene);
			menuSelect = "99";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleBtnB(ActionEvent event){
		try {
			Parent btnb = FXMLLoader.load(getClass().getResource("..\\payment\\Payment.fxml"));
			Scene scene = new Scene(btnb);
			Stage primaryStage = (Stage) btnB.getScene().getWindow();
			primaryStage.setScene(scene);
			menuSelect = "77";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
