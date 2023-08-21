package mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 팩터리 빌더에서 sql 세션 가져오기  
public class Config {
	private SqlSession sqlSession;

	public Config() {
		try {
			// 1. MyBatis 설정 파일의 경로를 지정합니다.
			String resource = "mybatis-config.xml";

			// 2. 설정 파일을 읽어들이는 InputStream 객체를 생성합니다.
			// Resources 클래스의 getResourceAsStream 메서드를 사용하여 설정 파일의 경로를 입력 스트림으로 변환
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 3. SqlSessionFactory를 생성합니다.
			// 이 객체는 MyBatis의 설정 정보를 담고 있으며, SqlSession 객체를 생성할 때 사용됩니다.
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			System.out.println(sqlSessionFactory);

			// 4. SqlSessionFactory를 사용하여 SqlSession 객체를 생성(열)합니다.
			// SqlSession 객체는 실제로 데이터베이스와의 연결을 관리합니다.
			sqlSession = sqlSessionFactory.openSession();
			System.out.println(sqlSession);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}
}