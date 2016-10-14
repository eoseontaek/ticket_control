package barcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.krysalis.barcode4j.BarcodeClassResolver;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.MimeTypes;
import org.krysalis.barcode4j.tools.UnitConv;


public class BarcodeCreate {

  public static void main(String[] args) throws IOException {
	  init();	  
  }
 
  
  private static void init(){
	  
	  String sellCount="1001";
			  
	  //////////////////////////////오늘날짜
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	  Calendar c1 = Calendar.getInstance();
	  String strToday = sdf.format(c1.getTime());
	  String today = strToday.substring(2, 8);
	  
	  //////////////////////////////일반식=1, 특식=2
	  String menuA = "10";
	  String menuB = "20";
	  
	  
	  ////////////////////////////////////이벤트처리로 A인지 B인지 선택하고 저장
	  String selectMenu;
	  /////////////////////////////////////////////////////////////
	  
	  String barcodeType = "ean-13";
	  String barcodeDate = menuA+today+sellCount;
	 	  
	  final int dpi = 203;
	  String fileFormat = "jpg";
	  
	  String dir = "d:/barcode/";
//	  String fileName = barcodeDate+fileFormat;
	  String outputFile = dir + barcodeDate+"."+fileFormat;
	  
	  boolean isAntiAliasing = false;
	  
	  createBarcode(barcodeType, barcodeDate, fileFormat, isAntiAliasing, dpi, outputFile);
	
  }
  
  
  private static void createBarcode(String barcodeType, String barcodeDate, String fileFormat, boolean isAntiAliansing, int dpi, String outputFile){
	  try{
		  AbstractBarcodeBean bean = null;
		  
		  BarcodeClassResolver resolver = new DefaultBarcodeClassResolver();
		  Class clazz = resolver.resolveBean(barcodeType);
		  bean = (AbstractBarcodeBean)clazz.newInstance();
		  bean.doQuietZone(true);
		  
		  OutputStream out = new FileOutputStream(new File(outputFile));
		  try{
			  String mimeType = MimeTypes.expandFormat(fileFormat);
			  int imageType = BufferedImage.TYPE_BYTE_BINARY;
			  BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, mimeType, dpi, imageType, isAntiAliansing, 0);
			  
			  bean.generateBarcode(canvas, barcodeDate);
			  
			  canvas.finish();
			  
			  System.out.println("creat image success.");
			  
		  } finally{
			  out.close();
		  }
	  } catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
}