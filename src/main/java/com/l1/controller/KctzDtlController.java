package com.l1.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.entity.KctzDtl;
import com.l1.entity.Sku;
import com.l1.service.BillStatService;
import com.l1.service.KctzDtlService;
import com.l1.service.SkuService;
import com.l1.util.DateJsonValueProcessor;
import com.l1.util.ResponseUtil;

import net.sf.json.JSONArray;

import net.sf.json.JsonConfig;

/**
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/kctzDtl")
public class KctzDtlController {
    @Resource
    private KctzDtlService kctzDtlService;

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
                                    @RequestParam(value = "rows", required = false) String rows, KctzDtl s_kctzDtl) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", s_kctzDtl.getId());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<KctzDtl> kctzDtlList = kctzDtlService.find(map);
        Long total = kctzDtlService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", kctzDtlList);
        result.put("total", total);

        return result;
    }

    @RequestMapping("/findAllById")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "id", required = false) String id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        List<KctzDtl> linkManList = kctzDtlService.find(map);
        Map<String, Object> result = new HashMap<String, Object>();

        Long total = kctzDtlService.getTotal(map);
        result.put("rows", linkManList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save11", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(KctzDtl kctzDtl) {
        kctzDtlService.add(kctzDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(KctzDtl kctzDtl) {
        kctzDtlService.update(kctzDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") String[] ids) {
        kctzDtlService.delete(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public KctzDtl findById(@RequestParam("id") int id) {
        KctzDtl kctzDtl = kctzDtlService.findById(id);
        return kctzDtl;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(KctzDtl kctzDtl, @RequestParam("id") int id) throws Exception {
        kctzDtl.setId(id);

        int resultTotal = 0; // 操作的记录条数
        if (kctzDtl.getDtlId() == null) {
            resultTotal = kctzDtlService.add(kctzDtl);
        } else {
            resultTotal = kctzDtlService.update(kctzDtl);
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
            kctzDtlService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
