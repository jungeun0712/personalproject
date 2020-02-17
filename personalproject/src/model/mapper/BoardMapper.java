package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Board;

public interface BoardMapper {

	@Select("select ifnull(max(num),0) from infoboard")
	int maxnum();

	@Insert("insert into infoboard "
 + " (num, id, type, keyword, checkpass, subject, content, file1, readcnt, regdate)"
 + " values(#{num}, #{id}, #{type}, #{keyword}, #{checkpass}, #{subject}, #{content}, #{file1}, #{readcnt}, now())")
	int insert(Board board);
	
	@Select({"<script>",
   "select count(*) from infoboard where id=#{id} and type=#{type}",
   " <if test='col1 != null'> and ${col1} like '%${find}%'</if>",
   " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
   "</script>"})
	Integer boardCount(Map<String, Object> map);

	@Select({"<script>",
		   "select count(*) from infoboard where type=#{type}",
		   " <if test='col1 != null'> and ${col1} like '%${find}%'</if>",
		   " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
		   "</script>"})
	Integer boardCount2(Map<String, Object> map);
	
	@Select({"<script>",
    "select * from infoboard where id=#{id} and type=#{type}",
    " <if test='col1 != null'> and ${col1} like '%${find}%'</if>",
    " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
    " <choose>",
    " <when test='num != null'> and num = #{num} </when>",
    " <otherwise>"
    + " order by num desc limit #{start},#{limit}"
    + "</otherwise>",
     "</choose>",
    "</script>"})
	List<Board> select(Map<String, Object> map);
	
	@Select({"<script>",
	    "select * from infoboard where type=#{type}",
	    " <if test='col1 != null'> and ${col1} like '%${find}%'</if>",
	    " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
	    " <choose>",
	    " <when test='num != null'> and num = #{num} </when>",
	    " <otherwise>"
	    + " order by num desc limit #{start},#{limit}"
	    + "</otherwise>",
	     "</choose>",
	    "</script>"})
		List<Board> select2(Map<String, Object> map);
	
	@Update("update infoboard set readcnt = readcnt + 1 "
			+ " where num = #{num}")
	void readcntadd(int num);

	@Update("update  infoboard  set checkpass=#{checkpass}, subject=#{subject},"
		+ " content=#{content},file1=#{file1}, keyword=#{keyword} where id=#{id} and type=#{type} and num=#{num}")
	int update(Board board);

	@Delete("delete from infoboard where num=#{num}")
	int delete(int num);

	@Select("select question from question where today = curdate()")
	String select3();

}
