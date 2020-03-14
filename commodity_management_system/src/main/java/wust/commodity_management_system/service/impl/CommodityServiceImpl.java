package wust.commodity_management_system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wust.commodity_management_system.dao.CommodityMapper;
import wust.commodity_management_system.model.Commodity;
import wust.commodity_management_system.model.Image;
import wust.commodity_management_system.pojo.CommodityPOJO;
import wust.commodity_management_system.service.CommodityService;
import wust.commodity_management_system.util.FileUtil;
import wust.commodity_management_system.utilBean.DefaultSet;

import java.util.List;


/**
 * @author lucky
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public void insertCommodity(Commodity commodity, Image image) {
        commodityMapper.insert(commodity);
        FileUtil.GenerateImage(image);
    }

    /**
     * 插入商品
     *
     * @param commodityPOJO:
     * @return void:
     * @author haoran.xiao
     * @date 2020/3/8 17:21
     */
    @Override
    public void insertCommodity(CommodityPOJO commodityPOJO) {
        commodityPOJO.setImageAddress(DefaultSet.relativePath +commodityPOJO.getImageName());
        commodityMapper.insert(commodityPOJO);
        FileUtil.GenerateImage(commodityPOJO.getImageStr(), DefaultSet.absolutePath,commodityPOJO.getImageName());
    }

    @Override
    public void updateCommodity(Commodity commodity, Image image) {
        String path=commodityMapper.selectById(commodity.getNumber()).getImageAddress();
        commodityMapper.updateById(commodity);
        FileUtil.GenerateImage(image.getBase64ImgStr(), path);
    }

    /**
     * 更新商品
     *
     * @param commodityPOJO:
     * @return void:
     * @author haoran.xiao
     * @date 2020/3/8 17:21
     */
    @Override
    public void updateCommodity(CommodityPOJO commodityPOJO) {
        String path= DefaultSet.rootPath+commodityMapper.selectById(commodityPOJO.getNumber()).getImageAddress();
        commodityMapper.updateById(commodityPOJO);
        FileUtil.GenerateImage(commodityPOJO.getImageStr(), path);
    }

    /**
     * 删除商品
     *
     * @param commodity: 
     * @return void: 
     * @author haoran.xiao
     * @date 2020/3/8 17:17
     */
    @Override
    public void deleteCommodity(Commodity commodity) {
        long num=commodity.getNumber();
        if(commodityMapper==null||commodityMapper.selectById(num)==null){
            return;
        }
        String path="E:"+commodityMapper.selectById(num).getImageAddress();
        FileUtil.deleteFile(path);
        commodityMapper.deleteById(commodity.getNumber());

        /*Map<String,Object> map=new HashMap<>();
        map.put("commodity_id",commodity.getNumber());
        List<UserCommodity> userCommodities = userCommodityMapper.selectByMap(map);
        List<Long> list=userCommodities.stream().map(UserCommodity::getId).collect(Collectors.toList());
        if(list!=null&&list.size()>0){
            userCommodityMapper.deleteBatchIds(list);
        }*/

    }

    @Override
    public PageInfo<Commodity> searchCommodity(int pageNum, int pageSize, String searchwords) {
        PageHelper.startPage(pageNum, pageSize);


        if(searchwords!=null){
            searchwords="%"+searchwords+"%";
            List<Commodity>  users = commodityMapper.searchCommodity(searchwords);
            PageInfo<Commodity> pageInfo = new PageInfo<>(users);
            return pageInfo;
        }
        else {
            List<Commodity>  users = commodityMapper.selectAll();
            PageInfo<Commodity> pageInfo = new PageInfo<>(users);
            return pageInfo;
        }



    }
}
