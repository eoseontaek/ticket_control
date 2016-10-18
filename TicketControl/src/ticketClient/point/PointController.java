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
	
	//@FXML private Button btnOk1;
	@FXML private Button btnCancel;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prevBtn.setOnAction(event->handleprevBtnAction(event));				//�ڷΰ���
		chargeBtn.setOnAction(event->handlechargeBtnAction(event));			//����Ʈ �����ϱ�(Ȱ��ȭ)
		chargePoint.setOnAction(event->handleChkAction(event));				//�����ϱ�
		
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
		dialog.setTitle("Ȯ��");

		if(chargeTf.getText().isEmpty()){
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("ChkDialog.fxml"));
				Label txtTitle = (Label) parent.lookup("#txtTitle");
				txtTitle.setText("�ݾ��� �Է��ϼ���.");
				Button btnOk = (Button) parent.lookup("#btnOk");
				btnOk.setOnAction(event1->dialog.close());	
				Scene scene = new Scene(parent);

				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("..\\point\\YesNoDialog.fxml"));
				Label txtTitle = (Label) parent.lookup("#txtTitle");
				txtTitle.setText("�����Ͻðڽ��ϱ�?");
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
		System.out.println("fffffff");
//		ClientDAO dao = new ClientDAO();
//		
////		String ClientPoint = String.valueOf(point);
////		currentPoint.setText(ClientPoint);
//		
//		String UpdatePoint = chargeTf.getText();
//		int point = Integer.parseInt(UpdatePoint);
//		
//		ClientVO data = new ClientVO(point);
//		
//		System.out.println(dao.ClientUpdate(data));
		
	}
}
