package com.ssafy.guestbook.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.guestbook.model.FileInfoDto;
import com.ssafy.guestbook.model.GuestBookDto;

public interface GuestBookDao {
	
	void registerArticle(GuestBookDto guestBookDto) throws SQLException;
	List<GuestBookDto> listArticle(Map<String, Object> map) throws SQLException;
	int getTotalCount(Map<String, String> map) throws SQLException;
	GuestBookDto getArticle(int articleNo) throws SQLException;
	void updateArticle(GuestBookDto guestBookDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	List<FileInfoDto> fileInfoList(int articleNo) throws SQLException;
	
}
