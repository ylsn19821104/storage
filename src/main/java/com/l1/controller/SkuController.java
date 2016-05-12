package com.l1.controller;

import com.l1.entity.PageBean;
import com.l1.entity.Sku;
import com.l1.service.SkuService;
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
@RequestMapping("/sku")
public class SkuController {
    @Resource
    private SkuService skuService;

    @RequestMapping
    public String showPage() {
        return "skuList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Sku s_sku)
            throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemName", StringUtil.formatLike(s_sku.getItemName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Sku> skuList = skuService.find(map);
        Long total = skuService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", skuList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Sku sku) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (sku.getId() == null) {
            resultTotal = skuService.add(sku);
        } else {
            resultTotal = skuService.update(sku);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    @RequestMapping("/comboList")
    @ResponseBody
    public List<Map<String, String>> comboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, String>> skuList = skuService.findForCombo(map);
        return skuList;
    }
    @RequestMapping("/getAvailableSkuInfo")
    @ResponseBody
    public List<Map<String, String>> getAvailableSkuInfo(Integer billId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (billId != null) {
            map.put("billId",billId);
        }
        List<Map<String, String>> skuList = skuService.findForCombo(map);
        return skuList;
    }

    @RequestMapping(value = "findById", method = RequestMethod.GET)
    @ResponseBody
    public Sku findById(Integer id) {
        return skuService.findById(id);
    }
}
