package com.l1.controller;

import com.l1.entity.PageBean;
import com.l1.entity.Supplier;
import com.l1.service.SupplierService;
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
 * @Description:
 * @Author: hongxp
 * @Since: 2016年4月12日
 */

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    private SupplierService supplierService;


    @RequestMapping
    public String showPage() {
        return "supplierManage";
    }

    /**
     * 分页条件查询用户
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */


    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, Supplier s_supplier
    ) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supplierName", StringUtil.formatLike(s_supplier.getSupplierName()));
        map.put("supplierState", StringUtil.formatLike(s_supplier.getStat()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Supplier> supplierList = supplierService.find(map);
        Long total = supplierService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", supplierList);
        result.put("total", total);
        return result;
    }

    /**
     * 添加或者修改供应商
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Supplier supplier) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (supplier.getId() == null) {
            supplier.setCreate_time(DateUtil.now());
            resultTotal = supplierService.add(supplier);
        } else {
            supplier.setUpdate_time(DateUtil.now());
            resultTotal = supplierService.update(supplier);
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
     * 删除供应商
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            supplierService.delete(Integer.parseInt(idsStr[i]));
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/comboList")
    @ResponseBody
    public List<Supplier> itemManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "使用");
        List<Supplier> supplierList = supplierService.find(map);

        return supplierList;
    }

}
