package wust.commodity_management_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import wust.commodity_management_system.model.Permission;
import wust.commodity_management_system.model.SuperUser;
import wust.commodity_management_system.model.User;
/**
 * @author lucky
 */

public interface SuperUserService extends IService<SuperUser> {
    /*修改用户权限*/
    public int modifyPermission(User user);
    /*删除用户*/
    public int deltePermission(Long userId);
    /*获取用户权限信息*/
    public Permission getPermission(Long UserId);
    /*获取用户列表*/
    PageInfo<User> selectAll(int pageNum, int pageSize);
    /*修改权限*/
    public int updatePerssion(Permission permission);


    PageInfo<User> searchuser(int pageNum, int pageSize, String searchwords);

    void deleteuser(int id);

    User getUserByUsernameAndPassword(String username, String password);

    boolean login(String username, String password);
}
