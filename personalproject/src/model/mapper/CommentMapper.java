package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.Board;
import model.Comment;

public interface CommentMapper {

	@Select("select ifnull(max(seq),0) from comment")
	int maxseq();
	
	@Insert("insert into comment "
	+ " (num, seq, id, comment)"
	+ " values(#{num}, #{seq}, #{id}, #{comment})")
		int insert(Comment c);
	
	@Select({"<script>",
	 "select count(*) from comment where num=#{num}",
	 " <if test='col1 != null'> and ${col1} like '%${find}%'</if>",
	 " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
	 "</script>"})
	 Integer boardCount(Map<String, Object> map);


	@Select({"<script>",
	    "select * from comment ",
	    " <if test='col1 != null'> where ${col1} like '%${find}%'</if>",
	    " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
	    " <choose>",
	    " <when test='num != null'> where num = #{num} </when>",
	     "</choose>",
	    "</script>"})
		List<Comment> select(Map<String, Object> map);
}
