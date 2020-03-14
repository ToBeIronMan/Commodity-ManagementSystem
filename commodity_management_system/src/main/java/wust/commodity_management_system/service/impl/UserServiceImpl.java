package wust.commodity_management_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wust.commodity_management_system.dao.SuperUserMapper;
import wust.commodity_management_system.dao.UserMapper;
import wust.commodity_management_system.listener.SessionListener;
import wust.commodity_management_system.model.SuperUser;
import wust.commodity_management_system.model.User;
import wust.commodity_management_system.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lucky
 */
@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper ;
    @Autowired
    private SuperUserMapper superUserMapper;
    @Override
    public Long sletectIdByUserName(String username) {
        return userMapper.sletectIdByUserName(username);
    }

    @Override
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, HttpSession session) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username).eq("user_password", password);
        QueryWrapper<SuperUser> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("super_user_name", username).eq("super_user_password", password);
        User user = (User)userMapper.selectOne(queryWrapper);
        SuperUser superUser = (SuperUser)superUserMapper.selectOne(queryWrapper1);
        session = request.getSession();
        if(user!=null||superUser!=null){
            if(user==null) {
                session.setAttribute("superUser", superUser);
            }else {
                boolean hasLogin = SessionListener.checkIfHasLogin(user);
                if(!hasLogin) {
                    // 手动设置session的有效期为分钟
                    String sessionId = session.getId();
                    Cookie cookie = new Cookie("JSESSIONID", sessionId);
                    cookie.setMaxAge(60 * 30);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                    // 比较保存所有用户session的静态变量中，是否含有当前session的键值映射，如果含有就删除
                    if (SessionListener.containsKey(sessionId)) {
                        SessionListener.removeSession(sessionId);
                    }
                    // 把当前用户封装的session按sessionID和session进行键值封装，添加到静态变量map中。
                    SessionListener.addUserSession(session);
                    // 如果没有重复登录，则将该登录的用户信息添加入admin中
                    session.setAttribute("user", user);
                }else {
                    return "login";
                }
            }
            return "redirect:/index";
        }else{
            return "login";
        }
    }
}
