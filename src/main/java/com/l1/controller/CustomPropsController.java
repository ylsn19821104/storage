package com.l1.controller;

import com.l1.entity.CustomProp;
import com.l1.service.CustomPropService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/10.
 */
@Controller
@RequestMapping("customProps")
public class CustomPropsController {

    @Resource
    private CustomPropService customPropService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String showPage(@PathVariable("id") int id, Model model){
        Map<String,Object> info = customPropService.findInfo(id);
        model.addAllAttributes(info);
        return "customPropsManage";
    }

    @RequestMapping(value = "{propId}/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> list(@PathVariable("propId") int propId, Integer page, Integer rows){
        Map<String,Object> ret = new HashMap<String, Object>();
        int total = customPropService.getTotal(propId);
        List<CustomProp> list = customPropService.find(propId,page,rows);

        ret.put("total",total);
        ret.put("rows",list);
        return ret;
    }

    @RequestMapping(value = "{id}/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(@PathVariable("id") Integer id, CustomProp prop){

        Map<String,Object> ret = new HashMap<String, Object>();
        prop.setPropId(id);
        int count = customPropService.save(prop);
        ret.put("flag",count>0);
        return ret;
    }
    @RequestMapping(value = "remove",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> remove(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> ret = new HashMap<String, Object>();
        int count = customPropService.remove(ids);
        ret.put("flag",count>0);
        return ret;
    }

}
