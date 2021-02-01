package org.mybatis.test.mapper;

import org.apache.ibatis.annotations.*;
import org.mybatis.test.entity.User;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(id, username, password, avatar, address) VALUES (null, #{username}, #{password}, #{avatar}, #{address} )")
    Integer insert(User user);

    @Results(
            id = "userMap",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "created_at", property = "createdAt")
            }
    )
    @Select("select id, username, password, avatar, address, created_at FROM user WHERE id = #{id}")
    User selectById(@Param("id") Integer id);

}
