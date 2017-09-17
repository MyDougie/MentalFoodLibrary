package MFLP.model.dto;

public class populerBookDTO {


	private int count; 
	private int bookNo;
	private String title;
	private String publisher;
	private String writer;
	/**
	 * @param count
	 * @param bookNo
	 * @param title
	 * @param publisher
	 */
	
	public populerBookDTO(){}
	
	public populerBookDTO(int count, int bookNo, String title, String publisher, String writer) {
		super();
		this.count = count;
		this.bookNo = bookNo;
		this.title = title;
		this.publisher = publisher;
		this.writer = writer;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
