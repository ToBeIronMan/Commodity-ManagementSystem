package wust.commodity_management_system.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wust.commodity_management_system.dao.CommodityMapper;
import wust.commodity_management_system.model.ImageRecognitionResult;
import wust.commodity_management_system.util.image_orc.BaiduImgApi;

@Controller
@RequestMapping("/ImageOrc")
public class ImageOrcController {
    @Autowired
    CommodityMapper commodityMapper;
    
    /**
     * 测试控制器
     *
     * @param : 
     * @return java.lang.String: 
     * @author haoran.xiao
     * @date 2020/3/8 17:57
     */
    @RequestMapping(value = "/index")
    public String home() {
        return "index";
    }

    /**
     * 图像识别,得到识别信息
     *
     * @param bases: 
	 * @param name: 
	 * @param model: 
     * @return java.lang.String: 
     * @author haoran.xiao
     * @date 2020/3/8 17:05
     */
    @RequestMapping("/getTextByImage")
    public String getTextByImage(String bases, String name, Model model){
        System.out.println("name:"+name);

        String json= BaiduImgApi.advancedGeneralCore(bases);
        ImageRecognitionResult imageRecognitionResult=JSON.parseObject(json, ImageRecognitionResult.class);
        model.addAttribute("imageRecognitionResult",imageRecognitionResult);
        return "imageDate";
    }

    /**
     * 根据图片搜索得到商品
     *
     * @param bases: 
	 * @param name: 
	 * @param model: 
     * @return java.lang.String: 
     * @author haoran.xiao
     * @date 2020/3/8 17:16
     */
//    @RequestMapping("/searchByImage"  )
//    public String searchByImage(String bases,String name,Model model){
//        System.out.println("name:"+name);
//
//        String json=BaiduImgApi.advancedGeneralCore(bases);
//        ImageRecognitionResult imageRecognitionResult=JSON.parseObject(json, ImageRecognitionResult.class);
//        List<String> list=imageRecognitionResult.getResult().stream().map(Result::getKeyword).collect(Collectors.toList());
//        CommodityPOJO commodityPOJO=new CommodityPOJO();
//        commodityPOJO.setNames(list);
//        IPage<Commodity> iPage=commodityMapper.selectCommoditysByCommodityPOJO(commodityPOJO,new Page<>(1, 5));
//        model.addAttribute("iPage",iPage);
//        return "commodityDate";
//    }


}
