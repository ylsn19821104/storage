package com.l1.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
  private Integer id;
  private String name;
  private String code;
  private String stat;
  private String statName;
  private Integer create_by;
  private Integer update_by;

  private Date create_time;
  private Date update_time;

  private Brand brand;

  private String color;
  private String colorName;

  private String size;



  private String sizeName;


  // 品牌
  private Integer brandId;
  private String brandName;

  // 款号
  private String style;

  // 备注
  private String remark;

  // 大分类
  private Integer primaryCategoryId;
  private String primaryCategoryName;

  // 小分类
  private Integer minorCategoryId;
  private String minorCategoryName;

  // 租金
  private BigDecimal rental1;
  private BigDecimal rental2;

  // 押金
  private BigDecimal deposit;

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getPrimaryCategoryId() {
    return primaryCategoryId;
  }

  public void setPrimaryCategoryId(Integer primaryCategoryId) {
    this.primaryCategoryId = primaryCategoryId;
  }

  public String getPrimaryCategoryName() {
    return primaryCategoryName;
  }

  public void setPrimaryCategoryName(String primaryCategoryName) {
    this.primaryCategoryName = primaryCategoryName;
  }

  public Integer getMinorCategoryId() {
    return minorCategoryId;
  }

  public void setMinorCategoryId(Integer minorCategoryId) {
    this.minorCategoryId = minorCategoryId;
  }

  public String getMinorCategoryName() {
    return minorCategoryName;
  }

  public void setMinorCategoryName(String minorCategoryName) {
    this.minorCategoryName = minorCategoryName;
  }

  public BigDecimal getRental1() {
    return rental1;
  }

  public void setRental1(BigDecimal rental1) {
    this.rental1 = rental1;
  }

  public BigDecimal getRental2() {
    return rental2;
  }

  public void setRental2(BigDecimal rental2) {
    this.rental2 = rental2;
  }

  public BigDecimal getDeposit() {
    return deposit;
  }

  public void setDeposit(BigDecimal deposit) {
    this.deposit = deposit;
  }

  public Integer getBrandId() {
    return brandId;
  }

  public void setBrandId(Integer brandId) {
    this.brandId = brandId;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getStat() {
    return stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }

  public String getStatName() {
    return statName;
  }

  public void setStatName(String statName) {
    this.statName = statName;
  }

  public Integer getCreate_by() {
    return create_by;
  }

  public void setCreate_by(Integer create_by) {
    this.create_by = create_by;
  }

  public Integer getUpdate_by() {
    return update_by;
  }

  public void setUpdate_by(Integer update_by) {
    this.update_by = update_by;
  }

  public Date getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Date create_time) {
    this.create_time = create_time;
  }

  public Date getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(Date update_time) {
    this.update_time = update_time;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public String getColorName() {
    return colorName;
  }

  public void setColorName(String colorName) {
    this.colorName = colorName;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getSizeName() {
    return sizeName;
  }

  public void setSizeName(String sizeName) {
    this.sizeName = sizeName;
  }
}
