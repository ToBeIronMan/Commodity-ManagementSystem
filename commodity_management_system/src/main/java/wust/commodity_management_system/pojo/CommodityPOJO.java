package wust.commodity_management_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wust.commodity_management_system.model.Commodity;
import wust.commodity_management_system.model.Image;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityPOJO extends Commodity {
    private List<String> names;
    private List<String> types;
    private Long quantityMin;
    private Long quantityMax;
    private Double priceMin;
    private Double priceMax;
    private Image image;
    private String imageStr;
    private String imageName;
    private String imagePath;
}
