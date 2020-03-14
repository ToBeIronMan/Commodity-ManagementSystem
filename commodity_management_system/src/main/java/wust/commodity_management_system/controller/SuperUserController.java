package wust.commodity_management_system.controller;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wust.commodity_management_system.dao.PermissionMapper;
import wust.commodity_management_system.dao.UserMapper;
import wust.commodity_management_system.model.Permission;
import wust.commodity_management_system.model.User;
import wust.commodity_management_system.service.SuperUserService;
import wust.commodity_management_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lucky
 */
@Controller
public class SuperUserController {

    @Autowired
    private SuperUserService superUserService ;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @RequestMapping("/login")
    public String login(HttpServletResponse response, HttpServletRequest request, Model model, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("123");
        System.out.println();
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            User user = superUserService.getUserByUsernameAndPassword(username, password);
            request.getSession().setAttribute("userName", username);
            request.getSession().setAttribute(request.getSession().getId(), username);
            request.getSession().setAttribute("issearch", "no");
          return  userService.login(request, response, username, password, session);
            //return "redirect:/index";
        }catch (UnknownAccountException e){
            //用户民不存在
            model.addAttribute("msg","用户名不存在");
            e.printStackTrace();
            return "login";
        }catch (IncorrectCredentialsException e)
        {
            model.addAttribute("msg","密码错误");
            return "login";
        }






    }


        @RequestMapping("/userlist")
    public String getUserList(Model model, PageInfo pageInfo, HttpServletResponse response, HttpServletRequest request) {

        int pageNum = (pageInfo.getPageNum() == 0) ? 1 : pageInfo.getPageNum();
        int pageSize = (pageInfo.getPageSize() == 0) ? 6 : pageInfo.getPageSize();
        request.getSession().setAttribute("issearch", "no");
        PageInfo<User> result = superUserService.selectAll(pageNum, pageSize);
        String  username = (String) request.getSession().getAttribute("userName");
        model.addAttribute("permission", new Permission());
        model.addAttribute("userName", username);
        model.addAttribute("users", result.getList());
        model.addAttribute("pageInfo", result);
        return "userlist";
    }

    @RequestMapping("/listorsearch")
    public String Listorsearch(Model model, PageInfo pageInfo, HttpServletResponse response, HttpServletRequest request) {

        String key = (String) request.getSession().getAttribute("issearch");
        if (key.equals("yes")) {
            int pageNum = (pageInfo.getPageNum() == 0) ? 1 : pageInfo.getPageNum();
            int pageSize = (pageInfo.getPageSize() == 0) ? 6: pageInfo.getPageSize();
            String searchwords = (String) request.getSession().getAttribute("searchwords");
            request.getSession().setAttribute("searchwords", searchwords);
            request.getSession().setAttribute("issearch", "yes");

            PageInfo<User> result = superUserService.searchuser(pageNum, pageSize, searchwords);
            String  username = (String) request.getSession().getAttribute("userName");
            model.addAttribute("userName", username);
            model.addAttribute("users", result.getList());
            model.addAttribute("pageInfo", result);
            return "userlist";
        } else {
            int pageNum = (pageInfo.getPageNum() == 0) ? 1 : pageInfo.getPageNum();
            int pageSize = (pageInfo.getPageSize() == 0) ? 6: pageInfo.getPageSize();
            request.getSession().setAttribute("issearch", "no");
            PageInfo<User> result = superUserService.selectAll(pageNum, pageSize);
            String  username = (String) request.getSession().getAttribute("userName");
            model.addAttribute("userName", username);
            model.addAttribute("users", result.getList());
            model.addAttribute("pageInfo", result);
            return "userlist";
        }

    }

    @RequestMapping("/searchuser")
    public String searchuser(Model model, PageInfo pageInfo, HttpServletResponse response, HttpServletRequest request) {

        int pageNum = (pageInfo.getPageNum() == 0) ? 1 : pageInfo.getPageNum();
        int pageSize = (pageInfo.getPageSize() == 0) ? 6 : pageInfo.getPageSize();
        request.getSession().setAttribute("searchwords", request.getParameter("searchwords"));
        request.getSession().setAttribute("issearch", "yes");
        String searchwords = request.getParameter("searchwords");
        if (searchwords == null) {
            searchwords = (String) request.getSession().getAttribute("searchwords");
        }
        PageInfo<User> result = superUserService.searchuser(pageNum, pageSize, searchwords);
        String user = (String) request.getSession().getAttribute("userName");
        model.addAttribute("user", user);
        model.addAttribute("users", result.getList());
        model.addAttribute("pageInfo", result);
        return "userlist";

    }

    @RequestMapping("/userdelete")
    public String userdalete(Model model, HttpServletResponse response, HttpServletRequest request) {
        String aid = request.getParameter("id");
        int id = Integer.parseInt(aid);
        superUserService.deleteuser(id);
        return "redirect:/userlist";

    }
    @RequestMapping("/showPermission")
    public String showPermission(String id,Model model)
    {
        int userId = Integer.parseInt(id);
        Permission permission=permissionMapper.selectById(userId);
        model.addAttribute("permission",permission);
 return "success";
    }
    @RequestMapping("/updateuser")
    public String updateuser(Model model, HttpServletResponse response, HttpServletRequest request) {
        String id = request.getParameter("updateid");
        String username = request.getParameter("updateusername");
        String password = request.getParameter("updatepassword");
        String phone = request.getParameter("updateuserphone");

        User user = new User();

        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserId((long) Integer.parseInt(id));
        user.setUserPhone(phone);
        userMapper.updateById(user);
        return "redirect:/userlist";

    }
    @RequestMapping("/updatePermission")
    public String updateuserPermission(Model model, HttpServletResponse response, HttpServletRequest request) {
        String id = request.getParameter("permission_id");
        String look = request.getParameter("permission_look");
        String add= request.getParameter("permission_update");
        String delete = request.getParameter("permission_delete");
        String update = request.getParameter("permission_update");
        Permission permission=new Permission();
        permission.setUserId((long) Integer.parseInt(id));
        permission.setLookPermission((long) Integer.parseInt(look));
        permission.setDeletePermission((long) Integer.parseInt(delete));
        permission.setUpdatePermission((long) Integer.parseInt(update));
        permission.setAddPermission((long) Integer.parseInt(add));
        permissionMapper.updateById(permission);
        return "redirect:/userlist";

    }
    @RequestMapping("/adduser")
    public String adduser(Model model, HttpServletResponse response, HttpServletRequest request) {

        String username = request.getParameter("addusername");
        String password = request.getParameter("addpassword");
        String phone = request.getParameter("addphone");

        User user =new User();

        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserPhone(phone);
        userMapper.insert(user);

        return "redirect:/userlist";

    }
    @RequestMapping("/tologin")

    public String toLogin(){
        System.out.println();
        return "login";
    }
    @RequestMapping("/noAuth")

    public String noAuth(){
        System.out.println();
        return "noAuth";
    }
}
