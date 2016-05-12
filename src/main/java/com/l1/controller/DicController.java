package com.l1.controller;

import com.l1.entity.Dic;
import com.l1.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/8.
 */
@Controller
@RequestMapping("dic")
public class DicController {
    @Autowired
    private DicService dicService;
    @RequestMapping(value = "/{key}")
    @ResponseBody
    public List<Dic> query(@PathVariable("key") String key){
        return dicService.query(key);
    }
}
