package wust.commodity_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /**
     * 置信度
     */
    private double score;
    /**
     * 识别结果类型
     */
    private String root;
    /**
     * 百科词条
     */
    private Baike_info baike_info;
    /**
     * 物体名称
     */
    private String keyword;
}
