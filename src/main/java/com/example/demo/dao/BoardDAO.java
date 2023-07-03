package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.autoconfigure.quartz.JobStoreType;
import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.BoardVO;

@Repository
public class BoardDAO {
	
	public List<BoardVO> findAll(HashMap<String, Object> map){
		return DBManager.findAll(map);
	}
	
	public BoardVO findByno(int no) {
		return DBManager.findByNo(no);
	}
	
	public int insert(BoardVO b) {
		return DBManager.insert(b);
	} 
	
	public int getNextNo() {
		return DBManager.getNextNo();
	}
	
	public void updateHit(int no) {
		DBManager.updateHit(no);
	}
	
	public void updateStep (HashMap<String, Object> map) {
		DBManager.updateStep(map);
	}
	
	public int update(BoardVO b) {
		return DBManager.update(b);
	}
	
	public int delete(BoardVO b) {
		return DBManager.delete(b);
	}
}
