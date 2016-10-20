package ticketServer;

import java.awt.Button;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ticketServer.dao.Barcode;
import ticketServer.dao.BarcodeDAO;

public class BarcodeScan extends Thread{
	public void run(){
		while(true){
			InputStreamReader br = new InputStreamReader(System.in);
			BufferedReader field = new BufferedReader(br);
			JFrame frame = new JFrame("식권 확인");
			
			String scanned;
			try {
				scanned = field.readLine();
				if(scanned.length()==13){
					String scannedBarcode = scanned.substring(0, 12);
					BarcodeDAO dao = new BarcodeDAO();
					
					if(dao.getUseOX(scannedBarcode)==0){
						JOptionPane.showMessageDialog(frame, "감사합니다.");
						System.exit(0);
					}
					if(dao.getUseOX(scannedBarcode)==1){
						JOptionPane.showMessageDialog(frame, "이미 사용 된 식권입니다.");
						System.exit(0);
					}
				}
				
			} catch (IOException e) {
			}
		
		}		
	}
}
