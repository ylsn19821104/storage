package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.entity.Warehouse;
import com.l1.entity.Ycrk;
import com.l1.service.BillStatService;
import com.l1.service.WarehouseService;
import com.l1.service.YcrkService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
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
@RequestMapping("/ycrk")
public class YcrkController {
    @Resource
    private YcrkService ycrkService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private BillStatService billStatService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping
    public String showPage() {
        return "ycrkManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Ycrk s_ycrk
    ) throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("billNo", StringUtil.formatLike(s_ycrk.getBillNo()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Ycrk> ycrkList = ycrkService.find(map);
        Long total = ycrkService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", ycrkList);
        result.put("total", total);


        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Ycrk ycrk) {
        ycrk.setCreate_time(DateUtil.now());
        ycrkService.save(ycrk);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Ycrk ycrk) {
        Integer warehouseId = ycrk.getOutWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                ycrk.setOutWarehouseName(warehouse.getName());
            }
        }

        Integer inWarehouseId = ycrk.getInWarehouseId();
        if (inWarehouseId != null && inWarehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(inWarehouseId);
            if (warehouse != null) {
                ycrk.setInWarehouseName(warehouse.getName());
            }
        }

        if (ycrk.getStat() > 0) {
            BillStat billStat = billStatService.findById(ycrk.getStat());
            if (billStat != null) {
                ycrk.setStatName(billStat.getName());
            }
        }

        ycrk.setUpdate_time(DateUtil.now());
        ycrkService.update(ycrk);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    //TODO ?
//  @RequestMapping("/save11")
//  @ResponseBody
//  public String save(Ycrk ycrk) throws Exception {
//    Integer warehouseId = ycrk.getOutWarehouseId();
//    if (warehouseId != null && warehouseId > 0) {
//      Warehouse warehouse = warehouseService.findById(warehouseId);
//      if (warehouse != null) {
//        ycrk.setOutWarehouseName(warehouse.getName());
//      }
//    }
//
//    Integer inWarehouseId = ycrk.getInWarehouseId();
//    if (inWarehouseId != null && inWarehouseId > 0) {
//      Warehouse warehouse = warehouseService.findById(inWarehouseId);
//      if (warehouse != null) {
//        ycrk.setInWarehouseName(warehouse.getName());
//      }
//    }
//
//    Integer statId = ycrk.getStat();
//    if (statId != null && statId > 0) {
//      BillStat billStat = billStatService.findById(statId);
//      if (billStat != null) {
//        ycrk.setStatName(billStat.getName());
//      }
//    }
//
//    int resultTotal = 0; // 操作的记录条数
//    if (ycrk.getId() == null) {
//      ycrk.setCreate_time(DateUtil.now());
//      resultTotal = ycrkService.add(ycrk);
//    } else {
//      ycrk.setUpdate_time(DateUtil.now());
//      resultTotal = ycrkService.update(ycrk);
//    }
//
//    Map<String, Object> result = new HashMap<String, Object>();
//    if (resultTotal > 0) {
//      result.put("success", true);
//    } else {
//      result.put("success", false);
//    }
//
//
//    return ;
//  }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids)
            throws Exception {
        if (ids != null && ids.length > 0) {
            ycrkService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);

        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Ycrk findById(@RequestParam(value = "id") Integer id)
            throws Exception {
        Ycrk ycrk = ycrkService.findById(id);
        return ycrk;
    }

}
