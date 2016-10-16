package ticketClient.payment;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PaymentController implements Initializable{

	@FXML private Button prevBtn;
	@FXML private Button payBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prevBtn.setOnAction(event->handleBtnAction(event));
		payBtn.setOnAction(event->handlePayBtnAction(event));
	}

	public void handleBtnAction(ActionEvent event) {
		try {
			Parent prev = FXMLLoader.load(getClass().getResource("..\\menuresult\\MenuResult.fxml"));
			Scene scene = new Scene(prev);
			Stage primaryStage = (Stage) prevBtn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void handlePayBtnAction(ActionEvent event) {

		//일반식, 특식 구분
		System.out.println("B메뉴구매"); 
		BarcodeCreator newbc = new BarcodeCreator(77);
		//		BarcodeCreator newbc = new BarcodeCreator(99);
	}
}
