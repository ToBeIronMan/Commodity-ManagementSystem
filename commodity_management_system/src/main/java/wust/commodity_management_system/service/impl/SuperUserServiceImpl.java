package wust.commodity_management_system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wust.commodity_management_system.dao.PermissionMapper;
import wust.commodity_management_system.dao.SuperUserMapper;
import wust.commodity_management_system.dao.UserMapper;
import wust.commodity_management_system.model.Permission;
import wust.commodity_management_system.model.SuperUser;
import wust.commodity_management_system.model.User;
import wust.commodity_management_system.service.SuperUserService;

import java.util.List;

@Service
class SuperUserPermissionImpl extends ServiceImpl<SuperUserMapper, SuperUser> implements SuperUserService {
    @Autowired
    private SuperUserMapper superUserMapper;
    @Autowired
    private UserMapper userMapper ;
    @Autowired
    private PermissionMapper permissionMaper ;

    @Override
    public int modifyPermission(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deltePermission(Long userId) {
        return superUserMapper.deleteById(userId);
    }

    @Override
    public Permission getPermission(Long userId) {
        return permissionMaper.selectById(userId);
    }



    @Override
    public int updatePerssion(Permission permission) {
        return permissionMaper.updateById(permission);
    }

    @Override
    public PageInfo<User> searchuser(int pageNum, int pageSize, String searchwords) {
        PageHelper.startPage(pageNum, pageSize);
        searchwords="%"+searchwords+"%";
        List<User> users = userMapper.searchuser(searchwords);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public void deleteuser(int id) {
        userMapper.deleteById(id);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userMapper.getUserByUsernameAndPassword( username,  password);
    }

    @Override
    public boolean login(String username, String password) {
        return true;
    }

    @Override
    public PageInfo<User> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }


}