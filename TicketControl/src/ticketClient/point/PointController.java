package ticketClient.point;

import java.io.IOException;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ticketClient.DAO.ClientDAO;
import ticketClient.DAO.ClientVO;

public class PointController implements Initializable{

	@FXML private Button prevBtn;
	@FXML private Button chargeBtn;
	@FXML private Button chargePoint;
	@FXML private TextField chargeTf;
	@FXML private Label currentPoint;
	
	@FXML private Button btnOk;
	@FXML private Button btnCancel;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prevBtn.setOnAction(event->handleprevBtnAction(event));
		chargeBtn.setOnAction(event->handlechargeBtnAction(event));
		chargePoint.setOnAction(event->handleChkAction(event));
		
		ClientDAO dao = new ClientDAO();
		dao.DBConnection();
		
		int point = dao.ClientSelect();
		String ClientPoint = String.valueOf(point);
		
		currentPoint.setText(ClientPoint);
		
	}
	
	private Stage primaryStage;	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
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
	
	public void handleChkAction(ActionEvent event) {
		
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("확인");
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("custom_dialog.fxml"));
			Label txtTitle = (Label) parent.lookup("#txtTitle");
			txtTitle.setText("충전하시겠습니까?");
			//Button btnOk = (Button) parent.lookup("#btnOk");
			Button btnCancel = (Button) parent.lookup("#btnCancel");
			btnCancel.setOnAction(event1->dialog.close());	
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
