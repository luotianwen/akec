/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 待提交报台Entity
 * @author 待提交报台
 * @version 2020-03-26
 */
public class ReportDStandbookImageDetail extends DataEntity<ReportDStandbookImageDetail> {
	
	private static final long serialVersionUID = 1L;
	private String reportImgUrl;		// report_img_url
	private ReportDStandbook reportId;		// report_id 父类
	private Date addDate;		// add_date
	
	public ReportDStandbookImageDetail() {
		super();
	}

	public ReportDStandbookImageDetail(String id){
		super(id);
	}

	public ReportDStandbookImageDetail(ReportDStandbook reportId){
		this.reportId = reportId;
	}

	@Length(min=0, max=100, message="report_img_url长度必须介于 0 和 100 之间")
	public String getReportImgUrl() {
		return reportImgUrl;
	}

	public void setReportImgUrl(String reportImgUrl) {
		this.reportImgUrl = reportImgUrl;
	}
	
	@Length(min=0, max=32, message="report_id长度必须介于 0 和 32 之间")
	public ReportDStandbook getReportId() {
		return reportId;
	}

	public void setReportId(ReportDStandbook reportId) {
		this.reportId = reportId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
}