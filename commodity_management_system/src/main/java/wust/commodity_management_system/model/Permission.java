package wust.commodity_management_system.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lucky
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_permission")
public class Permission {
    @TableId
    private Long userId;//User表的id
    private Long addPermission;//添加权限，为0无此权限。1由此权限
    private Long deletePermission;//删除权限
    private Long updatePermission;//更新权限
    private Long lookPermission;//查看权限


}
