package com.l1.controller;

import com.l1.entity.MinorCategory;
import com.l1.entity.PageBean;
import com.l1.service.MinorCategoryService;
import com.l1.util.DateUtil;
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
@RequestMapping("/category/minor")
public class MinorCategoryController {
    @Resource
    private MinorCategoryService minorCategoryService;

    @RequestMapping
    public String showPage() {
        return "minorCategoryManage";
    }

    /**
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, MinorCategory s_minor
    ) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_minor.getName()));
        map.put("code", StringUtil.formatLike(s_minor.getCode()));
        map.put("stat", "使用");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<MinorCategory> minorList = minorCategoryService.find(map);
        Long total = minorCategoryService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", minorList);
        result.put("total", total);

        return result;
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(MinorCategory minor) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (minor.getId() == null) {
            minor.setCreate_time(DateUtil.now());
            minor.setStat("使用");
            resultTotal = minorCategoryService.add(minor);
        } else {
            minor.setUpdate_time(DateUtil.now());
            resultTotal = minorCategoryService.update(minor);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    /**
     * 删除仓库
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids)
            throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            minorCategoryService.delete(Integer.parseInt(idsStr[i]));
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        return result;
    }

    @RequestMapping("/itemManagerComboList")
    @ResponseBody
    public List<MinorCategory> itemManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "使用");
        List<MinorCategory> minorList = minorCategoryService.find(map);
        return minorList;
    }

}
