/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 报台信息Entity
 * @author 报台信息
 * @version 2020-02-27
 */
public class ReportStandbook extends DataEntity<ReportStandbook> {
	
	private static final long serialVersionUID = 1L;
	private Date operateDate;		// 报台时间
	private String hospitalName;		// 医院
	private String hospitalId;		// 医院
	private String provinceCode;		// 省
	private String provinceName;		// 省
	private String cityName;		// 市
	private String cityCode;		// 市
	private String doctorName;		// 医生
	private String patientSex;		// 患者性别
	private String patientAge;		// 患者年龄
	private String surgeryId;		// 手术类别
	private String surgeryGrade;		// surgery_grade
	private String userName;		// 报台人
	private String userId;		// 报台人
	private String type;		// 报台类型
	private String unitCount;		// 台数
	private String status;		// 状态
	private String suggest;		// 建议
	private String updateAdminName;		// update_admin_name
	private String dealerName;		// 报台人单位
	private String patientName;		// patient_name
	private Date beginOperateDate;		// 开始 报台时间
	private Date endOperateDate;		// 结束 报台时间
	private List<ReportStandbookGradeDetail> reportStandbookGradeDetailList = Lists.newArrayList();		// 子表列表
	private List<ReportStandbookImageDetail> reportStandbookImageDetailList = Lists.newArrayList();		// 子表列表
	private List<ReportStandbookProductDetail> reportStandbookProductDetailList = Lists.newArrayList();		// 子表列表
	
	public ReportStandbook() {
		super();
	}

	public ReportStandbook(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	@Length(min=0, max=100, message="医院长度必须介于 0 和 100 之间")
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	@Length(min=0, max=32, message="医院长度必须介于 0 和 32 之间")
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Length(min=0, max=32, message="省长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=32, message="市长度必须介于 0 和 32 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Length(min=0, max=32, message="医生长度必须介于 0 和 32 之间")
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	@Length(min=0, max=1, message="患者性别长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=32, message="手术类别长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=100, message="报台人长度必须介于 0 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=32, message="报台人长度必须介于 0 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=1, message="报台类型长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1000, message="建议长度必须介于 0 和 1000 之间")
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
	
	@Length(min=0, max=100, message="报台人单位长度必须介于 0 和 100 之间")
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
	
	public Date getBeginOperateDate() {
		return beginOperateDate;
	}

	public void setBeginOperateDate(Date beginOperateDate) {
		this.beginOperateDate = beginOperateDate;
	}
	
	public Date getEndOperateDate() {
		return endOperateDate;
	}

	public void setEndOperateDate(Date endOperateDate) {
		this.endOperateDate = endOperateDate;
	}
		
	public List<ReportStandbookGradeDetail> getReportStandbookGradeDetailList() {
		return reportStandbookGradeDetailList;
	}

	public void setReportStandbookGradeDetailList(List<ReportStandbookGradeDetail> reportStandbookGradeDetailList) {
		this.reportStandbookGradeDetailList = reportStandbookGradeDetailList;
	}
	public List<ReportStandbookImageDetail> getReportStandbookImageDetailList() {
		return reportStandbookImageDetailList;
	}

	public void setReportStandbookImageDetailList(List<ReportStandbookImageDetail> reportStandbookImageDetailList) {
		this.reportStandbookImageDetailList = reportStandbookImageDetailList;
	}
	public List<ReportStandbookProductDetail> getReportStandbookProductDetailList() {
		return reportStandbookProductDetailList;
	}

	public void setReportStandbookProductDetailList(List<ReportStandbookProductDetail> reportStandbookProductDetailList) {
		this.reportStandbookProductDetailList = reportStandbookProductDetailList;
	}
}