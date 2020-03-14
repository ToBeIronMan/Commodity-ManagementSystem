package wust.commodity_management_system.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wust.commodity_management_system.model.Permission;
import wust.commodity_management_system.model.SuperUser;
import wust.commodity_management_system.model.User;
import wust.commodity_management_system.service.PermissionService;
import wust.commodity_management_system.service.SuperUserService;
import wust.commodity_management_system.service.UserService;
import wust.commodity_management_system.util.SmsUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller

public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    SuperUserService superUserService;



    @RequestMapping("/regist")
    public String regist(String username, String password, String phone, String vcode, HttpSession session){
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserPhone(phone);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        QueryWrapper<SuperUser> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("super_user_name", user.getUserName());
        //用户名是否已存在，存在返回"msg"
        if(userService.getOne(queryWrapper)!=null||superUserService.getOne(queryWrapper1)!=null){
            return "msg";
        }
        //验证码是否正确
        if(!vcode.equals(session.getAttribute("vcode"))){
            return "msg1";
        }
        if(userService.save(user)){
            Permission permission = new Permission(userService.sletectIdByUserName(username),0L,0L,0L,0L);

            permissionService.save(permission);
            return "login";
        }else {
            return "register";
        }
    }
    @RequestMapping("logout")
    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        session = request.getSession(false);
        if (session == null) {
            // 没登录，重定向到首页
            String url = response.encodeRedirectURL(request.getContextPath());
            response.sendRedirect(url);
        }else {
            // 从session中移除登录状态
            session.removeAttribute("user");
            String url = response.encodeRedirectURL(request.getContextPath());
            response.sendRedirect(url);
        }
    }
    @RequestMapping("/toregist")

    public String toLogin(){
        System.out.println();
        return "register";
    }
    /**
     *获取验证码
     */
    @PostMapping("/getVcode")
    @ResponseBody
    public String getVcode(String phone, HttpSession session) throws ClientException {
        String vcode = SmsUtil.vcode();
        SendSmsResponse response = SmsUtil.sendSms(vcode, phone);
        if(response.getCode() != null && "OK".equals(response.getCode())) {
            //将vcode存入session
            session.setAttribute("vcode", vcode);
            return "true";
        }else {
            return "false";
        }
    }
}
