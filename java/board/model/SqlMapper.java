package board.model;

import java.util.ArrayList;

import board.Article;
import board.Reply;
import board.member.Member;

public class SqlMapper {
	
	ArticleDBUtil articleDB = new ArticleDBUtil();
	MemberDBUtil memberDB = new MemberDBUtil();
	ReplyDBUtil replyDB = new ReplyDBUtil();

	public ArrayList<Article> getSearchedList(String keyword) {
		String sql = """
				
				SELECT a.*, m.nickname
				FROM article a
				INNER JOIN `member` m
				ON a.memberIdx = m.idx
				WHERE a.title LIKE '%%%s%%'
				
				""";
		
		sql = String.format(sql, keyword);
		return articleDB.getDataList(sql);
		
	}

	public ArrayList<Article> getArticleList() {
		String sql = """
				SELECT a.*, m.nickname
				FROM article a
				INNER JOIN `member` m
				ON a.memberIdx = m.idx
				""";
					
		ArrayList<Article> articleList = articleDB.getDataList(sql);
		return articleList;
	}

	public Article getArticleById(int id) {

		String sql = """
				
				SELECT a.*, m.nickname
				FROM article a
				INNER JOIN `member`df m
				ON a.memberIdx = m.idx
				WHERE a.idx = %d
				
				""";
		
		sql = String.format(sql, id);
		return articleDB.getData(sql);
	}
	
	
	public void updateArticle(Article a) {
		
			String sql = """

					UPDATE article
					SET title = '%s',
					`body` = '%s'
					WHERE idx = %d
					
					""";
			sql = String.format(sql, a.getTitle(), a.getBody(), a.getIdx());
			articleDB.updateData(sql);
	}
	
	public void insertArticle(Article a) {
		String sql = """
				
				INSERT INTO article 
				SET title = '%s', 
				`body` = '%s', 
				nickname = '%s',
				regDate = '%s'
				
				""";
		
		sql = String.format(sql, a.getTitle(), a.getBody(), a.getNickname(), a.getRegDate());
		articleDB.updateData(sql);
	}
	
	public void deleteArticle(int id) {
		String sql = """
				
				DELETE
				FROM article
				WHERE idx = %d
				
				""";
		sql = String.format(sql, id);
		articleDB.updateData(sql);
		
	}
	
	public void insertMember(Member m) {
		String sql = """
				
				INSERT INTO `member`
				SET loginId = '%s',
				loginPw = '%s',
				nickname = '%s',
				regDate = NOW()
					
				""";
		sql = String.format(sql, m.getLoginId(), m.getLoginPw(), m.getNickname());
		articleDB.updateData(sql);
	}
	
	public Member getMemberLoginIdAndLoginPw(String loginId, String loginPw) {
		String sql = """
				
				SELECT *
				FROM `member`
				WHERE loginId = '%s' 
				AND loginPw = '%s'
				
				""";
		sql = String.format(sql, loginId, loginPw);
		return memberDB.getData(sql);
	}

	public ArrayList<Reply> getRepliesByArticleIdx(int articleIdx) {
		String sql = """
    			
    			SELECT ar.*, m.nickname
    			FROM articleReply ar
    			INNER JOIN `member` m
    			ON ar.articleIdx = m.idx
    			WHERE ar.articleIdx = %d
    			
				""";
		sql = String.format(sql, articleIdx);
		return replyDB.getDataList(sql);
	}

	public void insertReply(Reply reply) {
		String sql = """
							
				INSERT INTO articleReply
			    SET articleIdx = %d,
			    body = '%s',
			    memberIdx = %d,
			    regDate = NOW(),
			    updateDate = NOW()
						
				""";
		sql = String.format(sql, reply.getIdx(), reply.getBody(), reply.getMemberIdx());
		replyDB.updateData(sql);
	}
}
