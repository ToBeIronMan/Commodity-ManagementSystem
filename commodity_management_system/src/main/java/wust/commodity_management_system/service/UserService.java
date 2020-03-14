package wust.commodity_management_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wust.commodity_management_system.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService  extends IService<User> {
    Long sletectIdByUserName(String username);
    String login(HttpServletRequest request, HttpServletResponse response, String username, String password, HttpSession session);
}
