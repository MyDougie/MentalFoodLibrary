package MFLP.model.dto;

public class ReserveDTO {
	private int reserveNo;
	private String title;
	private String reserver;
	private String reserveDate;
	private int order;
	
	public ReserveDTO(){}

	//도서번호, 도서명, 예약자 ID, 예약일, 예약순위 
	

	public String getTitle() {
		return title;
	}

	public ReserveDTO(int reserveNo, String title, String reserver, String reserveDate, int order) {
		super();
		this.reserveNo = reserveNo;
		this.title = title;
		this.reserver = reserver;
		this.reserveDate = reserveDate;
		this.order = order;
	}
	
	public int getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}

	public String getReserver() {
		return reserver;
	}

	public void setReserver(String reserver) {
		this.reserver = reserver;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
}
