package com.l1.controller;

import com.l1.entity.*;
import com.l1.service.*;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: luopotaotao
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
        List<Dic> statusDic = dicService.query("rentStatus");
        model.addAttribute("statusDic",statusDic);
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
    public Map<String, Object> save(Rent rent,
                                    @RequestParam(value = "inserted",required = false) String insertedStr,
                                    @RequestParam(value = "updated",required = false) String updatedStr,
                                    @RequestParam(value = "deleted",required = false) Integer[] deleted, BindingResult err) {
        rent.setCreate_time(DateUtil.now());
        List<RentDtl> inserted = null;
        List<RentDtl> updated = null;
        if(rent.getId()!=null&&rent.getId()>0){
            if(updatedStr!=null&&updatedStr.length()>0){
                inserted = jsonArrayToRentDetailList(JSONArray.fromObject(updatedStr));
            }
        }
        if(insertedStr!=null&&insertedStr.length()>0){
            inserted = jsonArrayToRentDetailList(JSONArray.fromObject(insertedStr));
        }

        int count = 0;
        if (rent.getId() != null) {
            count = rentService.updateWithDetails(rent, inserted,updated,deleted);
        } else {
            count = rentService.saveRentWithDetails(rent, inserted);

        }
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", count > 0);
        return ret;
    }

    private List<RentDtl> jsonArrayToRentDetailList(JSONArray array){
        List<RentDtl> ret = null;
        if(array!=null&&array.size()>0){
            ret = new LinkedList<RentDtl>();
            for(int i = 0;i<array.size();i++){
                ret.add(jsonToRentDetail(array.getJSONObject(i)));
            }
        }
        return ret;
    }

    private RentDtl jsonToRentDetail(JSONObject json) {
        WrappedJSON item = new WrappedJSON(json);
        RentDtl ret = new RentDtl();
        ret.setDtlId(item.getInteger("dtlId"));
        ret.setSkuId(item.getInteger("skuId"));
        ret.setItemName(item.getString("itemName"));
//        ret.setStat(json.getInt("stat"));
        ret.setItemPrice(item.getBigDecimal("itemPrice"));

        ret.setItemAmount(item.getInteger("itemAmount"));

        ret.setItemRent(item.getBigDecimal("itemRent"));
        ret.setItemRepo(item.getBigDecimal("itemRepo"));
        return ret;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Rent rent) {
        rent.setUpdate_time(DateUtil.now());
        rentService.update(rent);

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

//    @RequestMapping(value = "/save11")
//    @ResponseBody
//    public Map<String, Object> save1(Rent rent) throws Exception {
//        Integer warehouseId = rent.getWarehouseId();
//        if (warehouseId != null && warehouseId > 0) {
//            Warehouse warehouse = warehouseService.findById(warehouseId);
//            if (warehouse != null) {
//                rent.setWarehouseName(warehouse.getName());
//            }
//        }
//
//        Integer statId = rent.getStat();
//        if (statId != null && statId > 0) {
//            BillStat billStat = billStatService.findById(statId);
//            if (billStat != null) {
//                rent.setStatName(billStat.getName());
//            }
//        }
//
//        int resultTotal = 0; // 操作的记录条数
//        if (rent.getId() == null) {
//            rent.setCreate_time(DateUtil.now());
//            resultTotal = rentService.add(rent);
//        } else {
//            rent.setUpdate_time(DateUtil.now());
//            resultTotal = rentService.update(rent);
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
    @RequestMapping(value = "/finish",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> finish(@RequestParam(value = "ids[]")Integer[] ids){
        Map<String,Object> ret = new HashMap<String, Object>();
        int count = rentService.finish(ids);
        ret.put("flag",count>0);
        return ret;
    }
    private class WrappedJSON {
        private JSONObject source;

        public WrappedJSON(JSONObject source) {
            this.source = source;
        }

        public String getString(String key) {
            return source.get(key)==null||source.get(key) instanceof JSONNull ? null : source.getString(key);
        }

        public Integer getInteger(String key) {
            return source.get(key)==null|| source.get(key) instanceof JSONNull ? null : source.getInt(key);
        }

        public Double getDouble(String key) {
            return source.get(key)==null||source.get(key) instanceof JSONNull ? null : source.getDouble(key);
        }

        public BigDecimal getBigDecimal(String key) {
            return source.get(key)==null||source.get(key) instanceof JSONNull ? null : BigDecimal.valueOf(source.getDouble(key));
        }
    }
}
