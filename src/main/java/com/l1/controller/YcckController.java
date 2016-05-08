package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.entity.Warehouse;
import com.l1.entity.Ycck;
import com.l1.service.BillStatService;
import com.l1.service.WarehouseService;
import com.l1.service.YcckService;
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
@RequestMapping("/ycck")
public class YcckController {
    @Resource
    private YcckService ycckService;

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
        return "ycckManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Ycck s_ycck,
                                    HttpServletResponse response) throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("billNo", StringUtil.formatLike(s_ycck.getBillNo()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Ycck> ycckList = ycckService.find(map);
        Long total = ycckService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", ycckList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Ycck ycck) {
        Integer warehouseId = ycck.getOutWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                ycck.setOutWarehouseName(warehouse.getName());
            }
        }

        Integer inWarehouseId = ycck.getInWarehouseId();
        if (inWarehouseId != null && inWarehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(inWarehouseId);
            if (warehouse != null) {
                ycck.setInWarehouseName(warehouse.getName());
            }
        }

        if (ycck.getStat() > 0) {
            BillStat billStat = billStatService.findById(ycck.getStat());
            if (billStat != null) {
                ycck.setStatName(billStat.getName());
            }
        }

        ycck.setCreate_time(DateUtil.now());
        ycckService.save(ycck);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Ycck ycck) {
        Integer warehouseId = ycck.getOutWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                ycck.setOutWarehouseName(warehouse.getName());
            }
        }

        Integer inWarehouseId = ycck.getInWarehouseId();
        if (inWarehouseId != null && inWarehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(inWarehouseId);
            if (warehouse != null) {
                ycck.setInWarehouseName(warehouse.getName());
            }
        }

        if (ycck.getStat() > 0) {
            BillStat billStat = billStatService.findById(ycck.getStat());
            if (billStat != null) {
                ycck.setStatName(billStat.getName());
            }
        }

        ycck.setUpdate_time(DateUtil.now());
        ycckService.update(ycck);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping("/save11")
    @ResponseBody
    public Map<String, Object> save(Ycck ycck, HttpServletResponse response) throws Exception {
        Integer warehouseId = ycck.getOutWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                ycck.setOutWarehouseName(warehouse.getName());
            }
        }

        Integer inWarehouseId = ycck.getInWarehouseId();
        if (inWarehouseId != null && inWarehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(inWarehouseId);
            if (warehouse != null) {
                ycck.setInWarehouseName(warehouse.getName());
            }
        }

        Integer statId = ycck.getStat();
        if (statId != null && statId > 0) {
            BillStat billStat = billStatService.findById(statId);
            if (billStat != null) {
                ycck.setStatName(billStat.getName());
            }
        }

        int resultTotal = 0; // 操作的记录条数
        if (ycck.getId() == null) {
            ycck.setCreate_time(DateUtil.now());
            resultTotal = ycckService.add(ycck);
        } else {
            ycck.setUpdate_time(DateUtil.now());
            resultTotal = ycckService.update(ycck);
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
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids, HttpServletResponse response)
            throws Exception {
        if (ids != null && ids.length > 0) {
            ycckService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Ycck findById(@RequestParam(value = "id") Integer id, HttpServletResponse response)
            throws Exception {
        Ycck ycck = ycckService.findById(id);
        return ycck;
    }

}
