package com.l1.controller;

import com.l1.entity.PageBean;
import com.l1.entity.Warehouse;
import com.l1.service.WarehouseService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hongxp 2016年4月12日
 */

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
    @Resource
    private WarehouseService warehouseService;

    @RequestMapping
    public String showPage(){
        return "warehouseManage";
    }

    /**
     * 分页条件查询仓库
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows, Warehouse s_warehouse) throws Exception {
        Map<String,Object> ret = new HashMap<String, Object>();

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_warehouse.getName()));
        map.put("code", StringUtil.formatLike(s_warehouse.getCode()));
        map.put("stat", StringUtil.formatLike(s_warehouse.getStat()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Warehouse> warehouseList = warehouseService.find(map);
        Long total = warehouseService.getTotal(map);

        JSONArray jsonArray = JSONArray.fromObject(warehouseList);
        ret.put("rows", warehouseList);
        ret.put("total", total);
        return ret;
    }

    /**
     * 添加或者修改仓库
     *
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(Warehouse warehouse) throws Exception {
        Map<String,Object> ret = new HashMap<String, Object>();
        int resultTotal = 0; // 操作的记录条数
        if (warehouse.getId() == null) {
            warehouse.setCreate_time(DateUtil.now());
            resultTotal = warehouseService.add(warehouse);
        } else {
            warehouse.setUpdate_time(DateUtil.now());
            resultTotal = warehouseService.update(warehouse);
        }

        if (resultTotal > 0) {
            ret.put("success", true);
        } else {
            ret.put("success", false);
        }
        return ret;
    }

    /**
     * 删除仓库
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(@RequestParam(value = "ids") String ids)
            throws Exception {
        Map<String,Object> ret = new HashMap<String, Object>();
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            warehouseService.delete(Integer.parseInt(idsStr[i]));
        }
        ret.put("success", true);

        return ret;
    }

    @RequestMapping(value = "/comboList",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,String>> itemManagerComboList() throws Exception {
        List<Map<String,String>> ret = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "使用");
        ret = warehouseService.findForCombo(map);
        return ret;
    }

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    @ResponseBody
    public Warehouse findById(@RequestParam(value = "warehouseId") String warehouseId) throws Exception {
        Warehouse ret = warehouseService.findById(Integer.parseInt(warehouseId));
        return ret;
    }

}
