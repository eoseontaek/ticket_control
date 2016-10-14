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
import javafx.stage.Stage;

public class LogInContoller implements Initializable{
	@FXML private Button btnLogIn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btnLogIn.setOnAction((event)->handleBtnLogInAction(event));
		
	}
	
	public void handleBtnLogInAction(ActionEvent event){
		
		////// 로그인 처리 //////////////////////////////////////////
		
		
		
		///////////////////////////////////////////////////////////		
		
		try {
			Parent administrator = FXMLLoader.load(getClass().getResource("..\\administratorMain\\AdministratorMain.fxml"));
			Scene scene = new Scene(administrator);
			Stage primaryStage = (Stage) btnLogIn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
