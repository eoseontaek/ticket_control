package ticketServer.logIn;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LogInContoller implements Initializable{
	@FXML private Button btnLogIn;
	@FXML private TextField txtFieldID;
	@FXML private PasswordField pwFieldPW;
	
	Stage dialog;
	
	private Stage primaryStage;	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btnLogIn.setOnAction((event)->handleBtnLogInAction(event));
		
	}
	
	public void handleBtnLogInAction(ActionEvent event){
		
		////// 로그인 처리 //////////////////////////////////////////
		System.out.println("id :" + txtFieldID.getText());
		System.out.println("pw :" + pwFieldPW.getText());
		
		dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("확인");
		
		///////////////////////////////////////////////////////////		
		if (txtFieldID.getText().equals("admin") && pwFieldPW.getText().equals("admin")) { // id, pw 일치하면...
			try {
				Parent administrator = FXMLLoader
						.load(getClass().getResource("..\\administratorMain\\AdministratorMain.fxml"));
				Scene scene = new Scene(administrator);
				Stage primaryStage = (Stage) btnLogIn.getScene().getWindow();
				
				primaryStage.setScene(scene);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // id, pw 일치하지 않으면...
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("..\\..\\ticketClient\\point\\ChkDialog.fxml"));
				Label txtTitle = (Label) parent.lookup("#txtTitle");
				txtTitle.setText("아이디 또는 비밀번호를 다시 확인하세요.");
				Button btnOk = (Button) parent.lookup("#btnOk");
				btnOk.setOnAction(event1->dialog.close());	
				Scene scene = new Scene(parent);

				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
