package com.l1.entity;

/**
 * @author hongxp
 *
 */
public class User {

  private Integer id;
  private String userName;
  private String password;
  private String trueName;
  private String email;
  private String phone;
  private String roleName;
  private String userCode;
  private String text;
  


  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTrueName() {
    return trueName;
  }

  public void setTrueName(String trueName) {
    this.trueName = trueName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}


}
