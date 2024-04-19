package org.iwms.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.iwms.server.model.UserProfile;

import java.util.List;

@Mapper
public interface UserProfileMapper {
    @Select("SELECT * FROM user_profile")
    List<UserProfile> getAllUsers();
    @Insert("INSERT INTO user_profile (name) VALUES (#{name})")
    void insertUserByName(String name);
    @Insert("INSERT INTO user_profile (user_id, name) VALUES (#{userId}, #{name})")
    void insertUserByUserIdAndName(Integer userId, String name);
}
