package com.l1.entity;

import java.math.BigDecimal;

public class RentDetail {
  private Integer id;
  private Integer dtlId;

  private int stat;

  private Integer skuId;
  private String itemName;
  private BigDecimal itemPrice;
  private Integer itemAmount;


  private BigDecimal itemRent;
  private BigDecimal itemRepo;

  public RentDetail() {
  }

  public RentDetail(Integer id, Integer dtlId, int stat, Integer skuId, String itemName, BigDecimal itemPrice, Integer itemAmount, BigDecimal itemRent, BigDecimal itemRepo) {
    this.id = id;
    this.dtlId = dtlId;
    this.stat = stat;
    this.skuId = skuId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemAmount = itemAmount;
    this.itemRent = itemRent;
    this.itemRepo = itemRepo;
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
}
