package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.WarningMapper;

public class WarningDao {
	private Map<String,Object> map=new HashMap<String,Object>();
	private Class<WarningMapper> cls = WarningMapper.class;
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
	
	public boolean insert(Warning w) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).insert(w);
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

	public List<Warning> list(int pageNum, int limit, String column, String find) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			return session.getMapper(cls).select(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return null;		
		}

	public Warning selectOne(int seq) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("seq", seq);
			return session.getMapper(cls).select(map).get(0);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return null;
	}

	public boolean delete(int seq) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).delete(seq);
			if (cnt > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return false;
	}
}
