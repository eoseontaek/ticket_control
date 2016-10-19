package ticketClient.point;

import java.io.IOException;
import java.net.URL;
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
import ticketClient.TicketClient;
import ticketClient.DAO.ClientDAO;
import ticketServer.packet.PointPacket;

public class PointController implements Initializable{
	public static PointController instance;
	
	@FXML private Button prevBtn;
	@FXML private Button chargeBtn;
	@FXML private Button chargePoint;
	@FXML private TextField chargeTf;
	@FXML private Label currentPoint;
	
	public Label getCurrentPoint(){
		return currentPoint;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		prevBtn.setOnAction(event->handleprevBtnAction(event));				//뒤로가기
		chargeBtn.setOnAction(event->handlechargeBtnAction(event));			//포인트 충전하기(활성화)
		chargePoint.setOnAction(event->handleChkAction(event));				//충전하기
		
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
	Stage dialog;
	public void handleChkAction(ActionEvent event) {

		dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("확인");

		if(chargeTf.getText().isEmpty()){
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("ChkDialog.fxml"));
				Label txtTitle = (Label) parent.lookup("#txtTitle");
				txtTitle.setText("금액을 입력하세요.");
				Button btnOk = (Button) parent.lookup("#btnOk");
				btnOk.setOnAction(event1->dialog.close());	
				Scene scene = new Scene(parent);

				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(!chargeTf.getText().isEmpty()){
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("YesNoDialog.fxml"));
				Label txtTitle = (Label) parent.lookup("#txtTitle");
				txtTitle.setText("충전하시겠습니까?");
				Button btnCancel = (Button) parent.lookup("#btnCancel");
				Button btnOk1 = (Button) parent.lookup("#btnOk1");
				btnCancel.setOnAction(event1->dialog.close());
				btnOk1.setOnAction(event1->handleUpdateAction(event1));
				Scene scene = new Scene(parent);
				
				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleUpdateAction(ActionEvent event) {
		int amount = Integer.parseInt(chargeTf.getText());
		System.out.println("충전 요청 금액 : "+ amount);
		
		TicketClient.instance.send(new PointPacket(amount));

		chargeTf.setText("");
		dialog.close();
	}
}
