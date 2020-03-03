package wust.commodity_management_system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Number;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lucky
 */
@Data
@NoArgsConstructor
@TableName("commodity")
public class Commodity {
    private Long number;
    private String name;
    private String type;
    private String price;
    private Long  quantity;
    private String imageAdress;

}
