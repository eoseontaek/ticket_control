package ticketClient.purchase;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class PurchaseController implements Initializable{

	@FXML private Button prevBtn;
	@FXML private Button menuChk;
	@FXML private DatePicker datePicker;
	
	public static String selectedDate;
	public static String displayDate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prevBtn.setOnAction(event->handleprevBtnAction(event));
		menuChk.setOnAction(event->handlemenuChkAction(event));

		
		LocalDate nowDate = LocalDate.now();
		datePicker.setValue(nowDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		selectedDate = nowDate.format(formatter); /////////////////////////////////오늘날짜로 저장
		
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy.MM.dd (EEE)");
		displayDate = nowDate.format(formatter2);
		
		datePicker.setOnAction(event ->{
			LocalDate date = datePicker.getValue();
			selectedDate = date.format(formatter); ////////////////////////////////선택되는 날짜로 변경
			displayDate = date.format(formatter2);
		});
		
	}
	public void handlemenuChkAction(ActionEvent event) {
		try {
			Parent chk = FXMLLoader.load(getClass().getResource("..\\menuresult\\MenuResult.fxml"));
			Scene scene = new Scene(chk);
			Stage primaryStage = (Stage) menuChk.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
