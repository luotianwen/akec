/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 报台信息Entity
 * @author 报台信息
 * @version 2020-02-27
 */
public class ReportStandbookGradeDetail extends DataEntity<ReportStandbookGradeDetail> {
	
	private static final long serialVersionUID = 1L;
	private String grade;		// 评分
	private ReportStandbook report;		// report_id 父类
	private String gradeId;		// 评分id
	private String gradeName;		// 评分id

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public ReportStandbookGradeDetail() {
		super();
	}

	public ReportStandbookGradeDetail(String id){
		super(id);
	}

	public ReportStandbookGradeDetail(ReportStandbook report){
		this.report = report;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=32, message="report_id长度必须介于 0 和 32 之间")
	public ReportStandbook getReport() {
		return report;
	}

	public void setReport(ReportStandbook report) {
		this.report = report;
	}
	
	@Length(min=0, max=32, message="评分id长度必须介于 0 和 32 之间")
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
}