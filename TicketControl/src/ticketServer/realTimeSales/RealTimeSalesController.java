package ticketServer.realTimeSales;

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

public class RealTimeSalesController implements Initializable{
	@FXML private Button btnHome;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnHome.setOnAction(event->handleBtnHomeAction(event));
		
	}
	
	
	public void handleBtnHomeAction(ActionEvent event){
	
		try {
			Parent homeRoot = FXMLLoader.load(getClass().getResource("..\\administratorMain\\AdministratorMain.fxml"));
			Scene scene = new Scene(homeRoot);
			Stage primaryStage = (Stage) btnHome.getScene().getWindow();
			primaryStage.setScene(scene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
