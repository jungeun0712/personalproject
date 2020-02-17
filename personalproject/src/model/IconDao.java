package model;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.IconMapper;


public class IconDao {
	private Map<String,Object> map=new HashMap<String,Object>();
    private Class<IconMapper> cls = IconMapper.class;

	//게시물 등록
	public boolean insert(Icon i) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).insert(i);
			if (cnt > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DBConnection.close(session);
		}
		return false;
	}
}
