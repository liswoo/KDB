package getRcept_no;

public class DartVo {
	
	//result
	int page_no;
	String page_count;
	String total_count;
	String total_page;
	String bgn_de;
	String end_de;  
	
	//list
	String corp_cls;
	String corp_name;
	String corp_code;
	String stock_code;
	String report_nm;
	String rcept_no;
	String flr_nm ;
	String rcept_dt;
	String rm ;
	
	
	public DartVo(String corp_cls, String corp_name, String corp_code, String stock_code, String report_nm,
			String rcept_no, String flr_nm, String rcept_dt, String rm) {
		super();
		this.corp_cls = corp_cls;
		this.corp_name = corp_name;
		this.corp_code = corp_code;
		this.stock_code = stock_code;
		this.report_nm = report_nm;
		this.rcept_no = rcept_no;
		this.flr_nm = flr_nm;
		this.rcept_dt = rcept_dt;
		this.rm = rm;
	}
	

	
	public int getPage_no() {
		return page_no;
	}
	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}
	public String getPage_count() {
		return page_count;
	}
	public void setPage_count(String page_count) {
		this.page_count = page_count;
	}
	public String getTotal_count() {
		return total_count;
	}
	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}
	public String getTotal_page() {
		return total_page;
	}
	public void setTotal_page(String total_page) {
		this.total_page = total_page;
	}
	public String getCorp_cls() {
		return corp_cls;
	}
	public void setCorp_cls(String corp_cls) {
		this.corp_cls = corp_cls;
	}
	public String getCorp_name() {
		return corp_name;
	}
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	public String getCorp_code() {
		return corp_code;
	}
	public void setCorp_code(String corp_code) {
		this.corp_code = corp_code;
	}
	public String getStock_code() {
		return stock_code;
	}
	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}
	public String getReport_nm() {
		return report_nm;
	}
	public void setReport_nm(String report_nm) {
		this.report_nm = report_nm;
	}
	public String getRcept_no() {
		return rcept_no;
	}
	public void setRcept_no(String rcept_no) {
		this.rcept_no = rcept_no;
	}
	public String getFlr_nm() {
		return flr_nm;
	}
	public void setFlr_nm(String flr_nm) {
		this.flr_nm = flr_nm;
	}
	public String getRcept_dt() {
		return rcept_dt;
	}
	public void setRcept_dt(String rcept_dt) {
		this.rcept_dt = rcept_dt;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	
	
}
