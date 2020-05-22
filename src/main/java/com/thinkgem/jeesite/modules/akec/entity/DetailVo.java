package com.thinkgem.jeesite.modules.akec.entity;

import java.io.Serializable;
import java.util.Date;

public class DetailVo implements Serializable {
    private String id;
    private Date createDate;
    private Date operateDate;		// 报台时间
    private String hospitalName;		// 医院

    private String provinceName;		// 省
    private String cityName;		// 市
    private String doctorName;		// 医生
    private String patientSex;		// 患者性别
    private String patientAge;		// 患者年龄
    private String surgeryGrade;		// surgery_grade
    private String userName;		// 报台人
    private String dealerName2;		// 报台人单位
    private String type;		// 报台类型
    private String status;		// 状态
    private String suggest;		// 建议
    private String comments2;
    private String baseReportName;

    private String isRecordUnit;		// 是否记台
    private String materialCode;
    private String materialDesc;
    private String materialSpeDesc;

    private String seriesName;
    private String individualcode;
    private String dealerCode;
    private String dealerName;
    private String comments;
    private String saleType;

    public String getOperateDate2() {
        return operateDate2;
    }

    public void setOperateDate2(String operateDate2) {
        this.operateDate2 = operateDate2;
    }

    public String getCreateDate2() {
        return createDate2;
    }

    public void setCreateDate2(String createDate2) {
        this.createDate2 = createDate2;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private String operateDate2;		// 报台时间
    private String createDate2;		// 报台时间
    private String createTime;		// 报台时间
    public String getBaseReportName() {
        return baseReportName;
    }

    public void setBaseReportName(String baseReportName) {
        this.baseReportName = baseReportName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

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

    public String getSurgeryGrade() {
        return surgeryGrade;
    }

    public void setSurgeryGrade(String surgeryGrade) {
        this.surgeryGrade = surgeryGrade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDealerName2() {
        return dealerName2;
    }

    public void setDealerName2(String dealerName2) {
        this.dealerName2 = dealerName2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getComments2() {
        return comments2;
    }

    public void setComments2(String comments2) {
        this.comments2 = comments2;
    }

    public String getIsRecordUnit() {
        return isRecordUnit;
    }

    public void setIsRecordUnit(String isRecordUnit) {
        this.isRecordUnit = isRecordUnit;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getMaterialSpeDesc() {
        return materialSpeDesc;
    }

    public void setMaterialSpeDesc(String materialSpeDesc) {
        this.materialSpeDesc = materialSpeDesc;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getIndividualcode() {
        return individualcode;
    }

    public void setIndividualcode(String individualcode) {
        this.individualcode = individualcode;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }
}
