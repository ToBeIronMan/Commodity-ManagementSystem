package wust.commodity_management_system.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wust.commodity_management_system.dao.CommodityMapper;
import wust.commodity_management_system.model.Commodity;
import wust.commodity_management_system.pojo.CommodityPOJO;
import wust.commodity_management_system.service.CommodityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @Autowired
    CommodityMapper commodityMapper;

    /**
     * 删除商品,跳转到主页
     *
     * @param id:
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/7 23:13
     */
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        try{
            Commodity commodity=new Commodity();
            commodity.setNumber(id);
            commodityService.deleteCommodity(commodity);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/index";
    }

    /**
     * 根据商品名搜索,返回商品数据页,前端异步加载
     *
     * @param search:
	 * @param model:
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/7 23:12
     */
    @RequestMapping(value = "/search")
    public String search(Model model,HttpServletResponse response, HttpServletRequest request) {
        String searchwords = request.getParameter("searchwords");
        PageInfo<Commodity> result=commodityService.searchCommodity(1,4,searchwords);
        request.getSession().setAttribute("searchwords", searchwords);
        request.getSession().setAttribute("issearch", "yes");
        model.addAttribute("result",result.getList());
        model.addAttribute("pageInfo", result);
        return "index";
    }
    @RequestMapping("/listorsearch2")
    public String Listorsearch(Model model, PageInfo pageInfo, HttpServletResponse response, HttpServletRequest request) {

        String key = (String) request.getSession().getAttribute("issearch");
        if ("yes".equals(key)) {
            int pageNum = (pageInfo.getPageNum() == 0) ? 1 : pageInfo.getPageNum();
            int pageSize = (pageInfo.getPageSize() == 0) ? 4 : pageInfo.getPageSize();
            String searchwords = (String) request.getSession().getAttribute("searchwords");
            request.getSession().setAttribute("searchwords", searchwords);
            request.getSession().setAttribute("issearch", "yes");

            PageInfo<Commodity> result = commodityService.searchCommodity(pageNum, pageSize, searchwords);

            model.addAttribute("result", result.getList());
            model.addAttribute("pageInfo", result);
            return "index";
        } else {
            int pageNum = (pageInfo.getPageNum() == 0) ? 1 : pageInfo.getPageNum();
            int pageSize = (pageInfo.getPageSize() == 0) ? 4: pageInfo.getPageSize();
            request.getSession().setAttribute("issearch", "no");
            PageInfo<Commodity> result = commodityService.searchCommodity(pageNum, pageSize, null);
            request.getSession().setAttribute("pageInfo", result);
            model.addAttribute("result", result.getList());
            model.addAttribute("pageInfo", result);
            return "index";
        }
    }
    /**
     * 更新商品所有信息
     *
     * @param commodityPOJO:
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/7 23:14
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(CommodityPOJO commodityPOJO) {
        commodityService.updateCommodity(commodityPOJO);
        return commodityPOJO.getNumber().toString();
    }

    /**
     * 新增商品,跳转到主页
     *
     * @param commodityPOJO:
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/7 23:14
     */
    @RequestMapping(value = "/insert")
    public String insert(CommodityPOJO commodityPOJO) {
        commodityService.insertCommodity(commodityPOJO);
        return "redirect:/index";
    }

    /**
     * 查询商品详细信息,跳转到详情页
     *
     * @param id:
	 * @param model:
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/7 23:15
     */
    @RequestMapping(value = "/selectOne/{id}")
    public String selectOne(@PathVariable("id") Long id,Model model) {
       model.addAttribute("commodity", commodityService.getById(id));
        return "details";
    }

    /**
     * 根据任意条件查询商品,用作主页展示数据,返回商品数据页,前端异步加载
     *
     * @param model:
	 * @param page:
	 * @param size:
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/7 23:17
     */
    @RequestMapping(value = "/list")
    public String commodityList(Model model, PageInfo pageInfo, HttpServletResponse response, HttpServletRequest request) {
        int pageNum = (pageInfo.getPageNum() == 0) ? 1 : pageInfo.getPageNum();
        int pageSize = (pageInfo.getPageSize() == 0) ? 4 : pageInfo.getPageSize();
        request.getSession().setAttribute("issearch", "no");
        PageInfo<Commodity> result=commodityService.searchCommodity(pageNum,pageSize,null);
        model.addAttribute("result",result.getList());
        model.addAttribute("pageInfo", result);
        return "index";
    }

    /**
     * 控制器跳转到主页
     *
     * @param :
     * @return java.lang.String:
     * @author haoran.xiao
     * @date 2020/3/7 23:23
     */
    @RequestMapping(value = "/index")
    public String index(Model model,HttpServletResponse response, HttpServletRequest request) {
        PageInfo<Commodity> result = commodityService.searchCommodity(1, 4, null);
       model.addAttribute("result",result.getList());
        model.addAttribute("pageInfo", result);
        return "index";
    }



}
