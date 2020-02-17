package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Board;
import model.Question;

public interface QuestionMapper {
	@Select("select ifnull(max(num),0) from question")
	int maxnum();

	@Insert("insert into question "
 + " (num,question,pw,today)"
 + " values(#{num},#{question},#{pw},#{today})")
	int insert(Question q);

	@Select({"<script>",
   "select count(*) from question",
   " <if test='col1 != null'>where ${col1} like '%${find}%'</if>",
   " <if test='col2 != null'>OR ${col2} like '%${find}%'</if>",
   "</script>"})
	Integer boardCount(Map<String, Object> map);

	@Select({"<script>",
	    "select * from question",
	    " <if test='col1 != null'> where ${col1} like '%${find}%'</if>",
	    " <if test='col2 != null'> or ${col2} like '%${find}%'</if>",
	    " <choose>",
	    " <when test='num != null'> where num = #{num} </when>",
	    " <otherwise>"
	    + " order by num desc limit #{start},#{limit}"
	    + "</otherwise>",
	     "</choose>",
	    "</script>"})
		List<Question> select(Map<String, Object> map);
		
	@Update("update question set question=#{question}, pw=#{pw} where num=#{num}")
	int update(Question q);
}
