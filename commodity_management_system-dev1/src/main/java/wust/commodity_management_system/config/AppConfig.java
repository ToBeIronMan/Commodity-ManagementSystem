package wust.commodity_management_system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wust.commodity_management_system.interceptor.AdminInterceptor;

/**
 * Springboot配置类
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    /**
     * 配置默认跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/", "/user/login","/assets/**");
    }
}
