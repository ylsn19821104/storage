package com.l1.controller;

import com.l1.service.SeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/8.
 */
@Controller
@RequestMapping("seq")
public class SeqController {
//    @Autowired
//    private SeqService seqService;
//
//    @RequestMapping(value = "next",method = RequestMethod.GET)
//    public Map<String,String>  next(){
//        Map<String,String>  ret = new HashMap<String, String>();
//        int seq = seqService.next();
//        ret.put("seq",String.format("%1$,04d", seq));
//
//        return ret;
//    }
}
