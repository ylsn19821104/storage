package com.l1.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReturnMain {
  private Integer id;
  private String billNo;

  private int warehouseId;
  private String warehouseName;

  private String customerName;
  private String customerPhone;
  private String customerAddr;
  private String customerCard;

  private String logisticsCompany;
  private String expressBillNo;
  private String rentBillNo;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date beginDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date endDate;

  // 组
  private int rentDay;
  private BigDecimal rentMoney;
  private BigDecimal repoMoney;
  private BigDecimal damageMoney;

  private int stat;
  private String statName;

  private Integer create_by;
  private Integer update_by;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date create_time;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date update_time;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBillNo() {
    return billNo;
  }

  public void setBillNo(String billNo) {
    this.billNo = billNo;
  }

  public int getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(int warehouseId) {
    this.warehouseId = warehouseId;
  }

  public String getWarehouseName() {
    return warehouseName;
  }

  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerPhone() {
    return customerPhone;
  }

  public void setCustomerPhone(String customerPhone) {
    this.customerPhone = customerPhone;
  }

  public String getCustomerAddr() {
    return customerAddr;
  }

  public void setCustomerAddr(String customerAddr) {
    this.customerAddr = customerAddr;
  }


  public String getLogisticsCompany() {
    return logisticsCompany;
  }

  public void setLogisticsCompany(String logisticsCompany) {
    this.logisticsCompany = logisticsCompany;
  }

  public String getExpressBillNo() {
    return expressBillNo;
  }

  public void setExpressBillNo(String expressBillNo) {
    this.expressBillNo = expressBillNo;
  }

  public String getRentBillNo() {
    return rentBillNo;
  }

  public void setRentBillNo(String returnBillNo) {
    this.rentBillNo = returnBillNo;
  }

  public Date getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public int getRentDay() {
    return rentDay;
  }

  public void setRentDay(int rentDay) {
    this.rentDay = rentDay;
  }

  public BigDecimal getRentMoney() {
    return rentMoney;
  }

  public void setRentMoney(BigDecimal rentMoney) {
    this.rentMoney = rentMoney;
  }

  public BigDecimal getRepoMoney() {
    return repoMoney;
  }

  public void setRepoMoney(BigDecimal repoMoney) {
    this.repoMoney = repoMoney;
  }

  public int getStat() {
    return stat;
  }

  public void setStat(int stat) {
    this.stat = stat;
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

  public String getStatName() {
    return statName;
  }

  public void setStatName(String statName) {
    this.statName = statName;
  }

  public BigDecimal getDamageMoney() {
    return damageMoney;
  }

  public void setDamageMoney(BigDecimal damageMoney) {
    this.damageMoney = damageMoney;
  }

  public String getCustomerCard() {
    return customerCard;
  }

  public void setCustomerCard(String customerCard) {
    this.customerCard = customerCard;
  }

}
