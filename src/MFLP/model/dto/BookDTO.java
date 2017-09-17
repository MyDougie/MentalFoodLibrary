package MFLP.model.dto;

public class BookDTO {

	private int bookNo;
	private String title;
	private String description;
	private String writer;
	private String writeDate;
	private String publisher;
	private int available;
	private String bookImg;
	private int categoryNo;

	public BookDTO() {
	}

	/**
	 * @param bookNo
	 * @param title
	 * @param description
	 * @param writer
	 * @param writeDate
	 * @param publisher
	 * @param available
	 * @param bookImg
	 * @param categoryNo
	 */
	public BookDTO(int bookNo, String title, String description, String writer, String writeDate, String publisher,
			int available, String bookImg, int categoryNo) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.description = description;
		this.writer = writer;
		this.writeDate = writeDate;
		this.publisher = publisher;
		this.available = available;
		this.bookImg = bookImg;
		this.categoryNo = categoryNo;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
}
