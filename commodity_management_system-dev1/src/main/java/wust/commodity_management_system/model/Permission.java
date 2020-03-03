package wust.commodity_management_system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lucky
 */
@Data
@NoArgsConstructor
@TableName("permission")
public class Permission {
    @TableId(type = IdType.AUTO)
    private Long id; //id
    private Long userId;//User表的id
    private Long addPermission;//添加权限，为0无此权限。1由此权限
    private Long deletePermission;//删除权限
    private Long updatePermission;//更新权限


}
