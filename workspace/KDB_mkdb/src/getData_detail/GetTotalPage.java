package getData_detail;

public class GetTotalPage {
	
	public int totalPage(String start, String end,String detail) {
		DartJson dj = new DartJson();
		dj.getDartVo(start, end, 1, "100",detail);
		int totalpage = dj.total_page;
		return(totalpage);
	}
}
