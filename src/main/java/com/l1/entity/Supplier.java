package com.l1.entity;

import java.util.Date;

public class Supplier {
  private Integer id;
  private String supplierName;
  private String supplierCode;
  private String type;
  private String stat;
  private String mobile;
  private String text;

  private Integer create_by;

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

  private Integer update_by;

  private Date create_time;
  private Date update_time;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }

  public String getSupplierCode() {
    return supplierCode;
  }

  public void setSupplierCode(String supplierCode) {
    this.supplierCode = supplierCode;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStat() {
    return stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
