package board;

public class Article {
	
	private int idx;
	private String title;
	private int memberIdx;
	private String nickname;
	private String body;
	private String regDate;
	
	public Article(int idx, String title, int memberIdx, String nickname, String body, String regDate) {
		this.idx = idx;
		this.title = title;
		this.memberIdx = memberIdx;
		this.nickname = nickname;
		this.body = body;
		this.regDate = regDate;
	}

	public Article(int idx, String title, String body) {
		this.idx = idx;
		this.title = title;
		this.body = body;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
