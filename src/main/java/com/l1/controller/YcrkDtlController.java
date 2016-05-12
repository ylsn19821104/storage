package com.l1.controller;

import com.l1.entity.PageBean;
import com.l1.entity.YcrkDtl;
import com.l1.service.BillStatService;
import com.l1.service.SkuService;
import com.l1.service.YcrkDtlService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/ycrkDtl")
public class YcrkDtlController {
    @Resource
    private YcrkDtlService ycrkDtlService;

    @Resource
    private BillStatService billStatService;

    @Resource
    private SkuService skuService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, YcrkDtl s_ycrkDtl
    ) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", s_ycrkDtl.getId());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<YcrkDtl> ycrkDtlList = ycrkDtlService.find(map);
        Long total = ycrkDtlService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", ycrkDtlList);
        result.put("total", total);

        return result;
    }

    @RequestMapping("/findAllById")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "id", required = false) String id, HttpServletRequest request
    ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        List<YcrkDtl> linkManList = ycrkDtlService.find(map);
        Map<String, Object> result = new HashMap<String, Object>();

//TODO 排除?		jsonConfig.setExcludes(new String[] { "customer" });

        Long total = ycrkDtlService.getTotal(map);
        result.put("rows", linkManList);
        result.put("total", total);

        request.setAttribute("combo", "hoho");
        return result;
    }

    @RequestMapping(value = "/save11", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(YcrkDtl ycrkDtl) {
        ycrkDtlService.add(ycrkDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(YcrkDtl ycrkDtl) {
        ycrkDtlService.update(ycrkDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") String[] ids) {
        ycrkDtlService.delete(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public YcrkDtl findById(@RequestParam("id") int id) {
        YcrkDtl ycrkDtl = ycrkDtlService.findById(id);
        return ycrkDtl;
    }


    @ResponseBody
    public Map<String, Object> save(YcrkDtl ycrkDtl, @RequestParam("id") int id) throws Exception {
        ycrkDtl.setId(id);

        int resultTotal = 0; // 操作的记录条数
        if (ycrkDtl.getDtlId() == null) {
            resultTotal = ycrkDtlService.add(ycrkDtl);
        } else {
            resultTotal = ycrkDtlService.update(ycrkDtl);
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
    public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids) throws Exception {
        if (ids != null && ids.length > 0) {
            ycrkDtlService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
