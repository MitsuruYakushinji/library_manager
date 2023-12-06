package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Library;
import com.example.service.LibraryService;
import com.example.service.LoginUser;


@Controller
@RequestMapping("/library")
public class LibraryController {
	
	private final LibraryService libraryService;
	
	@Autowired
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	// 書籍の一覧表示
	@GetMapping
	public String index(Model model) {
		List<Library> libraries = this.libraryService.findAll();
		model.addAttribute("libraries", libraries);
		return "library/index";
	}
	
	// 書籍IDから書籍情報を1件分検索
	@GetMapping("borrow")
	public String borrowingForm(@RequestParam("id") Integer id, Model model) {
		Library library = this.libraryService.findById(id);
		model.addAttribute("library", library);
		return "library/borrowingForm";
	}
	
	// 書籍の貸し出し処理
	@PostMapping("borrow")
	public String borrow(@RequestParam("id") Integer id, @RequestParam("return_due_date") String returnDueDate, @AuthenticationPrincipal LoginUser loginUser) {
		Library library = this.libraryService.findById(id);
		library.setUserId(loginUser.getUser().getId());
		
		// Logsモデルを利用したINSERT処理
		
		return "redirect:/library";
	}
	
}
