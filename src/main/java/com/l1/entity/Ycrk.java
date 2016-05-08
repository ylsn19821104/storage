package com.l1.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Ycrk {
	private Integer id;
	private String billNo;
	private String outBillNo;

	private Integer outWarehouseId;
	private String outWarehouseName;

	private Integer inWarehouseId;
	private String inWarehouseName;

	private Integer makerId;
	private String makerName;

	private BigDecimal total;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date billDate;

	private int stat;
	private String statName;

	private Integer create_by;

	private Integer update_by;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date update_time;

	public Integer getOutWarehouseId() {
		return outWarehouseId;
	}

	public void setOutWarehouseId(Integer outWarehouseId) {
		this.outWarehouseId = outWarehouseId;
	}

	public String getOutWarehouseName() {
		return outWarehouseName;
	}

	public void setOutWarehouseName(String outWarehouseName) {
		this.outWarehouseName = outWarehouseName;
	}

	public Integer getInWarehouseId() {
		return inWarehouseId;
	}

	public void setInWarehouseId(Integer inWarehouseId) {
		this.inWarehouseId = inWarehouseId;
	}

	public String getInWarehouseName() {
		return inWarehouseName;
	}

	public void setInWarehouseName(String inWarehouseName) {
		this.inWarehouseName = inWarehouseName;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

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

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date beginDate) {
		this.billDate = beginDate;
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

	public String getOutBillNo() {
		return outBillNo;
	}

	public void setOutBillNo(String outBillNo) {
		this.outBillNo = outBillNo;
	}

	public Integer getMakerId() {
		return makerId;
	}

	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}

	public String getMakerName() {
		return makerName;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
}
