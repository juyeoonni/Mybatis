package mybatis;

public class MybatisMain {

	public static void main(String[] args) {
		Config config = new Config();
		
		// 보드 DAO 내부에 sql세션 가져오기
		// BoardDAO 객체를 생성하면서 Config로부터 얻은 SqlSession을 주입
		BoardDAO boardDAO = new BoardDAO(config.getSqlSession());
		
		// 실제 데이터베이스 작업을 시작
		boardDAO.work();
		
	}

}

