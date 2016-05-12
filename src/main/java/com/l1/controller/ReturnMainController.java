package com.l1.controller;

import com.l1.entity.*;
import com.l1.service.BillStatService;
import com.l1.service.DicService;
import com.l1.service.ReturnMainService;
import com.l1.service.WarehouseService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@RequestMapping("/return")
public class ReturnMainController {
    @Resource
    private ReturnMainService returnMainService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private BillStatService billStatService;

    @Resource
    private DicService dicService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping
    public String showPage(Model model) {
        List<Dic> statusDic = dicService.query("returnStatus");
        model.addAttribute("statusDic",statusDic);
        return "returnManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, ReturnMain s_returnMain)
            throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerName", StringUtil.formatLike(s_returnMain.getCustomerName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<ReturnMain> returnMainList = returnMainService.find(map);
        Long total = returnMainService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", returnMainList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(ReturnMain returnMain, BindingResult err) throws IOException {
        Integer warehouseId = returnMain.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                returnMain.setWarehouseName(warehouse.getName());
            }
        }

        if (returnMain.getStat() > 0) {
            BillStat billStat = billStatService.findById(returnMain.getStat());
            if (billStat != null) {
                returnMain.setStatName(billStat.getName());
            }
        }

        returnMain.setCreate_time(DateUtil.now());
        int count = returnMainService.save(returnMain);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count>0);
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(ReturnMain returnMain) {
        Integer warehouseId = returnMain.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                returnMain.setWarehouseName(warehouse.getName());
            }
        }

        if (returnMain.getStat() > 0) {
            BillStat billStat = billStatService.findById(returnMain.getStat());
            if (billStat != null) {
                returnMain.setStatName(billStat.getName());
            }
        }

        returnMain.setUpdate_time(DateUtil.now());
        returnMainService.update(returnMain);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping("/save11")
    @ResponseBody
    public Map<String, Object> save(ReturnMain returnMain, HttpServletResponse response) throws Exception {
        Integer warehouseId = returnMain.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                returnMain.setWarehouseName(warehouse.getName());
            }
        }

        Integer statId = returnMain.getStat();
        if (statId != null && statId > 0) {
            BillStat billStat = billStatService.findById(statId);
            if (billStat != null) {
                returnMain.setStatName(billStat.getName());
            }
        }

        int resultTotal = 0; // 操作的记录条数
        if (returnMain.getId() == null) {
            returnMain.setCreate_time(DateUtil.now());
            resultTotal = returnMainService.add(returnMain);
        } else {
            returnMain.setUpdate_time(DateUtil.now());
            resultTotal = returnMainService.update(returnMain);
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
            returnMainService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ReturnMain findById(@RequestParam(value = "id") Integer id, HttpServletResponse response) throws Exception {
        ReturnMain returnMain = returnMainService.findById(id);
        return returnMain;
    }

}
