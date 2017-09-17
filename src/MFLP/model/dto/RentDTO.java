package MFLP.model.dto;

public class RentDTO {
   private int bookNo;
   private String title;
   private String startDate;
   private String endDate;
   private int extensionCount;
   private int lateFee;
   private String memberId;
   
   public RentDTO(){}
   public RentDTO(int bookNo, String title, String startDate, String endDate, int extensionCount, int lateFee, String memberId) {
      super();
      this.bookNo = bookNo;
      this.title = title;
      this.startDate = startDate;
      this.endDate = endDate;
      this.extensionCount = extensionCount;
      this.lateFee = lateFee;
      this.memberId = memberId;
   }
   //select r.member_id, r.book_no, b.title, start_date, end_date, extension_count, 100*trunc(sysdate-To_Date(end_date))
   public RentDTO(String memberId, int bookNo, String title, String startDate, String endDate, int extensionCount, int lateFee) {
      super();
      this.memberId = memberId;
      this.bookNo = bookNo;
      this.title = title;
      this.startDate = startDate;
      this.endDate = endDate;
      this.extensionCount = extensionCount;
      this.lateFee = lateFee;
   }
   
   // rent.book_no, member_id, title, start_date, end_date, extension_count, 100*trunc(sysdate-To_Date(end_date))
   
   public RentDTO(int bookNo, String memberId, String title, String startDate, String endDate, int extensionCount, int lateFee) {
      this.bookNo = bookNo;
      this.memberId = memberId;
      this.title = title;
      this.startDate = startDate;
      this.endDate = endDate;
      this.extensionCount = extensionCount;
      this.lateFee = lateFee;
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

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public int getExtensionCount() {
      return extensionCount;
   }

   public void setExtensionCount(int extensionCount) {
      this.extensionCount = extensionCount;
   }

   public int getLateFee() {
      return lateFee;
   }

   public void setLateFee(int lateFee) {
      this.lateFee = lateFee;
   }
   public String getMemberId() {
      return memberId;
   }
   public void setMemberId(String memberId) {
      this.memberId = memberId;
   }
   
}