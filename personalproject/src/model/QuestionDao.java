package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.BoardMapper;
import model.mapper.QuestionMapper;

public class QuestionDao {
	private Map<String,Object> map=new HashMap<String,Object>();
    private Class<QuestionMapper> cls = QuestionMapper.class;
	public int maxnum() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).maxnum();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return 0;
	}
	//게시물 등록
	public boolean insert(Question q) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).insert(q);
			if (cnt > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return false;
	}
	public int boardCount(String column, String find) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
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
	public List<Question> list
	      (int pageNum,int limit,String column, String find) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("start", (pageNum -1) * limit);
			map.put("limit",limit);
			if(column != null) {
				String[] col = column.split(",");
			    map.put("col1",col[0]);
			    if(col.length == 2) {
			       map.put("col2",col[1]);
			    }
			    map.put("find",find);
			}
			return session.getMapper(cls).select(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return null;		
	}	
	public Question selectOne(int num) {
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
	public boolean update(Question q) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).update(q);
			if (cnt > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return false;
	}
}
