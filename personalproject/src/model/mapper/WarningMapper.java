package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.Warning;

public interface WarningMapper {
	@Select("select ifnull(max(seq),0) from warning")
	int maxseq();
	
	@Insert("insert into warning "
	+ " (num, seq, reason, id, warnid) "
	+ " values(#{num}, #{seq}, #{reason}, #{id}, #{warnid})")
	int insert(Warning w);
	
	@Select({"<script>",
		 "select count(*) from warning ",
		 " <if test='col1 != null'> where ${col1} like '%${find}%'</if>",
		 " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
		 "</script>"})
		 Integer boardCount(Map<String, Object> map);
	
	@Select({"<script>",
	    "select * from warning ",
	    " <if test='col1 != null'> where ${col1} like '%${find}%'</if>",
	    " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
	    " <choose>",
	    " <when test='seq != null'> where seq = #{seq} </when>",
	     "</choose>",
	    "</script>"})
		List<Warning> select(Map<String, Object> map);

	@Delete("delete from warning where seq=#{seq}")
	int delete(int seq);
}
