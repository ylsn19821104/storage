package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.service.BillStatService;
import com.l1.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hongxp 2016年4月12日
 */

@Controller
@RequestMapping("/billStat")
public class BillStatController {
    @Resource
    private BillStatService billstatService;

    @RequestMapping
    public String showPage() {
        return "";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, BillStat s_billstat
    ) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_billstat.getName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<BillStat> billstatList = billstatService.find(map);
        Long total = billstatService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();


        result.put("rows", billstatList);
        result.put("total", total);

        return result;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(BillStat billstat) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (billstat.getId() == null) {
            resultTotal = billstatService.add(billstat);
        } else {
            resultTotal = billstatService.update(billstat);
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
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            billstatService.delete(Integer.parseInt(idsStr[i]));
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/comboList")
    @ResponseBody
    public List<BillStat> comboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<BillStat> statList = billstatService.find(map);
        return statList;
    }

}
