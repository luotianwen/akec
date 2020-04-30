/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 医院管理Entity
 * @author 医院管理
 * @version 2020-04-04
 */
public class Hospital extends DataEntity<Hospital> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalName;		// 医院名称
	private String hospitalId;		// hospital_id
	private String provinceCode;		// province_code
	private String provinceName;		// 省
	private String cityName;		// 市
	private String cityCode;		// city_code
	private String userName;		// 创建人
	private AppUser user;		// user_id
	private String updateAdminName;		// 更新人
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String year;		// 年度
	private String unitCount;		// 医院总台数
	private String akCount;		// 爱康总台数

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(String unitCount) {
		this.unitCount = unitCount;
	}

	public String getAkCount() {
		return akCount;
	}

	public void setAkCount(String akCount) {
		this.akCount = akCount;
	}

	private List<HospitalCountDetail> hospitalCountDetailList = Lists.newArrayList();		// 子表列表
	private List<HospitalProductDetail> hospitalProductDetailList = Lists.newArrayList();		// 子表列表

	public Hospital() {
		super();
	}

	public Hospital(String id){
		super(id);
	}

	@Length(min=1, max=100, message="医院名称长度必须介于 1 和 100 之间")
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	@Length(min=0, max=32, message="hospital_id长度必须介于 0 和 32 之间")
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Length(min=0, max=32, message="province_code长度必须介于 0 和 32 之间")
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	@Length(min=0, max=100, message="省长度必须介于 0 和 100 之间")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Length(min=0, max=100, message="市长度必须介于 0 和 100 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Length(min=0, max=32, message="city_code长度必须介于 0 和 32 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Length(min=0, max=100, message="创建人长度必须介于 0 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}
	
	@Length(min=0, max=32, message="更新人长度必须介于 0 和 32 之间")
	public String getUpdateAdminName() {
		return updateAdminName;
	}

	public void setUpdateAdminName(String updateAdminName) {
		this.updateAdminName = updateAdminName;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
	public List<HospitalCountDetail> getHospitalCountDetailList() {
		return hospitalCountDetailList;
	}

	public void setHospitalCountDetailList(List<HospitalCountDetail> hospitalCountDetailList) {
		this.hospitalCountDetailList = hospitalCountDetailList;
	}
	public List<HospitalProductDetail> getHospitalProductDetailList() {
		return hospitalProductDetailList;
	}

	public void setHospitalProductDetailList(List<HospitalProductDetail> hospitalProductDetailList) {
		this.hospitalProductDetailList = hospitalProductDetailList;
	}
}