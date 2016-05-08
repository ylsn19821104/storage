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
import com.l1.entity.YcckDtl;
import com.l1.entity.Sku;
import com.l1.service.BillStatService;
import com.l1.service.YcckDtlService;
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
@RequestMapping("/ycckDtl")
public class YcckDtlController {
    @Resource
    private YcckDtlService ycckDtlService;

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
                                    @RequestParam(value = "rows", required = false) String rows, YcckDtl s_ycckDtl
    ) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", s_ycckDtl.getId());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<YcckDtl> ycckDtlList = ycckDtlService.find(map);
        Long total = ycckDtlService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", ycckDtlList);
        result.put("total", total);

        return result;
    }

    @RequestMapping("/findAllById")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "id", required = false) String id
    ) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        List<YcckDtl> linkManList = ycckDtlService.find(map);
        Map<String, Object> result = new HashMap<String, Object>();
        //TODO 排除?jsonConfig.setExcludes(new String[] { "customer" });

        Long total = ycckDtlService.getTotal(map);
        result.put("rows", linkManList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save11", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(YcckDtl ycckDtl) {
        ycckDtlService.add(ycckDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(YcckDtl ycckDtl) {
        ycckDtlService.update(ycckDtl);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> remove(@RequestParam(value = "ids[]") String[] ids) {
        ycckDtlService.delete(ids);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("flag", true);
        return ret;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public YcckDtl findById(@RequestParam("id") int id) {
        YcckDtl ycckDtl = ycckDtlService.findById(id);
        return ycckDtl;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(YcckDtl ycckDtl, @RequestParam("id") int id) throws Exception {
        ycckDtl.setId(id);

        int resultTotal = 0; // 操作的记录条数
        if (ycckDtl.getDtlId() == null) {
            resultTotal = ycckDtlService.add(ycckDtl);
        } else {
            resultTotal = ycckDtlService.update(ycckDtl);
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
            ycckDtlService.delete(ids);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", true);
        return result;
    }

}
