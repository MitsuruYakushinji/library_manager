package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Library;
import com.example.repository.LibraryRepository;

@Service
public class LibraryService {

	private final LibraryRepository libraryRepository;
	
	@Autowired
	public LibraryService(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}
	
	// 書籍情報の全件取得
	public List<Library> findAll(){
		return this.libraryRepository.findAll();
	}
	
	// 書籍情報をIDで1件分検索するメソッド
	public Library findById(Integer id) {
		
		Optional<Library> optionalLibrary = this.libraryRepository.findById(id);
		
		Library library = optionalLibrary.get();
		
		return library;
	}
	
}
