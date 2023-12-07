package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Log;
import com.example.repository.LogRepository;

@Service
public class LogService {

	private final LogRepository logRepository;
	
	@Autowired
	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	public List<Log> findAll(){
		return this.logRepository.findAll();
	}
	
	// データ保存用
	public Log save(Integer id, Integer userId, LocalDateTime rentDate, LocalDateTime preReturnDueDate, LocalDateTime returnDate) {
		// Entityクラス(Log)のインスタンス生成
		Log log = new Log();
		log.setLibraryId(id);
		log.setUserId(userId);
		log.setRentDate(rentDate);
		log.setReturnDueDate(preReturnDueDate);
		log.setReturnDate(returnDate);
		
		// reposotory.saveメソッドを利用してデータの保存を行う
	    return this.logRepository.save(log);
	}
}
