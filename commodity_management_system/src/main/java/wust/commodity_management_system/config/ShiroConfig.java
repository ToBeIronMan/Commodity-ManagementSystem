package wust.commodity_management_system.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /*
    创建
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager)
    {//摄制安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加内置过滤器
        // anon:无需登陆
        //authc:必须认证
        //perms:必须授权
        //role:必须等到角色授权
        Map<String,String> filterMap=new LinkedHashMap<String,String>();
//        filterMap.put("/add","authc");
//        filterMap.put("/update","authc");

        filterMap.put("/tologin","anon");
        filterMap.put("/login","anon");
        filterMap.put("/getVcode","anon");
        filterMap.put("/regist","anon");
        filterMap.put("/toregist","anon");
        filterMap.put("/updateuser","perms[admin]");
        filterMap.put("/userdelete","perms[admin]");
        filterMap.put("/searchuser","perms[admin]");
        filterMap.put("/listorsearch","perms[admin]");
        filterMap.put("/userlist","perms[admin]");
        filterMap.put("/adduser","perms[admin]");
        filterMap.put("/commodity","authc");
        filterMap.put("/search","perms[look]");
        filterMap.put("/listorsearch2","perms[look]");
        filterMap.put("/delete","perms[delete]");
        filterMap.put("/update","perms[update]");
        filterMap.put("/insert","perms[add]");
        filterMap.put("/selectOne","perms[look]");
        filterMap.put("/index","perms[look]");



        filterMap.put("/*","authc");


        //修改调整的的登陆页面
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //修改未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        //资源放心

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /*
    创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return  securityManager;
    }

/*
*创建Realm
 */
@Bean(name="userRealm")
public UserRealm getRealm()
{
    return new UserRealm();
}
}
