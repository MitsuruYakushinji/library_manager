package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

	// ログ情報の全件取得
	public List<Log> findAll() {
		return this.logRepository.findAll();
	}

	// ログから書籍IDとユーザーIDに該当する最新情報取得
	public Optional<Log> findByLibraryIdAndUserId(Integer libraryId, Integer userId) {
		return this.logRepository.findByLibraryIdAndUserId(libraryId, userId);
	}

	//	public Log findByLibraryId(Integer libraryId) {
	//		Optional <Log> optionalLog = this.logRepository.findById(libraryId);
	//		Log log = optionalLog.get();
	//		return log;
	//	}
	//	
	//	public Log findByUserId(Integer userId) {
	//		Optional <Log> optionalLog = this.logRepository.findById(userId);
	//		Log log = optionalLog.get();
	//		return log;
	//	}

	// 貸し出し時データ保存用
	public Log save(Integer id, Integer userId, LocalDateTime rentDate, LocalDateTime preReturnDueDate,
			LocalDateTime returnDate) {
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

	// 返却時データ更新用
	public void update(Integer libraryId, Integer userId) {

		// libraryId と userId でLogsテーブルを検索
		Optional<Log> optionalLog = findByLibraryIdAndUserId(libraryId, userId);

//		// Logsテーブルの検索結果があるか確認
//		optionalLog.isPresent();

		Log log = optionalLog.get();

		// RETURN_DATE を現在の日付で更新
		log.setReturnDate(LocalDateTime.now());

		// 更新されたLogsテーブルの情報を保存
		this.logRepository.save(log);
	}
}
