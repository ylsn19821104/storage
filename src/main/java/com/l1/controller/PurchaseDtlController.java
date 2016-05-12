package com.l1.controller;

import com.l1.entity.BillStat;
import com.l1.entity.PageBean;
import com.l1.entity.PurchaseDtl;
import com.l1.entity.Sku;
import com.l1.service.BillStatService;
import com.l1.service.PurchaseDtlService;
import com.l1.service.SkuService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/purchaseDtl")
public class PurchaseDtlController {
  @Resource
  private PurchaseDtlService purchaseDtlService;

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
      @RequestParam(value = "rows", required = false) String rows, PurchaseDtl s_purchaseDtl)
          throws Exception {
    PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", s_purchaseDtl.getId());
    map.put("start", pageBean.getStart());
    map.put("size", pageBean.getPageSize());

    List<PurchaseDtl> purchaseDtlList = purchaseDtlService.find(map);
    Long total = purchaseDtlService.getTotalByPurchaseId(s_purchaseDtl.getId());

    Map<String, Object> result = new HashMap<String, Object>();
    result.put("rows", purchaseDtlList);
    result.put("total", total);


    return result;
  }

  @RequestMapping("/findAllById")
  @ResponseBody
  public Map<String, Object> list(@RequestParam(value = "id", required = false) Integer id)
      throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);

    List<PurchaseDtl> linkManList = purchaseDtlService.find(map);
    Map<String, Object> result = new HashMap<String, Object>();

    // TODO 排除 jsonConfig.setExcludes(new String[] {"customer"});

    Long total = purchaseDtlService.getTotalByPurchaseId(id);
    result.put("rows", linkManList);
    result.put("total", total);


    return result;
  }

  @RequestMapping(value = "/save11", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> save(PurchaseDtl purchaseDtl) {
    if (purchaseDtl != null && purchaseDtl.getStat() > 0) {
      BillStat billStat = billStatService.findById(purchaseDtl.getStat());
      if (billStat != null) {
        purchaseDtl.setStatName(billStat.getName());
      }
    }

    purchaseDtlService.add(purchaseDtl);
    Map<String, Object> ret = new HashMap<String, Object>();
    ret.put("flag", true);
    return ret;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> update(PurchaseDtl purchaseDtl) {

    if (purchaseDtl != null && purchaseDtl.getStat() > 0) {
      BillStat billStat = billStatService.findById(purchaseDtl.getStat());
      if (billStat != null) {
        purchaseDtl.setStatName(billStat.getName());
      }
    }

    if (purchaseDtl != null && purchaseDtl.getSkuId() > 0) {
      Sku sku = skuService.findById(purchaseDtl.getSkuId());
      if (sku != null) {
        purchaseDtl.setItemName(sku.getItemName());
      }
    }

    purchaseDtlService.update(purchaseDtl);
    Map<String, Object> ret = new HashMap<String, Object>();
    ret.put("flag", true);
    return ret;
  }

  @RequestMapping(value = "remove", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> remove(@RequestParam(value = "ids[]") Integer[] ids) {
    purchaseDtlService.deleteByDtlIds(ids);
    Map<String, Object> ret = new HashMap<String, Object>();
    ret.put("flag", true);
    return ret;
  }

  @RequestMapping(value = "/findById", method = RequestMethod.GET)
  @ResponseBody
  public PurchaseDtl findById(@RequestParam("id") int id) {
    PurchaseDtl purchaseDtl = purchaseDtlService.findByDtlId(id);
    return purchaseDtl;
  }


  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> save(PurchaseDtl purchaseDtl, @RequestParam("id") int id)
      throws Exception {
    purchaseDtl.setId(id);
    if (purchaseDtl != null && purchaseDtl.getStat() > 0) {
      BillStat billStat = billStatService.findById(purchaseDtl.getStat());
      if (billStat != null) {
        purchaseDtl.setStatName(billStat.getName());
      }
    }

    if (purchaseDtl != null && purchaseDtl.getSkuId() > 0) {
      Sku sku = skuService.findById(purchaseDtl.getSkuId());
      if (sku != null) {
        purchaseDtl.setItemName(sku.getItemName());
      }
    }

    int resultTotal = 0; // 操作的记录条数
    if (purchaseDtl.getDtlId() == null) {
      resultTotal = purchaseDtlService.add(purchaseDtl);
    } else {
      resultTotal = purchaseDtlService.update(purchaseDtl);
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
  public Map<String, Object> delete(@RequestParam(value = "ids[]") Integer[] ids) throws Exception {
    if (ids != null && ids.length > 0) {
      purchaseDtlService.deleteByDtlIds(ids);
    }

    Map<String, Object> result = new HashMap<String, Object>();
    result.put("flag", true);

    return result;
  }

}
