package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;
import com.oracle.wls.shaded.org.apache.xalan.xsltc.compiler.sym;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	public static int pageSize =10;
	public static int totalRecord;
	public static int totalPage;
	
	
	@GetMapping("/listBoard")
	public void list(Model model, int pageNUM) {
		
		int start = (pageNUM-1)*pageSize+1; //현재 페이지에 보여줄 시작레코드
		int end=start+pageSize-1;			//현재 페이지에 보여줄 마지막레코드
		
		
		HashMap<String, Object> map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		model.addAttribute("list",dao.findAll(map));
	}
	
	@GetMapping("/detailBoard")
	public ModelAndView detail(int no) {
		ModelAndView mav = new ModelAndView();
		dao.updateHit(no);
		mav.addObject("b",dao.findByno(no));
		return mav;
	}
	
	@GetMapping("/deleteBoard")
	public void deleteForm (Model model, int no) {
		model.addAttribute("b",dao.findByno(no));
	}
	
	
	@PostMapping("/deleteBoard")
	public ModelAndView deleteSubmit (BoardVO b, String pwd, String fname, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		String path = request.getServletContext().getRealPath("upload");
		try {
			int re = dao.delete(b);
			pwd = b.getPwd();
			if(re == 1) {
				File file = new File(path + "/" + fname);
				file.delete();
			}else {
				mav.setViewName("redirect:/listBoard");
			}
		} catch (Exception e) {
			System.out.println("예외발생 deleteBoard"+e.getMessage());
		}
		return mav;
	}
}
