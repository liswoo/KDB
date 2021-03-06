package downloadFiles;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class DartDao {
	
	// DartVo객체를 입력받으면 객체안의 속성에 초기화된 데이터들을 데이터베이스에 인설트하는 메소드입니다.
	public void insertdb(ArrayList<DartVo> db) {
		
		DartVo dvv = null;
		String rcept_no = null;
		
		
		for(int i =0; i < db.size(); i++) {
			dvv=(DartVo)db.get(i); //다운캐스팅. 어레이 리스트에 저장된 프로덕트 객체를 하나하나 가져와서
			rcept_no=dvv.getRcept_no();
			
			
			String spec = "https://opendart.fss.or.kr/api/document.xml?"
					+ "crtfc_key=825e41d4f19edb65d48bad6ab83fffa58af8ba18"
					+ "&rcept_no="+rcept_no;
			
	        String outputDir = "C:\\Users\\woosd\\Desktop\\DartFiles";
	        InputStream is = null;
	        FileOutputStream os = null;
	        try{
	        	
	            URL url = new URL(spec);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            int responseCode = conn.getResponseCode();

	            System.out.println("responseCode " + responseCode);

	            // Status 가 200 일 때
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                String fileName = "";
	                String disposition = conn.getHeaderField("Content-Disposition");
	                String contentType = conn.getContentType();
	                
	                
	                if (disposition != null) {
	                    String target = "filename=";
	                    int index = disposition.indexOf(target);
	                    if (index != -1) {
	                        fileName = disposition.substring(index + target.length() + 1);
	                    }
	                } else {
	                    fileName = spec.substring(spec.lastIndexOf("/") + 1);
	                }

	                System.out.println("Content-Type = " + contentType);
	                System.out.println("Content-Disposition = " + disposition);
	                System.out.println("fileName = " + fileName);

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
	                System.out.println("File downloaded");
	            } else {
	                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
	            }
	            conn.disconnect();
	        } catch (Exception e){
	            System.out.println("An error occurred while trying to download a file.");

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

}