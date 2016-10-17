package ticketClient.point;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ticketClient.DAO.ClientDAO;
import ticketClient.DAO.ClientVO;

public class PointController implements Initializable{

	@FXML private Button prevBtn;
	@FXML private Button chargeBtn;
	@FXML private Button chargePoint;
	@FXML private TextField chargeTf;
	@FXML private Label currentPoint;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prevBtn.setOnAction(event->handleprevBtnAction(event));
		chargeBtn.setOnAction(event->handlechargeBtnAction(event));

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

	public void handlechargeBtnAction(ActionEvent event) {
		chargePoint.setDisable(false);
		chargeTf.setDisable(false);
	}
}
