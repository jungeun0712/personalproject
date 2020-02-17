package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Member;

public interface MemberMapper {
	@Select({"<script>",
		"select * from infohuman ",
		"<if test='id != null'> where binary id=#{id}</if>",
		"</script>"})
	List<Member> select(Map<String, Object> map);

	@Insert("insert into infohuman "
	+ "(id, pass, name, email, tel)"
	+ " values (#{id},#{pass},#{name},#{email},#{tel})")
	int insert(Member m);

	@Update("update infohuman set name=#{name},"
		+ " email=#{email}, tel=#{tel}"
		+ " where id=#{id}")
	int update(Member m);

	@Delete("delete from infohuman where id=#{id}")
	int delete(String id);

	@Update("update infohuman set pass=#{pass} where id=#{id}")
	int updatepass
	    (@Param("id") String id, @Param("pass") String pass);

	@Select("select id from infohuman"
			+ "  where name = #{name} and email = #{email}" )
	String idSearch
	   (@Param("name") String name,@Param("email") String email);
	
    @Select("select pass from infohuman "
  	+ " where id =#{id}  and name = #{name}")
	String pwSearch(@Param("id") String id, @Param("name") String name);
}
