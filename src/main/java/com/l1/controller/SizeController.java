package com.l1.controller;

import com.l1.entity.PageBean;
import com.l1.entity.Size;
import com.l1.service.SizeService;
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
@RequestMapping("/size")
public class SizeController {
    @Resource
    private SizeService sizeService;

    @RequestMapping
    public String showPage() {
        return "sizeManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Size s_size)
            throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_size.getName()));
        map.put("code", StringUtil.formatLike(s_size.getCode()));
        map.put("stat", "使用");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Size> sizeList = sizeService.find(map);
        Long total = sizeService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", sizeList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Size size) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (size.getId() == null) {
            size.setCreate_time(DateUtil.now());
            size.setStat("使用");
            resultTotal = sizeService.add(size);
        } else {
            size.setUpdate_time(DateUtil.now());
            resultTotal = sizeService.update(size);
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
            sizeService.delete(Integer.parseInt(idsStr[i]));
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/sizeComboList")
    @ResponseBody
    public List<Size> itemManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "使用");
        List<Size> brandList = sizeService.find(map);
        return brandList;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Size findById(@RequestParam(value = "id") String id) throws Exception {
        Size size = sizeService.findById(Integer.parseInt(id));
        return size;
    }

}
