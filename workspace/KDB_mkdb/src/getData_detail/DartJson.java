package getData_detail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class DartJson {

	int total_page;
	
final static String crtfc_key = "825e41d4f19edb65d48bad6ab83fffa58af8ba18";
	
	

	public ArrayList<DartVo> getDartVo(String bgn_de, String end_de,int page_no, String page_count, String pblntf_detail_ty) {
		
       String urlStr = "https://opendart.fss.or.kr/api/list.json?"
        		+ "crtfc_key=" + crtfc_key + "&page_no=" + page_no + "&page_count=" + page_count
        		+ "&bgn_de="+ bgn_de + "&end_de=" + end_de + "&pblntf_detail_ty="+ pblntf_detail_ty;
   
       System.out.println(urlStr);
     
     
     ArrayList<DartVo> pList = new ArrayList<DartVo>();
     
     try {
     	URL url = new URL(urlStr); 
         BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
         String line = "";
         String result="";
         //버퍼에 있는 정보를 문자열로 변환.
         while((line=bf.readLine())!=null){ //bf 에 있는값을 읽어와서 하나의 문자열로 만듭니다.
             result=result.concat(line);
         }
         
         //문자열을 JSON으로 파싱.
         JSONParser jsonParser = new JSONParser();
         JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
         
         //total page count
         int total_page = Integer.parseInt(String.valueOf(jsonObj.get("total_page")));
         this.total_page = total_page;
         
         JSONArray parse_list = (JSONArray) jsonObj.get("list");
         JSONObject obj;
 		
     		
 	
     		for(int i = 0 ; i < parse_list.size(); i++)
     		{
     			obj = (JSONObject) parse_list.get(i);
     			
     			String corp_code = (String)obj.get("corp_code");
     			String corp_name = (String)obj.get("corp_name");
     			String stock_code = (String)obj.get("stock_code");
     			String corp_cls = (String)obj.get("corp_cls");
     			String report_nm = (String)obj.get("report_nm");
     			String rcept_no = (String)obj.get("rcept_no");
     			String flr_nm = (String)obj.get("flr_nm");
     			String rcept_dt = (String)obj.get("rcept_dt");
     			String rm = (String)obj.get("rm");
     			
     			pList.add(new DartVo(corp_code,corp_name,stock_code,corp_cls,report_nm,rcept_no,flr_nm,rcept_dt,rm,pblntf_detail_ty));
     			
     		}bf.close();
     		 
     }catch(Exception e){
         System.out.println(e.getMessage());
}
     return(pList);
}
}