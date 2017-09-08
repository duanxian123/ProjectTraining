package com.linghao.projecttraining.model.bean;

import java.io.Serializable;

/**
 * 用户类
 * @author BOOKS
 *
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户id
	private Integer id;
	//用户昵称
	private String name;
	//用户账号
	private String number;
	//用户密码
	private String password;
	//活跃等级
	private Integer active_lv;
	//信誉等级
	private Integer credit_lv;
	//默认城市
	private String place;
	//用户头像 - URL : 用户头像.png
	private String icon;
	//用户性别
	private String sex;
	//用户年龄
	private Integer age;
	
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getActive_lv() {
		return active_lv;
	}
	public void setActive_lv(Integer active_lv) {
		this.active_lv = active_lv;
	}
	public Integer getCredit_lv() {
		return credit_lv;
	}
	public void setCredit_lv(Integer credit_lv) {
		this.credit_lv = credit_lv;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", number=" + number + ", password=" + password + ", active_lv="
				+ active_lv + ", credit_lv=" + credit_lv + ", place=" + place + ", icon=" + icon + ", sex=" + sex
				+ ", age=" + age + "]";
	}
	
	
}
