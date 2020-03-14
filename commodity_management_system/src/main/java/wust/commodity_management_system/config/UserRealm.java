package wust.commodity_management_system.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import wust.commodity_management_system.dao.PermissionMapper;
import wust.commodity_management_system.dao.SuperUserMapper;
import wust.commodity_management_system.dao.UserMapper;
import wust.commodity_management_system.model.Permission;
import wust.commodity_management_system.model.SuperUser;
import wust.commodity_management_system.model.User;

/**
 * @author lucky
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper=null;
    @Autowired
    SuperUserMapper superUserMapper=null;
    @Autowired
    PermissionMapper permissionMapper=null;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
        if(subject.getPrincipal().getClass().equals(SuperUser.class))
        {
            info.addStringPermission("admin");
            info.addStringPermission("add");
            info.addStringPermission("update");
            info.addStringPermission("look");
            info.addStringPermission("delete");
        }
        else  if(subject.getPrincipal().getClass().equals(User.class)){
            User user=(User)subject.getPrincipal();
            Permission permission =permissionMapper.selectById(user.getUserId());
            if(permission!=null)
            {
                if(permission.getLookPermission()>0)
                {
                    info.addStringPermission("look");
                }
                if(permission.getUpdatePermission()>0)
            {
                info.addStringPermission("update");
            }
                if(permission.getDeletePermission()>0)
                {
                    info.addStringPermission("delete");
                }
                if(permission.getAddPermission()>0)
                {
                    info.addStringPermission("add");
                }
            }

        }


        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken args0) throws AuthenticationException {
        System.out.println("执行认证逻辑");
       String password=null;
        UsernamePasswordToken token=(UsernamePasswordToken)args0;
        User user=userMapper.selectpasswordByname(token.getUsername());
        if(user!=null)
        {
            password=user.getUserPassword();
            return new SimpleAuthenticationInfo(user,password,"");
        }
        else  {
            SuperUser superUser=superUserMapper.selectSuperUserdByname(token.getUsername());
           if(superUser!=null)
           {
              password=superUser.getSuperUserPassword();
              return new SimpleAuthenticationInfo(superUser,password,"");
           }
           else{
               return null;
           }
        }

    }
}
