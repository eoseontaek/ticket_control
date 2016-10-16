package ticketClient.purchase;

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

public class PurchaseController implements Initializable{

	@FXML private Button prevBtn;
	@FXML private Button menuChk;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prevBtn.setOnAction(event->handleprevBtnAction(event));
		menuChk.setOnAction(event->handlemenuChkAction(event));

	}
	public void handlemenuChkAction(ActionEvent event) {
		try {
			Parent chk = FXMLLoader.load(getClass().getResource("..\\menuresult\\MenuResult.fxml"));
			Scene scene = new Scene(chk);
			Stage primaryStage = (Stage) menuChk.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleprevBtnAction(ActionEvent event) {
		try {
			Parent prev = FXMLLoader.load(getClass().getResource("..\\Main\\Main.fxml"));
			Scene scene = new Scene(prev);
			Stage primaryStage = (Stage) prevBtn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
