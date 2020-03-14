package wust.commodity_management_system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wust.commodity_management_system.model.Commodity;

import java.util.List;


/**
 * @author lucky
 */
public interface CommodityMapper extends BaseMapper<Commodity> {


    @Select("select * from commodity where name like #{searchwords}")
    List<Commodity> searchCommodity(@Param("searchwords") String searchwords);
    @Select("select * from commodity")
    List<Commodity> selectAll();

//    IPage<Commodity> selectCommoditysByUserCommodityPOJO(UserCommodityPOJO userCommodityPOJO, Page<Commodity> page);
//    IPage<Commodity> selectCommoditysByCommodityPOJO( CommodityPOJO commodityPOJO,Page<Commodity> page);
}
