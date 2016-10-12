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
	
	@FXML private Button btnBuyCheck;
	@FXML private Button btnBuy;
	@FXML private Button btnMenu;
	@FXML private Button btnInfo;
	@FXML private ImageView BarcodeImage;
	
	
	public void barcodeCheck(){
		//데이터베이스에서 회원아이디 뽑아오기 -> 바코드 있나 확인 -> 바코드번호가져오기
		String barcodeNum = "101610111001"+".jpg"; //바코드 넘버 불러와서 변수에 저장
		
//		if(barcodeNum!=null){
			File file = new File("d:/barcode/"+barcodeNum);
			Image image = new Image(file.toURI().toString());
			BarcodeImage.setImage(image);
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
		btnBuyCheck.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handlebtnBuyCheckAction(event);
			}
		});
		
		btnBuy.setOnAction(event->handlebtnBuyAction(event));
		btnMenu.setOnAction(event->handlebtnMenuAction(event));
		btnInfo.setOnAction(event->handlebtnInfoAction(event));	
	}
	
	
	public void handlebtnBuyCheckAction(ActionEvent event){
		System.out.println("구입확인!!!!!!!");
		barcodeCheck();
	}	
		
	
	public void handlebtnBuyAction(ActionEvent event){
		System.out.println("구입구입구입");
		BarcodeImage.setImage(null);
	}
	
	public void handlebtnMenuAction(ActionEvent event){
		System.out.println("메뉴확인!!!!!!!");
		BarcodeImage.setImage(null);
	}
	
	public void handlebtnInfoAction(ActionEvent event){
		System.out.println("마이페이지!!!!!!");
		BarcodeImage.setImage(null);
	}

}
