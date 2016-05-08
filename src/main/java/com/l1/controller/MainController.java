package com.l1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luopotaotao on 2016/5/6.
 */
@Controller
@RequestMapping(value = {"/","main"})
public class MainController {

    @RequestMapping
    public String main(){
        return "main";
    }
}
