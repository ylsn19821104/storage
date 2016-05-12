package com.l1.controller;

import com.l1.entity.Color;
import com.l1.entity.PageBean;
import com.l1.service.ColorService;
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
@RequestMapping("/color")
public class ColorController {
    @Resource
    private ColorService colorService;

    @RequestMapping
    public String showPage() {
        return "colorManage";
    }

    /**
     * 分页条件查询仓库
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Color s_color
    ) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_color.getName()));
        map.put("code", StringUtil.formatLike(s_color.getCode()));
        map.put("stat", "使用");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Color> colorList = colorService.find(map);
        Long total = colorService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", colorList);
        result.put("total", total);


        return result;
    }

    /**
     * 添加或者修改仓库
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Color color) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (color.getId() == null) {
            color.setCreate_time(DateUtil.now());
            color.setStat("使用");
            resultTotal = colorService.add(color);
        } else {
            color.setUpdate_time(DateUtil.now());
            resultTotal = colorService.update(color);
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
            colorService.delete(Integer.parseInt(idsStr[i]));
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        return result;
    }

    @RequestMapping("/itemManagerComboList")
    @ResponseBody
    public List<Color> itemManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "使用");
        List<Color> brandList = colorService.find(map);
        return brandList;
    }


}
