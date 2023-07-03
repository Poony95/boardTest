package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;

public class DBManager {

	public static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생 DBManager :"+e.getMessage());
		}
	}
	
	public static List<BoardVO> findAll(HashMap<String, Object> map){
		SqlSession session = sqlSessionFactory.openSession();
		List<BoardVO> list = session.selectList("board.findAll");
		session.close();
		return list;
	}
	
	public static BoardVO findByNo (int no) {
		BoardVO b = null;
		SqlSession session = sqlSessionFactory.openSession();
		b = session.selectOne("board.findByNo", no);
		session.close();
		return b;
	}
	
	public static int insert (BoardVO b) {
		int re = -1;
		SqlSession session=sqlSessionFactory.openSession();
		re = session.insert("board.insert", b);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getNextNo() {
		int no = 0;
		SqlSession session = sqlSessionFactory.openSession();
		no = session.selectOne("board.getNextNo");
		session.close();
		return no;
	}
	
	public static void updateHit(int no) {
		SqlSession session = sqlSessionFactory.openSession();
		session.update("board.updateHit",no);
		session.commit();
		session.close();
	}
	
	public static void updateStep(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		session.update("board.updateStep", map);
		session.commit();
		session.close();
	}
	
	public static int update(BoardVO b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.update("board.update",b);
		session.close();
		return re;
	}
	
	public static int delete(BoardVO b) {
		int re = -1;
		SqlSession session =sqlSessionFactory.openSession(true);
		re = session.delete("board.delete",b);
		session.close();
		return re;
	}
}
