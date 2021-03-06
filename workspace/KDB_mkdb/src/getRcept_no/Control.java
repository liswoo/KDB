package getRcept_no;

import java.util.ArrayList;

public class Control {

	public static void main(String[] args) {
		//실행방법 :
		//1.DartDao 클래스에서 DB설정을 합니다 - static 쪽 변수 입력.
		//2.Control 클래스에서 start_day에 시작일, end_day 종료일을 넣고 실행합니다.
		/*
		3개월 단위의 기간                                건수
		2020.01.01 ~ 2020.03.31	70103
		2020.04.01 ~ 2020.06.30	67880
		2020.07.01 ~ 2020.09.30	40865
		2020.10.01 ~ 2020.12.31	43817
		2021.01.01 ~ 2021.03.31	68913
		2021.04.01 ~ 2021.06.30	70321
		2021.07.01 ~ 2021.09.30	40272
		2021.10.01 ~ 2021.12.31	44925
		2022.01.01 ~ 2022.03.31	68913
		2022.04.01 ~ 2022.06.30	70321
		*/

		String start_day ="20201001";
		String end_day ="20200131";
		
		GetTotalPage gt = new GetTotalPage();
		int totalPageNum = gt.totalPage(start_day, end_day);
		
		
		for(int i = 1;  i <=totalPageNum; i++) {
			
			
			String bgn_de=start_day; // 시작일
	    	String end_de=end_day; // 종료일
	    	int page_no =i; // 페이지 번호
	    	String page_count ="100"; // 페이지 별 건수
		    
		    // 데이터를 얻어오는 객체를 생성
	    	DartJson dartjson = new DartJson();
			
	    	
	    	// 데이터를 JSON형태로 받아 Dart_Vo에 저장
			 ArrayList<DartVo> dartvo = dartjson.getDartVo(bgn_de, end_de, page_no, page_count);
			 
			
			// 데이터베이스에 접속에 관련하는객체를 만들고 데이터베이스에 입력
			DartDao dartdao = new DartDao();
			dartdao.insertdb(dartvo);
			
		}
		
		
	}

}
