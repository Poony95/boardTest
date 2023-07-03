package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;
import com.sist.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@RequestMapping("/updateBoard")
public class UpdateBoardController {
	@Autowired
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void form(Model model, int no) {
		model.addAttribute("b", dao.findByno(no));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(BoardVO b, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		String path = request.getServletContext().getRealPath("upload");
		String oldFname = b.getFname();

		String fname = null;
		MultipartFile uploadFile = b.getUploadFile();
		fname = uploadFile.getOriginalFilename();

		if (fname != null && !fname.equals("")) {
			fname = MyUtil.getRenameNotMultiple(fname);
			b.setFname(fname);
		}

		try {
			int re = dao.update(b);
			if (re == 1) {
				if (fname != null && !fname.equals("")) {
					File file = new File(path + "/" + oldFname);
					file.delete();
					try {
						byte []data = uploadFile.getBytes();
						FileOutputStream fos = new FileOutputStream(path + "/" + fname);
						fos.write(data);
						fos.close();
					} catch (Exception e) {
						System.out.println("예외발생 : " + e.getMessage());
					}
				}
			} else {
				mav.addObject("msg", "게시물 수정에 실패했습니다.");
				mav.setViewName("error");
			}
		} catch (Exception e) {
			mav.addObject("msg", "게시물 수정에 실패했습니다.");
			mav.setViewName("error");
		}
		return mav;
	}
}
