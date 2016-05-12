package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.entity.ReturnDtl;
import com.l1.entity.Sku;
import com.l1.service.BillStatService;
import com.l1.service.ReturnDtlService;
import com.l1.service.SkuService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/returnDtl")
public class ReturnDtlController {
    @Resource
    private ReturnDtlService returnDtlService;

    @Resource
    private BillStatService billStatService;

    @Resource
    private SkuService skuService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, ReturnDtl s_returnDtl,
                                    HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", s_returnDtl.getId());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<ReturnDtl> returnDtlList = returnDtlService.find(map);
        Long total = returnDtlService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", returnDtlList);
        result.put("total", total);

        return result;
    }

    @RequestMapping("/findAllById")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "id", required = false) String id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        List<ReturnDtl> linkManList = returnDtlService.find(map);
        Map<String, Object> result = new HashMap<String, Object>();
        //TODO        jsonConfig.setExcludes(new String[]{"customer"});


        Long total = returnDtlService.getTotal(map);
        result.put("rows", linkManList);
        result.put("total", total);
        return result;
    }

    @RequestMapping(value = "/save11", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(ReturnDtl returnDtl) {
        if (returnDtl != null && returnDtl.getStat() > 0) {
            BillStat billStat = billStatService.findById(returnDtl.getStat());
            if (billStat != null) {
                returnDtl.setStatName(billStat.getName());
            }
        }

        returnDtlService.add(returnDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(ReturnDtl returnDtl) {

        if (returnDtl != null && returnDtl.getStat() > 0) {
            BillStat billStat = billStatService.findById(returnDtl.getStat());
            if (billStat != null) {
                returnDtl.setStatName(billStat.getName());
            }
        }

        if (returnDtl != null && returnDtl.getSkuId() > 0) {
            Sku sku = skuService.findById(returnDtl.getSkuId());
            if (sku != null) {
                returnDtl.setItemName(sku.getItemName());
            }
        }

        returnDtlService.update(returnDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") String[] ids) {
        returnDtlService.delete(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public ReturnDtl findById(@RequestParam("id") int id) {
        ReturnDtl returnDtl = returnDtlService.findById(id);
        return returnDtl;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(ReturnDtl returnDtl, @RequestParam("id") int id) throws Exception {
        returnDtl.setId(id);
        if (returnDtl != null && returnDtl.getStat() > 0) {
            BillStat billStat = billStatService.findById(returnDtl.getStat());
            if (billStat != null) {
                returnDtl.setStatName(billStat.getName());
            }
        }

        if (returnDtl != null && returnDtl.getSkuId() > 0) {
            Sku sku = skuService.findById(returnDtl.getSkuId());
            if (sku != null) {
                returnDtl.setItemName(sku.getItemName());
            }
        }

        int resultTotal = 0; // 操作的记录条数
        if (returnDtl.getDtlId() == null) {
            resultTotal = returnDtlService.add(returnDtl);
        } else {
            resultTotal = returnDtlService.update(returnDtl);
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
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            returnDtlService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
