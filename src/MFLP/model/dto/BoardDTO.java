package MFLP.model.dto;

public class BoardDTO {
	
	private int boardNo; // �� ��ȣ
	private String title; // ����
	private String content; // ����
	private String writeDate; // �ۼ���
	private String fileName;// ÷�� ���� �̸�
	private int readNum; // ��ȸ��
	private int category; //�����Խ���0, ��� 1  ���� 2  ��Ÿ 3
	private String writerId; // �ۼ��� ���̵�
	private int parentNo; // ������ ������ ���� �θ��� ��ȣ


	public BoardDTO() {
		super();
	}

	public BoardDTO(int boardNo, String title, String content, String writeDate, String fileName, int readNum,
			int category, String writerId, int parentNo) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.fileName = fileName;
		this.readNum = readNum;
		this.category = category;
		this.writerId = writerId;
		this.parentNo = parentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "BoardDTO [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", writeDate="
				+ writeDate + ", fileName=" + fileName + ", readNum=" + readNum + ", category="
				+ category + ", writerId=" + writerId + ", parentNo=" + parentNo + "]";
	}
	


}
