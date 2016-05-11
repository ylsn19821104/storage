package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.RentDtl;
import com.l1.entity.Sku;
import com.l1.service.BillStatService;
import com.l1.service.RentDtlService;
import com.l1.service.SkuService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/rentDtl")
public class RentDtlController {
    @Resource
    private RentDtlService rentDtlService;

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
                                    @RequestParam(value = "rows", required = false) String rows, @RequestParam(required = true) String id) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("start", page);
        map.put("size", rows);

        List<RentDtl> rentDtlList = rentDtlService.find(map);
        Long total = rentDtlService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", rentDtlList);
        result.put("total", total);

        return result;
    }

    @RequestMapping("/findAllById")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "id", required = false) String id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        List<RentDtl> linkManList = rentDtlService.find(map);
        Map<String, Object> result = new HashMap<String, Object>();

        Long total = rentDtlService.getTotal(map);
        result.put("rows", linkManList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save11", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(RentDtl rentDtl) {
        if (rentDtl != null && rentDtl.getStat() > 0) {
            BillStat billStat = billStatService.findById(rentDtl.getStat());
            if (billStat != null) {
                rentDtl.setStatName(billStat.getName());
            }
        }

        rentDtlService.add(rentDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(RentDtl rentDtl) {

        if (rentDtl != null && rentDtl.getStat() > 0) {
            BillStat billStat = billStatService.findById(rentDtl.getStat());
            if (billStat != null) {
                rentDtl.setStatName(billStat.getName());
            }
        }

        if (rentDtl != null && rentDtl.getSkuId() > 0) {
            Sku sku = skuService.findById(rentDtl.getSkuId());
            if (sku != null) {
                rentDtl.setItemName(sku.getItemName());
            }
        }

        rentDtlService.update(rentDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") Integer[] ids) {
        rentDtlService.delete(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public RentDtl findById(@RequestParam("id") int id) {
        RentDtl rentDtl = rentDtlService.findById(id);
        return rentDtl;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(RentDtl rentDtl, @RequestParam("id") int id)
            throws Exception {
        rentDtl.setId(id);
        if (rentDtl != null && rentDtl.getStat() > 0) {
            BillStat billStat = billStatService.findById(rentDtl.getStat());
            if (billStat != null) {
                rentDtl.setStatName(billStat.getName());
            }
        }

        if (rentDtl != null && rentDtl.getSkuId() > 0) {
            Sku sku = skuService.findById(rentDtl.getSkuId());
            if (sku != null) {
                rentDtl.setItemName(sku.getItemName());
            }
        }

        int resultTotal = 0; // 操作的记录条数
        if (rentDtl.getDtlId() == null) {
            resultTotal = rentDtlService.add(rentDtl);
        } else {
            resultTotal = rentDtlService.update(rentDtl);
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
    public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids)
            throws Exception {
        if (ids != null && ids.length > 0) {
            rentDtlService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
