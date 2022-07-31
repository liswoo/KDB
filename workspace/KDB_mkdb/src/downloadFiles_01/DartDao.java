package downloadFiles_01;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;




public class DartDao {
	
	public void downloadfiles(String rcept_no) {
			//gmail
			String spec = "https://opendart.fss.or.kr/api/document.xml?"
					+ "crtfc_key=825e41d4f19edb65d48bad6ab83fffa58af8ba18"
					+ "&rcept_no="+rcept_no;
			//naver
//			String spec = "https://opendart.fss.or.kr/api/document.xml?"
//					+ "crtfc_key=f0d12585b6fc28be612f8f84acaa34bbd67a040b"
//					+ "&rcept_no="+rcept_no;
			
			//hanmail
//			String spec = "https://opendart.fss.or.kr/api/document.xml?"
//					+ "crtfc_key=f94853bed1dfbbfbf26c60e227319bfd4aa63db6"
//					+ "&rcept_no="+rcept_no;
			
			
			
	        String outputDir = "C:\\Users\\woosd\\Desktop\\DartFiles";
	        InputStream is = null;
	        FileOutputStream os = null;
	        try{
	        	
	            URL url = new URL(spec);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            int responseCode = conn.getResponseCode();
//	            System.out.println(spec);
//	            System.out.println("responseCode " + responseCode);

	            // Status 가 200 일 때
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                String fileName = "";
	                String disposition = conn.getHeaderField("Content-Disposition");
	                String contentType = conn.getContentType();
	                
	                
	                if (disposition != null) {
	                    String target = "filename=";
	                    int index = disposition.indexOf(target);
	                    if (index != -1) {
	                        fileName = "2"+disposition.substring(index + target.length() + 1);
	                    }
	                } else {
	                    fileName = spec.substring(spec.lastIndexOf("/") + 1);
	                }

//	                System.out.println("Content-Type = " + contentType);
//	                System.out.println("Content-Disposition = " + disposition);
//	                System.out.println("fileName = " + fileName);

	                is = conn.getInputStream();
	                os = new FileOutputStream(new File(outputDir, fileName));

	                final int BUFFER_SIZE = 4096;
	                int bytesRead;
	                byte[] buffer = new byte[BUFFER_SIZE];
	                while ((bytesRead = is.read(buffer)) != -1) {
	                    os.write(buffer, 0, bytesRead);
	                }
	                os.close();
	                is.close();
//	                System.out.println("File downloaded");
	            } else {
	                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
	            }
	            conn.disconnect();
	        } catch (Exception e){
	            System.out.println(rcept_no);
	            
	            try {
	                if (is != null){
	                    is.close();
	                }
	                if (os != null){
	                    os.close();
	                }
	            } catch (IOException e1){
	            	System.out.println("IOException");
	                e1.printStackTrace();
	            }
	        }
		
	}

}