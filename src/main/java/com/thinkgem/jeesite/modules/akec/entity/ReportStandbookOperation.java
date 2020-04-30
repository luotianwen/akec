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
 * 手术报数Entity
 * @author 手术报数
 * @version 2020-03-27
 */
public class ReportStandbookOperation extends DataEntity<ReportStandbookOperation> {
	
	private static final long serialVersionUID = 1L;
	private Date operateDate;		// 手术日期
	private Date operateEdate;		// 手术日期
	private String hospitalName;		// 医院名称
	private String hospitalId;		// 医院id
	private String provinceCode;		// 省
	private String provinceName;		// 省名称
	private String cityName;		// 市名称
	private String cityCode;		// 市
	private String surgeryId;		// 类别
	private String surgeryGrade;		// 类别
	private String userName;		// user_name
	private AppUser user;		// user_id
	private String unitCount;		// 医院总台数
	private String status;		// 状态
	private String updateAdminName;		// 更新人
	private String akCount;		// 爱康总台数
	private String ak;		// 是否入院
	private Date beginOperateDate;		// 开始 手术日期
	private Date endOperateDate;		// 结束 手术日期
	private Date beginOperateEdate;		// 开始 手术日期
	private Date endOperateEdate;		// 结束 手术日期
	private List<ReportStandbookOperationDetail> reportStandbookOperationDetailList = Lists.newArrayList();		// 子表列表
	
	public ReportStandbookOperation() {
		super();
	}

	public ReportStandbookOperation(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getOperateEdate() {
		return operateEdate;
	}

	public void setOperateEdate(Date operateEdate) {
		this.operateEdate = operateEdate;
	}
	
	@Length(min=0, max=100, message="医院名称长度必须介于 0 和 100 之间")
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	@Length(min=0, max=32, message="医院id长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=100, message="省名称长度必须介于 0 和 100 之间")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Length(min=0, max=100, message="市名称长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=32, message="类别长度必须介于 0 和 32 之间")
	public String getSurgeryId() {
		return surgeryId;
	}

	public void setSurgeryId(String surgeryId) {
		this.surgeryId = surgeryId;
	}
	
	@Length(min=0, max=200, message="类别长度必须介于 0 和 200 之间")
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
	
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
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
	
	@Length(min=0, max=32, message="更新人长度必须介于 0 和 32 之间")
	public String getUpdateAdminName() {
		return updateAdminName;
	}

	public void setUpdateAdminName(String updateAdminName) {
		this.updateAdminName = updateAdminName;
	}
	
	public String getAkCount() {
		return akCount;
	}

	public void setAkCount(String akCount) {
		this.akCount = akCount;
	}
	
	@Length(min=0, max=255, message="是否入院长度必须介于 0 和 255 之间")
	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
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
		
	public Date getBeginOperateEdate() {
		return beginOperateEdate;
	}

	public void setBeginOperateEdate(Date beginOperateEdate) {
		this.beginOperateEdate = beginOperateEdate;
	}
	
	public Date getEndOperateEdate() {
		return endOperateEdate;
	}

	public void setEndOperateEdate(Date endOperateEdate) {
		this.endOperateEdate = endOperateEdate;
	}
		
	public List<ReportStandbookOperationDetail> getReportStandbookOperationDetailList() {
		return reportStandbookOperationDetailList;
	}

	public void setReportStandbookOperationDetailList(List<ReportStandbookOperationDetail> reportStandbookOperationDetailList) {
		this.reportStandbookOperationDetailList = reportStandbookOperationDetailList;
	}
}