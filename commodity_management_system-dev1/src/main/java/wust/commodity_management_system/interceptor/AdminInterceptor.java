package wust.commodity_management_system.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import wust.commodity_management_system.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登陆拦截器
 */
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("--拦截器---");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return true;
        }
        //强制跳转到登陆页面...
        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }
}
