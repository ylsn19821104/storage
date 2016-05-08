package com.l1.controller;

import com.l1.entity.Brand;
import com.l1.entity.PageBean;
import com.l1.service.BrandService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hongxp 2016年4月12日
 */

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    @RequestMapping
    public String showPage() {
        return "brandManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Brand s_brand
    ) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_brand.getName()));
        map.put("code", StringUtil.formatLike(s_brand.getCode()));
        map.put("stat", "使用");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Brand> brandList = brandService.find(map);
        Long total = brandService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", brandList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Brand brand) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (brand.getId() == null) {
            brand.setCreate_time(DateUtil.now());
            brand.setStat("使用");
            resultTotal = brandService.add(brand);
        } else {
            brand.setUpdate_time(DateUtil.now());
            resultTotal = brandService.update(brand);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids)
            throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            brandService.delete(Integer.parseInt(idsStr[i]));
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        return result;
    }


    @RequestMapping("/itemManagerComboList")
    @ResponseBody
    public List<Brand> itemManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "使用");
        List<Brand> brandList = brandService.find(map);
        return brandList;
    }

}
