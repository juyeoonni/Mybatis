package mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
//		selectbyName();
//		selectbyName2();
//		selectbyNo();
//		selectbyNo2();
//		updatebyName("홍길동", "정주연");
//		deletebyNo(11);
//		selectDynamicSQL();
		selectDynamicSQLif();
	}
	
//	마이바티스는 mapper부터 바텀업으로 하는 방식이 좋다
	public void selectDynamicSQLif() {
		
		//메퍼의 네임스페이스 + id = selectDSQLif1
		List<BoardVO> boardlist =
		
		// title이 없으면 전체 조회가 되고 
		sqlSession.selectList("mybatis.BoardDAO.selectDSQLif1");
		
		// title 있다면 조건에 해당하는 것이 select 된다. 
		// sqlSession.selectList("mybatis.BoardDAO.selectDSQLif1", "오늘의 날씨");
		for (BoardVO boardVO : boardlist) {
			System.out.println(boardVO);
		}
		
	}
	
	public void selectDynamicSQL() {
		BoardVO inputboardVO = new BoardVO();
		inputboardVO.setTitle("좋은아침");
		inputboardVO.setWriter("홍길동");
		List<BoardVO> boardlist = sqlSession.selectList("mybatis.BoardDAO.selectDSQL", inputboardVO);
		
		for (BoardVO resultboardVO : boardlist) {
			System.out.println(resultboardVO);
		}
	}
	
	public void selectDynamicSQL2() {
		BoardVO inputboardVO = new BoardVO();
		inputboardVO.setTitle("좋은아침");
		inputboardVO.setWriter("홍길동");
		List<BoardVO> boardlist =
		sqlSession.selectList("mybatis.BoardDAO.selectDSQL2", inputboardVO);
		
		for (BoardVO resultboardVO : boardlist) {
			System.out.println(resultboardVO);
		}
	}
	
	public void updatebyName(String oldWriter, String newWriter) {
		BoardVO boardVO = new BoardVO();
		Map<String, Object> map = new HashMap<>();
		map.put("oldWriter", oldWriter);
		map.put("newWriter", newWriter);
		
		sqlSession.update("mybatis.BoardDAO.updatebyName", map);
		
		sqlSession.commit();
	}
	
	public void deletebyNo(int no) {
		 sqlSession.delete("mybatis.BoardDAO.deletebyNo", no);
	     sqlSession.commit();
	}
	
	
	public void selectbyNo2() {
		// map을 resultType으로 준다
		Map<String, Object> map = sqlSession.selectOne("mybatis.BoardDAO.selectbyNo2", 10);
		Set<String> keyset = map.keySet();
		for(String key : keyset) {
			System.out.println(key + ": " + map.get(key));
		}
	}
	
	public void selectbyNo() {
		BoardVO board = sqlSession.selectOne("mybatis.BoardDAO.selectbyNo", 10);
		System.out.println(board);
	}
	
	public void selectbyName2() {
		// String name = "홍길동";
		List<BoardVO> boardlist =
		sqlSession.selectList("mybatis.BoardDAO.selectbyName2", "홍길동");
	
		for(BoardVO boardVO: boardlist) {
			System.out.println(boardVO);
		}
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
