package MFLP.model.dto;

public class BoardCommentDTO {
	private int commentNo; //´ñ±Û¹øÈ£
	private String content; //´ñ±Û³»¿ë
	private String writeDate; //´ñ±ÛÀÛ¼ºÀÏ
	private String memberId; //´ñ±ÛÀÛ¼ºÀÚ 
	private int boardNo; //´ñ±ÛÀ» ÀÛ¼ºÇÏ´Â °Ô½Ã±Û¹øÈ£
	private int parentNo; //´ë´ñ±Û
	
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
