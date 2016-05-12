package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.Kctz;
import com.l1.entity.PageBean;
import com.l1.entity.Warehouse;
import com.l1.service.BillStatService;
import com.l1.service.KctzService;
import com.l1.service.WarehouseService;
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
@RequestMapping("/kctz")
public class KctzController {
    @Resource
    private KctzService kctzService;

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
        return "kctzManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Kctz s_kctz)
            throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("warehouseName", StringUtil.formatLike(s_kctz.getWarehouseName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Kctz> kctzList = kctzService.find(map);
        Long total = kctzService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", kctzList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Kctz kctz) {
        kctz.setCreate_time(DateUtil.now());
        kctzService.save(kctz);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Kctz kctz) {
        Integer warehouseId = kctz.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                kctz.setWarehouseName(warehouse.getName());
            }
        }

        if (kctz.getStat() > 0) {
            BillStat billStat = billStatService.findById(kctz.getStat());
            if (billStat != null) {
                kctz.setStatName(billStat.getName());
            }
        }

        kctz.setUpdate_time(DateUtil.now());
        kctzService.update(kctz);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    //TODO save1?
//    @RequestMapping("/save11")
//    @ResponseBody
//    public Map<String, Object> save(Kctz kctz) throws Exception {
//        Integer warehouseId = kctz.getWarehouseId();
//        if (warehouseId != null && warehouseId > 0) {
//            Warehouse warehouse = warehouseService.findById(warehouseId);
//            if (warehouse != null) {
//                kctz.setWarehouseName(warehouse.getName());
//            }
//        }
//
//        Integer statId = kctz.getStat();
//        if (statId != null && statId > 0) {
//            BillStat billStat = billStatService.findById(statId);
//            if (billStat != null) {
//                kctz.setStatName(billStat.getName());
//            }
//        }
//
//        int resultTotal = 0; // 操作的记录条数
//        if (kctz.getId() == null) {
//            kctz.setCreate_time(DateUtil.now());
//            resultTotal = kctzService.add(kctz);
//        } else {
//            kctz.setUpdate_time(DateUtil.now());
//            resultTotal = kctzService.update(kctz);
//        }
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        if (resultTotal > 0) {
//            result.put("success", true);
//        } else {
//            result.put("success", false);
//        }
//
//        return result;
//    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            kctzService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Kctz findById(@RequestParam(value = "id") Integer id) throws Exception {
        Kctz kctz = kctzService.findById(id);
        return kctz;
    }

}
