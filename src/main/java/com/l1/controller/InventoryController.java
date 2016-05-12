package com.l1.controller;

import com.l1.entity.Inventory;
import com.l1.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/9.
 */
@Controller
@RequestMapping("inventory")
public class InventoryController {
    @Resource
    InventoryService inventoryService;

    @RequestMapping
    public String showPage() {
        return "inventoryManage";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> list(Integer page,Integer rows){
        Map<String,Object> ret = new HashMap<String, Object>();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("start",page);
        params.put("size",rows);
        
        List<Inventory> list = inventoryService.find(params);
        Long total = inventoryService.getTotal(null);
        ret.put("total",total);
        ret.put("rows",list);
        return ret;
    }
}
