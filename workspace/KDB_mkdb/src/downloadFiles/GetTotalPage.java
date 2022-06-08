package downloadFiles;

public class GetTotalPage {
	
	public int totalPage(String start, String end) {
		DartJson dj = new DartJson();
		dj.getDartVo(start, end, 1, "100");
		int totalpage = dj.total_page;
		return(totalpage);
	}
}
