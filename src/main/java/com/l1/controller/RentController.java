package com.l1.controller;

import com.l1.entity.*;
import com.l1.service.BillStatService;
import com.l1.service.RentService;
import com.l1.service.WarehouseService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/rent")
public class RentController {
    @Resource
    private RentService rentService;

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
        return "rentManage";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Rent s_rent)
            throws Exception {
        if (page == null) {
            page = "1";
        }

        if (rows == null) {
            rows = "10";
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerName", StringUtil.formatLike(s_rent.getCustomerName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Rent> rentList = rentService.find(map);
        Long total = rentService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", rentList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Rent rent, String details, HttpServletRequest request) {
        rent.setCreate_time(DateUtil.now());
        int count = rentService.save(rent);
        if (details != null && details.length() > 2) {
            JSONArray array = JSONArray.fromObject(details);
            if (array != null && array.size() > 0) {
                List<RentDetail> list = new LinkedList<RentDetail>();
                for (int i = 0; i < array.size(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    RentDetail detail = new RentDetail();
                    //TODO 获取详细信息
                }
                //TODO写入数据库
            }


        }
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count > 0);
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Rent rent) {
        Integer warehouseId = rent.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                rent.setWarehouseName(warehouse.getName());
            }
        }

        if (rent.getStat() > 0) {
            BillStat billStat = billStatService.findById(rent.getStat());
            if (billStat != null) {
                rent.setStatName(billStat.getName());
            }
        }

        rent.setUpdate_time(DateUtil.now());
        rentService.update(rent);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/save11")
    @ResponseBody
    public Map<String, Object> save1(Rent rent) throws Exception {
        Integer warehouseId = rent.getWarehouseId();
        if (warehouseId != null && warehouseId > 0) {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            if (warehouse != null) {
                rent.setWarehouseName(warehouse.getName());
            }
        }

        Integer statId = rent.getStat();
        if (statId != null && statId > 0) {
            BillStat billStat = billStatService.findById(statId);
            if (billStat != null) {
                rent.setStatName(billStat.getName());
            }
        }

        int resultTotal = 0; // 操作的记录条数
        if (rent.getId() == null) {
            rent.setCreate_time(DateUtil.now());
            resultTotal = rentService.add(rent);
        } else {
            rent.setUpdate_time(DateUtil.now());
            resultTotal = rentService.update(rent);
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
            rentService.delete(ids);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Rent findById(@RequestParam(value = "id") Integer id) throws Exception {
        Rent rent = rentService.findById(id);
        return rent;
    }

}
