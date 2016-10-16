package ticketClient.payment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.krysalis.barcode4j.BarcodeClassResolver;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.MimeTypes;

public class BarcodeCreator {
	
	public BarcodeCreator(int menu){
	
	////////////// menuA : 99번으로 시작
	////////////// menuB : 77번으로 시작
		
	String sellCount = "1001"; //////////////////팔린개수 카운트
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Calendar c1 = Calendar.getInstance();
	String strToday = sdf.format(c1.getTime());
	String today = strToday.substring(2, 8);	

	  String barcodeDate = menu+today+sellCount; //바코트넘버
	
	  try{
		  AbstractBarcodeBean bean = null;
		  
		  BarcodeClassResolver resolver = new DefaultBarcodeClassResolver();
		  Class clazz = resolver.resolveBean("ean-13");
		  bean = (AbstractBarcodeBean)clazz.newInstance();
		  bean.doQuietZone(true);
			  
		  String dir = "c:/barcode/"; ////////////////////////////////////////////////////////경로설정
		  OutputStream out = new FileOutputStream(new File(dir + barcodeDate+"."+"jpg"));
		  try{
			  String mimeType = MimeTypes.expandFormat("jpg");
			  int imageType = BufferedImage.TYPE_BYTE_BINARY;
			  BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, mimeType, 203, imageType, false, 0);
			  
			  bean.generateBarcode(canvas, barcodeDate);
			  
			  canvas.finish();
			  
			  System.out.println("create image success.");
			  
		  } finally{
			  out.close();
		  }
	  } catch(Exception e){
		  e.printStackTrace();
	  }
	  		  
	
	}

}