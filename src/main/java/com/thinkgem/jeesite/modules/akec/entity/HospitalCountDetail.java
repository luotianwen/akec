/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 医院管理Entity
 * @author 医院管理
 * @version 2020-04-04
 */
public class HospitalCountDetail extends DataEntity<HospitalCountDetail> {
	
	private static final long serialVersionUID = 1L;
	private Hospital h;		// 医院 父类
	private String year;		// 年度
	private Integer akCount;		// 爱康台数
	private String type;		// 产品类型
	private String typename;		// 类型名称
	private String unitCount;		// 医院总台数

	public String getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(String unitCount) {
		this.unitCount = unitCount;
	}

	public HospitalCountDetail() {
		super();
	}

	public HospitalCountDetail(String id){
		super(id);
	}

	public HospitalCountDetail(Hospital h){
		this.h = h;
	}

	@Length(min=0, max=32, message="医院长度必须介于 0 和 32 之间")
	public Hospital getH() {
		return h;
	}

	public void setH(Hospital h) {
		this.h = h;
	}
	
	@Length(min=0, max=255, message="年度长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public Integer getAkCount() {
		return akCount;
	}

	public void setAkCount(Integer akCount) {
		this.akCount = akCount;
	}
	
	@Length(min=0, max=255, message="产品类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="类型名称长度必须介于 0 和 255 之间")
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
}