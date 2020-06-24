package com.thinkgem.jeesite.modules.akec.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ExcelTarget("reportImportData")
public class ReportImportData implements Serializable {

    @Excel(name = "报台时间", format = "yyyy-MM-dd",  width = 20,orderNum="1",databaseFormat="yyyy-MM-dd",importFormat="yyyy-MM-dd" )
    private Date operateDate;		// 报台时间
    @Excel(name = "医院",orderNum="4")
    @NotBlank(message = "报医院不能为空")
    private String hospitalName;		// 医院
    @Excel(name = "省",orderNum="2")
    @NotBlank(message = "省不能为空")
    private String provinceName;		// 省
    @Excel(name = "市",orderNum="3")
    @NotBlank(message = "市不能为空")
    private String cityName;		// 市
    @Excel(name = "医生",orderNum="5")
    @NotBlank(message = "医生不能为空")
    private String doctorName;		// 医生
    @Excel(name = "患者性别",orderNum="6", replace = {"男_0", "女_1"})
    @NotBlank(message = "患者性别不能为空")
    private String patientSex;		// 患者性别
    @Excel(name = "患者年龄",orderNum="8")
    @NotBlank(message = "患者年龄不能为空")
    private String patientAge;		// 患者年龄

    private String surgeryId;		// 手术类别
    @Excel(name = "手术类别",orderNum="10")
    @NotBlank(message = "手术类别不能为空")
    private String surgeryGrade;	// surgery_grade
    @Excel(name = "物料号(与二维码填写一个)",orderNum="20")
    private String materialCode;//物料号
    @Excel(name = "个体码",orderNum="30")
    private String indivualcode;//个体码
    @Excel(name = "二维码内容(与物料号填写一个)",orderNum="40")
    private String barCode;//二维码内容

    @Excel(name = "导入错误说明（系统生成不用填写）",orderNum="100")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public String getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(String surgeryId) {
        this.surgeryId = surgeryId;
    }
    public String getSurgeryGrade() {
        return surgeryGrade;
    }

    public void setSurgeryGrade(String surgeryGrade) {
        this.surgeryGrade = surgeryGrade;
    }
    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
    public String getIndivualcode() {
        return indivualcode;
    }

    public void setIndivualcode(String indivualcode) {
        this.indivualcode = indivualcode;
    }
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
