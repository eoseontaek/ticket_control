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
	
	public BarcodeCreator(String menu, String date, String count){
	
	////////////// menuA : 99������ ����
	////////////// menuB : 77������ ����
		
	String sellCount = "1001"; //////////////////�ȸ����� ī��Ʈ
	

	  String barcodeDate = menu+date+sellCount; //���ڵ�ѹ�
	
	  try{
		  AbstractBarcodeBean bean = null;
		  
		  BarcodeClassResolver resolver = new DefaultBarcodeClassResolver();
		  Class clazz = resolver.resolveBean("ean-13");
		  bean = (AbstractBarcodeBean)clazz.newInstance();
		  bean.doQuietZone(true);
			  
		  String dir = "c:/barcode/"; ////////////////////////////////////////////////////////��μ���
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