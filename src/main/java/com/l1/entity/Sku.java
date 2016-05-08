package com.l1.entity;

public class Sku {
	private Integer id;
	private Integer itemId;
	private String itemName;

	private Integer colorId;
	private String colorName;

	private Integer sizeDtlId;
	private String sizeDtlName;

	private String text;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getSizeDtlId() {
		return sizeDtlId;
	}

	public void setSizeDtlId(Integer sizeDtlId) {
		this.sizeDtlId = sizeDtlId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeDtlName() {
		return sizeDtlName;
	}

	public void setSizeDtlName(String sizeDtlName) {
		this.sizeDtlName = sizeDtlName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
