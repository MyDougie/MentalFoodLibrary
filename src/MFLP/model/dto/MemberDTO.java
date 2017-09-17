package MFLP.model.dto;

public class MemberDTO {
	private String member;
	private String name;
	private String password;
	private String addr;
	private String birth;
	private String phone;
	private int memberCode;
	private int memberCount;
	
	public MemberDTO(){}

	public MemberDTO(String member, String name, String password, String addr, String birth, String phone, int memberCode,
			int memberCount) {
		super();
		this.member = member;
		this.name = name;
		this.password = password;
		this.addr = addr;
		this.birth = birth;
		this.phone = phone;
		this.memberCode = memberCode;
		this.memberCount = memberCount;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	
	
}
