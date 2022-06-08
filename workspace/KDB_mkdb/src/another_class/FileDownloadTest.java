package another_class;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloadTest {

    public static void main(String[] args) {
        String spec = "https://opendart.fss.or.kr/api/document.xml?"
				+ "crtfc_key=825e41d4f19edb65d48bad6ab83fffa58af8ba18"
				+ "&rcept_no=20200102000192";
        String outputDir = "C:\\Users\\woosd\\Desktop\\testfile";
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
                
                // 일반적으로 Content-Disposition 헤더에 있지만 
                // 없을 경우 url 에서 추출해 내면 된다.
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
            e.printStackTrace();
            try {
                if (is != null){
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }
}