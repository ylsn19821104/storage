package com.l1.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.l1.entity.PageBean;
import com.l1.entity.PrimaryCategory;
import com.l1.entity.SizeDtl;
import com.l1.entity.Sku;
import com.l1.entity.Brand;
import com.l1.entity.Color;
import com.l1.entity.Item;
import com.l1.entity.MinorCategory;
import com.l1.service.BrandService;
import com.l1.service.ColorService;
import com.l1.service.ItemService;
import com.l1.service.MinorCategoryService;
import com.l1.service.PrimaryCategoryService;
import com.l1.service.SizeDtlService;
import com.l1.service.SkuService;
import com.l1.util.DateJsonValueProcessor;
import com.l1.util.DateUtil;
import com.l1.util.ResponseUtil;
import com.l1.util.StringUtil;

import net.sf.json.JSONArray;

import net.sf.json.JsonConfig;

/**
 * @author hongxp 2016年4月12日
 */

@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource
    private ItemService itemService;

    @Resource
    BrandService brandService;

    @Resource
    MinorCategoryService minorCategoryService;

    @Resource
    PrimaryCategoryService primaryCategoryService;

    @Resource
    ColorService colorService;

    @Resource
    SizeDtlService sizeDtlService;

    @Resource
    SkuService skuService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }

    @RequestMapping
    public String showPage() {
        return "itemManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows, Item s_item)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_item.getName()));
        map.put("code", StringUtil.formatLike(s_item.getCode()));
        map.put("stat", "1");
        map.put("start", rows!=null&&page!=null?(page>0?page-1:0)*rows:null);
        map.put("size", rows);

        List<Item> itemList = itemService.find(map);
        Long total = itemService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("rows", itemList);
        result.put("total", total);

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Item item) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        Integer brandId = item.getBrandId();
        if (brandId != null) {
            Brand brand = brandService.findById(brandId);
            item.setBrandName(brand.getName());
        }

        Integer primaryId = item.getPrimaryCategoryId();
        if (primaryId != null && primaryId > 0) {
            PrimaryCategory primaryCategory = primaryCategoryService.findById(primaryId);
            item.setPrimaryCategoryName(primaryCategory.getName());
        }

        Integer minorId = item.getMinorCategoryId();
        if (minorId != null && minorId > 0) {
            MinorCategory minorCategory = minorCategoryService.findById(minorId);
            item.setMinorCategoryName(minorCategory.getName());
        }

        String color = item.getColor();
        String[] colorIds = null;
        if (!StringUtil.isEmpty(color)) {
            colorIds = color.split(",");
            if (colorIds.length > 0) {
                List<String> names = colorService.findNamesByIds(colorIds);
                if (names != null && names.size() > 0) {
                    String colorNames = StringUtils.join(names, ",");
                    item.setColorName(colorNames);
                }
            }

        }

        String[] sizeIds = null;
        String size = item.getSize();
        if (!StringUtil.isEmpty(size)) {
            sizeIds = size.split(",");
            if (sizeIds.length > 0) {
                List<String> sizeNames = sizeDtlService.findNamesByIds(sizeIds);
                String sizeName = StringUtils.join(sizeNames, ",");
                item.setSizeName(sizeName);
            }
        }

        if (item.getId() == null) {
            item.setCreate_time(DateUtil.now());
            item.setStat("使用");
            resultTotal = itemService.add(item);

            createSku(item);
        } else {
            Item it = itemService.findById(item.getId());
            if (!(it.getColor().equals(color) && it.getSize().equals(size))) {
                createSku(item);
            }

            item.setUpdate_time(DateUtil.now());
            resultTotal = itemService.update(item);
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
            itemService.delete(Integer.parseInt(idsStr[i]));
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        return result;
    }

    private void createSku(Item item) {
        skuService.deleteByItemId(item.getId());

        String[] sizeIds = item.getSize().split(",");
        String[] colorIds = item.getColor().split(",");
        // 写入二维表
        if ((colorIds != null && colorIds.length > 0) && (sizeIds != null && sizeIds.length > 0)) {
            for (String colorId : colorIds) {
                for (String sizeId : sizeIds) {
                    Color color = colorService.findById(Integer.valueOf(colorId));
                    if (color == null) {
                        continue;
                    }

                    SizeDtl dtl = sizeDtlService.findById(Integer.valueOf(sizeId));
                    if (dtl == null) {
                        continue;
                    }

                    Sku sku = new Sku();
                    sku.setSizeDtlId(Integer.valueOf(sizeId));
                    sku.setSizeDtlName(dtl.getName());

                    sku.setColorId(Integer.valueOf(colorId));
                    sku.setColorName(color.getName());

                    sku.setItemId(item.getId());
                    sku.setItemName(item.getStyle());

                    skuService.add(sku);
                }

            }
        }
    }

}
