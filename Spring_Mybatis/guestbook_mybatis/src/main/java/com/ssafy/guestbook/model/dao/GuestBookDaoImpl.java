package com.ssafy.guestbook.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ssafy.guestbook.model.FileInfoDto;
import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.util.SqlMapConfig;

@Repository
public class GuestBookDaoImpl implements GuestBookDao {

	private final String NAMESPACE = "com.ssafy.guestbook.model.dao.GuestBookDao.";

	@Override
	public void registerArticle(GuestBookDto guestBookDto) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.insert(NAMESPACE + "registerArticle", guestBookDto);
			List<FileInfoDto> fileInfos = guestBookDto.getFileInfos();
			if (fileInfos != null && !fileInfos.isEmpty()) {
				sqlSession.insert(NAMESPACE + "registerFile", guestBookDto);
			}
			sqlSession.commit();
		}
	}

	@Override
	public List<GuestBookDto> listArticle(Map<String, Object> map) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectList(NAMESPACE + "listArticle", map);
		}
	}

	@Override
	public int getTotalCount(Map<String, String> map) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectOne(NAMESPACE + "getTotalCount", map);
		}
	}

	@Override
	public GuestBookDto getArticle(int articleNo) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectOne(NAMESPACE + "getArticle", articleNo);
		}
	}

	@Override
	public void updateArticle(GuestBookDto guestBookDto) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.update(NAMESPACE + "updateArticle", guestBookDto);
			sqlSession.commit();
		}
	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.delete(NAMESPACE + "deleteFile", articleNo);
			sqlSession.delete(NAMESPACE + "deleteArticle", articleNo);
			sqlSession.commit();
		}
	}

	@Override
	public List<FileInfoDto> fileInfoList(int articleNo) throws SQLException {
		try (SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectList(NAMESPACE + "fileInfoList", articleNo);
		}
	}

}
