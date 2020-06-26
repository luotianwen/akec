/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 待提交报台Entity
 * @author 待提交报台
 * @version 2020-03-26
 */
public class ReportDStandbook extends DataEntity<ReportDStandbook> {
	
	private static final long serialVersionUID = 1L;
	private Date operateDate;		// operate_date
	private String hospitalName;		// hospital_name
	private String hospitalId;		// hospital_id
	private String provinceCode;		// province_code
	private String provinceName;		// province_name
	private String cityName;		// city_name
	private String cityCode;		// city_code
	private String doctorName;		// doctor_name
	private String patientSex;		// patient_sex
	private String patientAge;		// patient_age
	private String surgeryId;		// surgery_id
	private String surgeryGrade;		// surgery_grade
	private String userName;		// user_name
	private User user;		// user_id
	private String type;		// type
	private String unitCount;		// unit_count
	private String status;		// status
	private String suggest;		// suggest
	private String updateAdminName;		// update_admin_name
	private String dealerName;		// dealer_name
	private String patientName;		// patient_name
	private List<ReportDStandbookGradeDetail> reportDStandbookGradeDetailList = Lists.newArrayList();		// 子表列表
	private List<ReportDStandbookImageDetail> reportDStandbookImageDetailList = Lists.newArrayList();		// 子表列表
	private List<ReportDStandbookProductDetail> reportDStandbookProductDetailList = Lists.newArrayList();		// 子表列表
	
	public ReportDStandbook() {
		super();
	}

	public ReportDStandbook(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	@Length(min=0, max=100, message="hospital_name长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=100, message="province_name长度必须介于 0 和 100 之间")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Length(min=0, max=100, message="city_name长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=32, message="doctor_name长度必须介于 0 和 32 之间")
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	@Length(min=0, max=1, message="patient_sex长度必须介于 0 和 1 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	
	@Length(min=0, max=32, message="surgery_id长度必须介于 0 和 32 之间")
	public String getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(String surgeryId) {
		this.surgeryId = surgeryId;
	}
	
	@Length(min=0, max=200, message="surgery_grade长度必须介于 0 和 200 之间")
	public String getSurgeryGrade() {
		return surgeryGrade;
	}

	public void setSurgeryGrade(String surgeryGrade) {
		this.surgeryGrade = surgeryGrade;
	}
	
	@Length(min=0, max=100, message="user_name长度必须介于 0 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1, message="type长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(String unitCount) {
		this.unitCount = unitCount;
	}
	
	@Length(min=0, max=1, message="status长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1000, message="suggest长度必须介于 0 和 1000 之间")
	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	
	@Length(min=0, max=32, message="update_admin_name长度必须介于 0 和 32 之间")
	public String getUpdateAdminName() {
		return updateAdminName;
	}

	public void setUpdateAdminName(String updateAdminName) {
		this.updateAdminName = updateAdminName;
	}
	
	@Length(min=0, max=100, message="dealer_name长度必须介于 0 和 100 之间")
	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	
	@Length(min=0, max=100, message="patient_name长度必须介于 0 和 100 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public List<ReportDStandbookGradeDetail> getReportDStandbookGradeDetailList() {
		return reportDStandbookGradeDetailList;
	}

	public void setReportDStandbookGradeDetailList(List<ReportDStandbookGradeDetail> reportDStandbookGradeDetailList) {
		this.reportDStandbookGradeDetailList = reportDStandbookGradeDetailList;
	}
	public List<ReportDStandbookImageDetail> getReportDStandbookImageDetailList() {
		return reportDStandbookImageDetailList;
	}

	public void setReportDStandbookImageDetailList(List<ReportDStandbookImageDetail> reportDStandbookImageDetailList) {
		this.reportDStandbookImageDetailList = reportDStandbookImageDetailList;
	}
	public List<ReportDStandbookProductDetail> getReportDStandbookProductDetailList() {
		return reportDStandbookProductDetailList;
	}

	public void setReportDStandbookProductDetailList(List<ReportDStandbookProductDetail> reportDStandbookProductDetailList) {
		this.reportDStandbookProductDetailList = reportDStandbookProductDetailList;
	}
}