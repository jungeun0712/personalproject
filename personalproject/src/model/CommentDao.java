package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.CommentMapper;

public class CommentDao {
	private Map<String,Object> map=new HashMap<String,Object>();
	private Class<CommentMapper> cls = CommentMapper.class;
	public int maxseq() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).maxseq();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return 0;
	}
	//게시물 등록
	public boolean insert(Comment c) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).insert(c);
			if (cnt > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return false;
	}
	public int boardCount(String column, String find, int seq, int num) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("seq", seq);
			map.put("num", num);
			if(column != null) {
				String[] col = column.split(",");
			    map.put("col1",col[0]);
			    if(col.length == 2) {
			       map.put("col2",col[1]);
			    }
			    map.put("find",find);
			}
			return session.getMapper(cls).boardCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return 0;
	}
	public List<Comment> list(int num) {
	SqlSession session = DBConnection.getConnection();
	try {
		map.clear();
		map.put("num", num);
		return session.getMapper(cls).select(map);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		 DBConnection.close(session);
	}
	return null;		
	}
	
	public Comment selectOne(int num) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("num", num);
			return session.getMapper(cls).select(map).get(0);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return null;
	}
}
