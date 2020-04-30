/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 待提交报台Entity
 * @author 待提交报台
 * @version 2020-03-26
 */
public class ReportDStandbookGradeDetail extends DataEntity<ReportDStandbookGradeDetail> {
	
	private static final long serialVersionUID = 1L;
	private String grade;		// grade
	private ReportDStandbook reportId;		// report_id 父类
	private String gradeId;		// grade_id
	
	public ReportDStandbookGradeDetail() {
		super();
	}

	public ReportDStandbookGradeDetail(String id){
		super(id);
	}

	public ReportDStandbookGradeDetail(ReportDStandbook reportId){
		this.reportId = reportId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=32, message="report_id长度必须介于 0 和 32 之间")
	public ReportDStandbook getReportId() {
		return reportId;
	}

	public void setReportId(ReportDStandbook reportId) {
		this.reportId = reportId;
	}
	
	@Length(min=0, max=32, message="grade_id长度必须介于 0 和 32 之间")
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
}