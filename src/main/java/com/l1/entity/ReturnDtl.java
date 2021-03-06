package com.l1.entity;

import java.math.BigDecimal;

public class ReturnDtl {
  private Integer id;
  private Integer dtlId;

  private int stat;
  private String statName;

  private Integer skuId;
  private String itemName;

  private BigDecimal itemPrice;
  private Integer itemAmount;

  private BigDecimal itemRent;
  private BigDecimal itemRepo;
  private BigDecimal itemDamage;

  private Integer warehouseId;
  private String warehouseName;


  public Integer getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(Integer warehouseId) {
    this.warehouseId = warehouseId;
  }

  public String getWarehouseName() {
    return warehouseName;
  }

  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getDtlId() {
    return dtlId;
  }

  public void setDtlId(Integer dtlId) {
    this.dtlId = dtlId;
  }

  public int getStat() {
    return stat;
  }

  public void setStat(int stat) {
    this.stat = stat;
  }

  public Integer getSkuId() {
    return skuId;
  }

  public void setSkuId(Integer skuId) {
    this.skuId = skuId;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public BigDecimal getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(BigDecimal itemPrice) {
    this.itemPrice = itemPrice;
  }

  public Integer getItemAmount() {
    return itemAmount;
  }

  public void setItemAmount(Integer itemAmount) {
    this.itemAmount = itemAmount;
  }

  public BigDecimal getItemRent() {
    return itemRent;
  }

  public void setItemRent(BigDecimal itemRent) {
    this.itemRent = itemRent;
  }

  public BigDecimal getItemRepo() {
    return itemRepo;
  }

  public void setItemRepo(BigDecimal itemRepo) {
    this.itemRepo = itemRepo;
  }

  public String getStatName() {
    return statName;
  }

  public void setStatName(String statName) {
    this.statName = statName;
  }

  public BigDecimal getItemDamage() {
    return itemDamage;
  }

  public void setItemDamage(BigDecimal itemDamage) {
    this.itemDamage = itemDamage;
  }
}
