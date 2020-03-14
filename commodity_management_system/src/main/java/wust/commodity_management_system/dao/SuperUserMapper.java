package wust.commodity_management_system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wust.commodity_management_system.model.SuperUser;
import wust.commodity_management_system.model.User;


public interface SuperUserMapper extends BaseMapper<SuperUser> {
    /*选择所有的用户*/

    @Select("select * form user where user_name= #{username} and user_password= #{password}")
    User selectByPasswordAndUsername(@Param("username")String username, @Param("password")String password);
    @Select("select * from super_user where super_user_name= #{name}")
    SuperUser  selectSuperUserdByname(@Param("name") String name);
}
