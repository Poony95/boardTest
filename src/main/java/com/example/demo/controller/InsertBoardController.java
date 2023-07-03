package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;
import com.sist.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/insertBoard")
public class InsertBoardController {
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void insertForm(@RequestParam(value ="no", defaultValue = "0") int no, Model model) {
		//no
		//답글이면 부모글의 글번호
		//새글이면 0
		model.addAttribute("no", no);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView insertSubmit(BoardVO b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		int no = dao.getNextNo();
		//답글쓰기와 관련한 세개의 칼럼에 대한 값을 설정 (일단 새 글로 설정)
				int b_ref = no;
				int b_level = 0;
				int b_step = 0;
				
				// 답글인지 여기서 어떻게 알 수 있나요?
				int pno = b.getNo();
				if(pno != 0) {
					//부모글 가져오기
					BoardVO p = dao.findByno(pno);
					b_ref = p.getB_ref();
					b_level = p.getB_level();
					b_step = p.getB_step();
					
					HashMap<String, Object> map = new HashMap();
					map.put("b_ref", b_ref);
					map.put("b_step", b_step);
					dao.updateStep(map);
					
					b_level++;
					b_step++;
				}
				
				b.setNo(no);
				b.setB_ref(b_ref);
				b.setB_level(b_level);
				b.setB_step(b_step);
				
		String path = request.getServletContext().getRealPath("upload");
		System.out.println("path : " + path);
		
		String fname = null;
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();
		
		// 업로드 파일이 없다면
		b.setFname("");
		
		//업로드 파일이 있다면
		if(fname != null && !fname.equals("")) {
			fname = MyUtil.getRenameNotMultiple(fname);
			b.setFname(fname);
		}
		try {
			int re = dao.insert(b);
			if(re == 1) {
				if(fname != null && !fname.equals("")) {
					try {
						byte []data = uploadFile.getBytes();
						FileOutputStream fos = new FileOutputStream(path + "/" +fname);
						fos.write(data);
						fos.close();
					} catch (Exception e) {
						System.out.println("예외발생 : "+e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			mav.addObject("msg","게시물등록에 실패했습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
}