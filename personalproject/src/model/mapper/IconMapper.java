package model.mapper;

import org.apache.ibatis.annotations.Insert;

import model.Icon;

public interface IconMapper {
	@Insert("insert into icon "
	+ " (id, date, icon)"
    + " values(#{id}, now(), #{icon})")
		int insert(Icon i);
				
}
