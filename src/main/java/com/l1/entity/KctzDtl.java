package com.l1.entity;

import java.math.BigDecimal;

public class KctzDtl {
	private Integer id;
	private Integer dtlId;
	private Integer skuId;
	private String skuName;

	private Integer warehouseId;
	private String warehouseName;

	private BigDecimal skuAmount;
	private BigDecimal tzAmount;

	private Integer locationId;
	private String locationName;

	private Integer tzLocationId;
	private String tzLocationName;

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getTzLocationName() {
		return tzLocationName;
	}

	public void setTzLocationName(String tzLocationName) {
		this.tzLocationName = tzLocationName;
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

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public BigDecimal getSkuAmount() {
		return skuAmount;
	}

	public void setSkuAmount(BigDecimal skuAmount) {
		this.skuAmount = skuAmount;
	}

	public BigDecimal getTzAmount() {
		return tzAmount;
	}

	public void setTzAmount(BigDecimal tzAmount) {
		this.tzAmount = tzAmount;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getTzLocationId() {
		return tzLocationId;
	}

	public void setTzLocationId(Integer tzLocationId) {
		this.tzLocationId = tzLocationId;
	}

}
