package wust.commodity_management_system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("super_user")
public class SuperUser {
    @TableId(type = IdType.AUTO)
    private Long SuperUserId ;
    /*用户id*/
    private String SuperUserName;
    /*用户姓名*/
    private String SuperUserPassword;
    /*用户密码*/
    private String SuperUserPhone;
    /*用户手机号*/
    private String SuperUserHeadImageAdress;
    /*用户头像地址*/
}
