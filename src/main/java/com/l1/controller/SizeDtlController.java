package com.l1.controller;

import com.l1.entity.SizeDtl;
import com.l1.service.SizeDtlService;
import com.l1.util.DateUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sizeDtl")
public class SizeDtlController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    @Resource
    private SizeDtlService sizeDtlService;

    @RequestMapping
    public String showPage() {
        return "sizeDtlManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "sizeId", required = false) String sizeId)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sizeId", sizeId);
        List<SizeDtl> sizeDtlList = sizeDtlService.find(map);

        Map<String, Object> result = new HashMap<String, Object>();
        //TODO 排出?jsonConfig.setExcludes(new String[] { "sizeId" });

        result.put("rows", sizeDtlList);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(SizeDtl sizeDtl) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (sizeDtl.getId() == null) {
            sizeDtl.setCreate_time(DateUtil.now());
            resultTotal = sizeDtlService.add(sizeDtl);
        } else {
            sizeDtl.setUpdate_time(DateUtil.now());
            resultTotal = sizeDtlService.update(sizeDtl);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "id") String id) throws Exception {
        sizeDtlService.delete(Integer.parseInt(id));

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        return result;
    }

    @RequestMapping("/comboList")
    @ResponseBody
    public List<SizeDtl> itemManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "1");
        List<SizeDtl> brandList = sizeDtlService.find(map);

        //TODO 过滤?
//	    config.setJsonPropertyFilter(new PropertyFilter() {
//	      @Override
//	      public boolean apply(Object source, String name, Object value) {
//	        if ("id".equals(name) || "text".equals(name)) {
//	          return false;
//	        } else {
//	          return true;
//	        }
//	      }
//	    });

        return brandList;
    }

}
