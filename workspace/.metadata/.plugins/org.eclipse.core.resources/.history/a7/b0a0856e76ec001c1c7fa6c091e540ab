package another_class;


import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.omg.CORBA.portable.InputStream;

public class Test2 {

	public static void main(String[] args) {
		String address = "https://opendart.fss.or.kr/api/document.xml?"
				+ "crtfc_key=825e41d4f19edb65d48bad6ab83fffa58af8ba18"
				+ "&rcept_no=20200102000192";
		System.out.println(address);
		
		try {
			InputStream is = null;
			URL url = new URL(address);
			FileOutputStream fos = new FileOutputStream("C:\\\\Users\\\\woosd\\\\Desktop\\\\testfile");
			
			URLConnection urlConnection = url.openConnection();
			is = urlConnection.getInputStream();
			byte[] buffer = new byte[1024];
			int readBytes;
			
			while((readBytes= is.read(buffer)) != -1) {
				fos.write(buffer,0,readBytes);
			}
			fos.close();
			System.out.println("파일 다운로드 완료");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
