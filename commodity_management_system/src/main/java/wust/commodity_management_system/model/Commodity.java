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
@TableName("commodity")
public class Commodity {
    @TableId("number")
    private Long number;
    private String name;
    private String type;
    private String price;
    private Long  quantity;
    private String imageAddress;

}
