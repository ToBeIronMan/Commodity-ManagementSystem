package wust.commodity_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Baike_info {
    private String baike_url;
    private String image_url;
    private String description;
}
