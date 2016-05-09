package com.l1.controller;

import com.l1.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
}
