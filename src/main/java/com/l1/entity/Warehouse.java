package com.l1.entity;

import java.util.Date;

public class Warehouse {
	private Long id;
	private String code;
	private String name;
	private String text;
	private String address;
	private String phone;
	private String stat;
	private Integer create_by;
	private Integer update_by;

	private Date create_time;
	private Date update_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", stat='" + stat + '\'' +
                ", create_by=" + create_by +
                ", update_by=" + update_by +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
