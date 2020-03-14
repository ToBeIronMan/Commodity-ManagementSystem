package wust.commodity_management_system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wust.commodity_management_system.model.User;

import java.util.List;

public  interface UserMapper extends BaseMapper<User> {

    @Select(" select * from user")
    List<User> selectAll();
    @Select("select * from user where user_name= #{name}")
   User  selectpasswordByname(@Param("name") String name);
    @Select("select * from user where user_name like #{searchwords} ")
    List<User> searchuser(@Param("searchwords") String searchwords);
@Select("select * from user where user_name=#{username} and user_password=#{password}")
    User getUserByUsernameAndPassword(String username, String password);
@Select("select user_id from user where user_name= #{username}")
    Long sletectIdByUserName(@Param("username") String username);
}
