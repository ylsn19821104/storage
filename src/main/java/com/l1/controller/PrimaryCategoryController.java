package com.l1.controller;

import com.l1.entity.PageBean;
import com.l1.entity.PrimaryCategory;
import com.l1.service.PrimaryCategoryService;
import com.l1.util.DateUtil;
import com.l1.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hongxp 2016年4月12日
 */

@Controller
@RequestMapping("/category/primary")
public class PrimaryCategoryController {
    @Resource
    private PrimaryCategoryService primaryCategoryService;

    @RequestMapping
    public String showPage() {
        return "primaryCategoryManage";
    }

    /**
     * 分页条件查询仓库
     *
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, PrimaryCategory s_primary,
                                    HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_primary.getName()));
        map.put("code", StringUtil.formatLike(s_primary.getCode()));
        map.put("stat", "使用");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<PrimaryCategory> primaryList = primaryCategoryService.find(map);
        Long total = primaryCategoryService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", primaryList);
        result.put("total", total);


        return result;
    }

    /**
     * 添加或者修改仓库
     *
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(PrimaryCategory primary, HttpServletResponse response) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (primary.getId() == null) {
            primary.setCreate_time(DateUtil.now());
            primary.setStat("使用");
            resultTotal = primaryCategoryService.add(primary);
        } else {
            primary.setUpdate_time(DateUtil.now());
            resultTotal = primaryCategoryService.update(primary);
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
            primaryCategoryService.delete(Integer.parseInt(idsStr[i]));
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        return result;
    }


    @RequestMapping("/itemManagerComboList")
    @ResponseBody
    public List<PrimaryCategory> itemManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "使用");
        List<PrimaryCategory> primaryList = primaryCategoryService.find(map);
        return primaryList;
    }

}
