package ticketClient.Purchase;

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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PurchaseController implements Initializable{

	@FXML private Button prevBtn;
	
	@FXML private Button btnA;
	@FXML private Button btnB;
	@FXML private Label message;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prevBtn.setOnAction(event->handleBtnACtion(event));
		
		btnA.setOnAction(event->handleBtnA(event));
		btnB.setOnAction(event->handleBtnB(event));
		
	
	}
	public void handleBtnACtion(ActionEvent event) {
		try {
			Parent prev = FXMLLoader.load(getClass().getResource("..\\Main\\Main.fxml"));
			Scene scene = new Scene(prev);
			Stage primaryStage = (Stage) prevBtn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void handleBtnA(ActionEvent event){
		System.out.println("A메뉴구매");
		message.setText("         A 메뉴를 구입하셨습니다");

		BarcodeCreator newbc = new BarcodeCreator(99);
		
		
	}
	
	public void handleBtnB(ActionEvent event){
		System.out.println("B메뉴구매"); 
		message.setText("         B 메뉴를 구입하셨습니다");
		
		BarcodeCreator newbc = new BarcodeCreator(77);
		
		
	}
	
		
}

