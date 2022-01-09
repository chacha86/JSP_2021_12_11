package board;

public class Reply {
	private int idx;
	private int parentNo;
	private String body;
	private int memberIdx;
	private String nickname;
	private String regDate;

	public Reply(int parentNo, String body, int memberIdx, String nickname, String regDate) {
		this.parentNo = parentNo;
		this.body = body;
		this.memberIdx = memberIdx;
		this.nickname = nickname;
		this.regDate = regDate;
	}
	
	public Reply(int parentNo, String body, int memberIdx) {
		this.parentNo = parentNo;
		this.body = body;
		this.memberIdx = memberIdx;
	}
	
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String rBody) {
		this.body = body;
	}
	public String getNickname() {
		return nickname;
	}
	public void setWriter(String nickname) {
		this.nickname = nickname;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
}
