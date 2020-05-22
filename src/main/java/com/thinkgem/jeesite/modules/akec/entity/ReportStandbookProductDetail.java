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
public class ReportStandbookProductDetail extends DataEntity<ReportStandbookProductDetail> {
	
	private static final long serialVersionUID = 1L;
	private ReportStandbook report;		// 外键参照报台主表 父类
    private Product product;
	private String productId;		// 产品ID
	private String individualcode;		// 产品个体码
	private String integral;		// 是否返利 1
	private String note;		// 备注2
	private String isRecordUnit;		// 是否记台
	private String isVerifyIndividualcode;		// 是否校验个体码
	private String scanCode;		// 条形码
	private String produceDate;		// 生产日期
	private String outdate;		// 失效日期
	private Sellproduct  sellproduct;


	private String materialCode;
	private String materialDesc;
	private String materialSpeDesc;

	private String seriesName;
	private String dealerCode;
	private String dealerName;
	private String comments;
	private String saleType;

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

	public Sellproduct getSellproduct() {
		return sellproduct;
	}

	public void setSellproduct(Sellproduct sellproduct) {
		this.sellproduct = sellproduct;
	}

	public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private String type;

	private String indivualcode;

	public String getIndivualcode() {
		return indivualcode;
	}

	public void setIndivualcode(String indivualcode) {
		this.indivualcode = indivualcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public ReportStandbookProductDetail() {
		super();
	}

	public ReportStandbookProductDetail(String id){
		super(id);
	}

	public ReportStandbookProductDetail(ReportStandbook report){
		this.report = report;
	}

	@Length(min=1, max=32, message="外键参照报台主表长度必须介于 1 和 32 之间")
	public ReportStandbook getReport() {
		return report;
	}

	public void setReport(ReportStandbook report) {
		this.report = report;
	}
	
	@Length(min=0, max=32, message="产品ID长度必须介于 0 和 32 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=100, message="产品个体码长度必须介于 0 和 100 之间")
	public String getIndividualcode() {
		return individualcode;
	}

	public void setIndividualcode(String individualcode) {
		this.individualcode = individualcode;
	}
	
	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Length(min=0, max=1, message="是否记台长度必须介于 0 和 1 之间")
	public String getIsRecordUnit() {
		return isRecordUnit;
	}

	public void setIsRecordUnit(String isRecordUnit) {
		this.isRecordUnit = isRecordUnit;
	}
	
	@Length(min=0, max=1, message="是否校验个体码长度必须介于 0 和 1 之间")
	public String getIsVerifyIndividualcode() {
		return isVerifyIndividualcode;
	}

	public void setIsVerifyIndividualcode(String isVerifyIndividualcode) {
		this.isVerifyIndividualcode = isVerifyIndividualcode;
	}
	
	@Length(min=0, max=100, message="条形码长度必须介于 0 和 100 之间")
	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}
	
	@Length(min=0, max=6, message="生产日期长度必须介于 0 和 6 之间")
	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	
	@Length(min=0, max=6, message="失效日期长度必须介于 0 和 6 之间")
	public String getOutdate() {
		return outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	
}