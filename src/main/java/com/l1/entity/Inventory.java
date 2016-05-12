package com.l1.entity;

import java.math.BigDecimal;


/**
 * 库存
 * 
 * @author hongxp 2016年4月28日
 *
 */
public class Inventory {
  private Integer id;
  private Integer skuId;
  private Integer amount;
  private Integer sizeId;
  private Integer colorId;
  private Integer warehouseId;
  private Integer locationId;
  private String style;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSkuId() {
    return skuId;
  }

  public void setSkuId(Integer skuId) {
    this.skuId = skuId;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Integer getSizeId() {
    return sizeId;
  }

  public void setSizeId(Integer sizeId) {
    this.sizeId = sizeId;
  }

  public Integer getColorId() {
    return colorId;
  }

  public void setColorId(Integer colorId) {
    this.colorId = colorId;
  }

  public Integer getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(Integer warehouseId) {
    this.warehouseId = warehouseId;
  }

  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }


}
