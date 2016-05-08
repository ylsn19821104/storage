package com.l1.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.l1.entity.WarehouseLocation;
import com.l1.service.WarehouseLocationService;
import com.l1.util.DateUtil;
import com.l1.util.ResponseUtil;

import net.sf.json.JSONArray;

import net.sf.json.JsonConfig;

/**
 * @author hongxp 2016年4月12日
 */

@Controller
@RequestMapping("/warehouseLocation")
public class WarehouseLocationController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    @Resource
    private WarehouseLocationService warehouseLocationService;

    @RequestMapping
    public String showPage() {
        return "warehouseLocationManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "warehouseId", required = false) String warehouseId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("warehouseId", warehouseId);
        List<WarehouseLocation> locationList = warehouseLocationService.find(map);

        Map<String, Object> result = new HashMap<String, Object>();
//		jsonConfig.setExcludes(new String[] { "warehouseId" });
        result.put("total",locationList.size());
        result.put("rows", locationList);
        return result;
    }

    /**
     * 添加或者修改仓库
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(WarehouseLocation location) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (location.getId() == null) {
            location.setCreate_time(DateUtil.now());
            resultTotal = warehouseLocationService.add(location);
        } else {
            location.setUpdate_time(DateUtil.now());
            resultTotal = warehouseLocationService.update(location);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    @RequestMapping("/deleteIds")
    @ResponseBody
    public Map<String, Object> deleteIds(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            warehouseLocationService.delete(Integer.parseInt(idsStr[i]));
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "id") String id) throws Exception {
        warehouseLocationService.delete(Integer.parseInt(id));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        return result;
    }

    @RequestMapping("/manageComboList")
    @ResponseBody
    public List<WarehouseLocation> itemManagerComboList(HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "使用");
        List<WarehouseLocation> warehouseList = warehouseLocationService.find(map);
        return warehouseList;
    }

}
