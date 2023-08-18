package mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class BoardDAO {
	
	
	private SqlSession sqlSession;
	
	// 생성자 생성
	public BoardDAO() {
		
	}
	
	public BoardDAO(SqlSession session) {
		this.sqlSession = session;
	}
	
	// db에 sql 호출
	public void work() {
//		insertNewPost();
//		selectAllPost();
		selectbyName();
	}
	
	public void selectbyName() {
		BoardVO boardVO1 = new BoardVO();
		boardVO1.setWriter("홍길동");
		List<BoardVO> boardlist =
		sqlSession.selectList("mybatis.BoardDAO.selectbyName", boardVO1);
	
		for(BoardVO boardVO: boardlist) {
			System.out.println(boardVO);
		}
	}
	
	// selectList는 리스트를 리턴해줌
	public void selectAllPost() {
		List<BoardVO> boardlist = sqlSession.selectList("mybatis.BoardDAO.selectAll");
		
		for(BoardVO boardVO : boardlist) {
			System.out.println(boardVO);
		}
	}
	
	public void insertNewPost() {
		// sql 세션을 갖고 insert 할 것
		// 메퍼의 네임스페이스와 아이디의 조합으로 인서트하고 커밋해라
		// sqlSession.insert("mybatis.BoardDAO.insertNewPost");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("두번째 제목");
		boardVO.setWriter("김길동");
		boardVO.setContent("내용");
		
		// (메퍼의 네임스페이스와 아이디의 조합, 넘길 내용)
		sqlSession.insert("mybatis.BoardDAO.insertNewPost2", boardVO);
		
		sqlSession.commit();
	}
}
