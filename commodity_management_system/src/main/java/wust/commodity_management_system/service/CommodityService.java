package wust.commodity_management_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import wust.commodity_management_system.model.Commodity;
import wust.commodity_management_system.model.Image;
import wust.commodity_management_system.pojo.CommodityPOJO;

public interface CommodityService extends IService<Commodity> {
    public void insertCommodity(Commodity commodity, Image image);
    public void insertCommodity(CommodityPOJO commodityPOJO);
    public void updateCommodity(Commodity commodity, Image image);
    public void updateCommodity(CommodityPOJO commodityPOJO);
    public void deleteCommodity(Commodity commodity);
    PageInfo<Commodity> searchCommodity(int pageNum, int pageSize, String searchwords);
}
