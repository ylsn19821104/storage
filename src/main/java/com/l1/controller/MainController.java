package com.l1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luopotaotao on 2016/5/6.
 */
@Controller
@RequestMapping(value = {"/"})
public class MainController {

    @RequestMapping
    public String index(){
        return "login";
    }
    
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
