/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 报台信息Entity
 * @author 报台信息
 * @version 2020-02-27
 */
public class ReportStandbookImageDetail extends DataEntity<ReportStandbookImageDetail> {
	
	private static final long serialVersionUID = 1L;
	private String reportImgUrl;		// 图片
	private ReportStandbook report;		// report_id 父类
	private Date addDate;		// 时间
	
	public ReportStandbookImageDetail() {
		super();
	}

	public ReportStandbookImageDetail(String id){
		super(id);
	}

	public ReportStandbookImageDetail(ReportStandbook report){
		this.report = report;
	}

	@Length(min=0, max=100, message="图片长度必须介于 0 和 100 之间")
	public String getReportImgUrl() {
		return reportImgUrl;
	}

	public void setReportImgUrl(String reportImgUrl) {
		this.reportImgUrl = reportImgUrl;
	}
	
	@Length(min=0, max=32, message="report_id长度必须介于 0 和 32 之间")
	public ReportStandbook getReport() {
		return report;
	}

	public void setReport(ReportStandbook report) {
		this.report = report;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
}