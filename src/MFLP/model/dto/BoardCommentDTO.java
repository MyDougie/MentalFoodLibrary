package MFLP.model.dto;

public class BoardCommentDTO {
	private int commentNo; //��۹�ȣ
	private String content; //��۳���
	private String writeDate; //����ۼ���
	private String memberId; //����ۼ��� 
	private int boardNo; //����� �ۼ��ϴ� �Խñ۹�ȣ
	private int parentNo; //����
	
	public BoardCommentDTO(){}
	
	public BoardCommentDTO(int commentNo, String content, String writeDate, String memberId, int boardNo, int parentNo) {
		super();
		this.commentNo = commentNo;
		this.content = content;
		this.writeDate = writeDate;
		this.memberId = memberId;
		this.boardNo = boardNo;
		this.parentNo = parentNo;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public String getContent() {
		return content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "BoardCommentDTO [commentNo=" + commentNo + ", content=" + content + ", writeDate=" + writeDate
				+ ", memberId=" + memberId + ", boardNo=" + boardNo + ", parentNo=" + parentNo + "]";
	}
		
}
