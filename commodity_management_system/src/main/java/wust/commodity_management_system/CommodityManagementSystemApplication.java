package wust.commodity_management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})

@MapperScan("wust.commodity_management_system.dao")
public class CommodityManagementSystemApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(CommodityManagementSystemApplication.class, args);
    }

}
