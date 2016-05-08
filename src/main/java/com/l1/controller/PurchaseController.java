package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.entity.Purchase;
import com.l1.entity.Warehouse;
import com.l1.service.BillStatService;
import com.l1.service.PurchaseService;
import com.l1.service.WarehouseService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
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
@RequestMapping("/purchase")
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private BillStatService billStatService;

    @RequestMapping
    public String showPage() {
        return "purchaseManage";
    }

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
                                    @RequestParam(value = "rows", required = false) String rows, Purchase s_purchase, HttpServletResponse response)
            throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supplierName", StringUtil.formatLike(s_purchase.getSupplierName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Purchase> purchaseList = purchaseService.find(map);
        Long total = purchaseService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", purchaseList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Purchase purchase) {
        Integer warehouseId = purchase.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                purchase.setWarehouseName(warehouse.getName());
            }
        }

        if (purchase.getStat() > 0) {
            BillStat billStat = billStatService.findById(purchase.getStat());
            if (billStat != null) {
                purchase.setStatName(billStat.getName());
            }
        }

        purchase.setCreate_time(DateUtil.now());
        purchaseService.save(purchase);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Purchase purchase) {
        Integer warehouseId = purchase.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                purchase.setWarehouseName(warehouse.getName());
            }
        }

        if (purchase.getStat() > 0) {
            BillStat billStat = billStatService.findById(purchase.getStat());
            if (billStat != null) {
                purchase.setStatName(billStat.getName());
            }
        }

        purchase.setUpdate_time(DateUtil.now());
        purchaseService.update(purchase);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping("/save11")
    @ResponseBody
    public Map<String, Object> save(Purchase purchase, HttpServletResponse response) throws Exception {
        Integer warehouseId = purchase.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                purchase.setWarehouseName(warehouse.getName());
            }
        }

        Integer statId = purchase.getStat();
        if (statId != null && statId > 0) {
            BillStat billStat = billStatService.findById(statId);
            if (billStat != null) {
                purchase.setStatName(billStat.getName());
            }
        }

        int resultTotal = 0; // 操作的记录条数
        if (purchase.getId() == null) {
            purchase.setCreate_time(DateUtil.now());
            resultTotal = purchaseService.add(purchase);
        } else {
            purchase.setUpdate_time(DateUtil.now());
            resultTotal = purchaseService.update(purchase);
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
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids, HttpServletResponse response) throws Exception {
        if (ids != null && ids.length > 0) {
            purchaseService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Purchase findById(@RequestParam(value = "id") Integer id, HttpServletResponse response) throws Exception {
        Purchase purchase = purchaseService.findById(id);
        return purchase;
    }

}
