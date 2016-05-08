package com.l1.entity;

public class YcckDtl {
	private Integer id;
	private Integer dtlId;

	private Integer skuId;
	private String skuName;

	private Integer skuAmount;
	private Integer outAmount;

	private Integer warehouseId;
	private String warehouseName;

	private Integer locationId;
	private String locationName;

	private Integer stat;
	private String statName;

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
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

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Integer getSkuAmount() {
		return skuAmount;
	}

	public void setSkuAmount(Integer skuAmount) {
		this.skuAmount = skuAmount;
	}

	public Integer getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(Integer outAmount) {
		this.outAmount = outAmount;
	}

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

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
