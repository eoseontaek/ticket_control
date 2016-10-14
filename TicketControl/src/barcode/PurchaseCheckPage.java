package barcode;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class PurchaseCheckPage extends Application implements Initializable{
	
	@FXML private Button btnGoMain;
	@FXML private ImageView BarcodeImage;
	
	
	public String barcodeCheck(){
		
		//////////////////////////////////////////////////////
		// �����ͺ��̽����� ���ڵ� �̸� �������� 
		///////////////////////////////////////////////////////
		
		String barcodeNum = "101610111001"+".jpg"; //
		return barcodeNum;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("purchaseCheck.fxml"));;
			Scene scene = new Scene(root,300,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//�̹��� �ε�		
		File file = new File("./"+barcodeCheck());
		Image image = new Image(file.toURI().toString());
		//�̹����� ������ �̹����ֱ�
		BarcodeImage.setImage(image);

		
		btnGoMain.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handlebtnGoMainAction(event);
			}
		});
		
	}
	
	
	public void handlebtnGoMainAction(ActionEvent event){
		System.out.println("�ڷΰ���"); 
		///////////////////////////////////////////////////////////
		
		
		////////////////////�������� ���ư��� ����/////////////////////////
		
		
		///////////////////////////////////////////////////////////
	}	
		
}
