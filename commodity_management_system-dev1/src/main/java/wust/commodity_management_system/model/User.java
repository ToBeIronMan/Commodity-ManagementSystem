package wust.commodity_management_system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;


//super用户和普通用公用一个model

/**
 * @author lucky
 */
@Data
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId ;
    /*用户id*/
    private String userName;
    /*用户姓名*/
    private String userPassword;
    /*用户密码*/
    private String userPhone;
    /*用户手机号*/
    private String userHeadImageAdress;
    /*用户头像地址*/
}
