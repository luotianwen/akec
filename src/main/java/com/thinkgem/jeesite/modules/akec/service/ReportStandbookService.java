/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.akec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbook;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookGradeDetail;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookGradeDetailDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookImageDetail;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookImageDetailDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportStandbookProductDetail;
import com.thinkgem.jeesite.modules.akec.dao.ReportStandbookProductDetailDao;

/**
 * 报台信息Service
 * @author 报台信息
 * @version 2020-02-27
 */
@Service
@Transactional(readOnly = true)
public class ReportStandbookService extends CrudService<ReportStandbookDao, ReportStandbook> {

	@Autowired
	private ReportStandbookGradeDetailDao reportStandbookGradeDetailDao;
	@Autowired
	private ReportStandbookImageDetailDao reportStandbookImageDetailDao;
	@Autowired
	private ReportStandbookProductDetailDao reportStandbookProductDetailDao;
	
	public ReportStandbook get(String id) {
		ReportStandbook reportStandbook = super.get(id);
		reportStandbook.setReportStandbookGradeDetailList(reportStandbookGradeDetailDao.findList(new ReportStandbookGradeDetail(reportStandbook)));
		reportStandbook.setReportStandbookImageDetailList(reportStandbookImageDetailDao.findList(new ReportStandbookImageDetail(reportStandbook)));
		reportStandbook.setReportStandbookProductDetailList(reportStandbookProductDetailDao.findList(new ReportStandbookProductDetail(reportStandbook)));
		return reportStandbook;
	}
	
	public List<ReportStandbook> findList(ReportStandbook reportStandbook) {
		return super.findList(reportStandbook);
	}
	
	public Page<ReportStandbook> findPage(Page<ReportStandbook> page, ReportStandbook reportStandbook) {
		return super.findPage(page, reportStandbook);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportStandbook reportStandbook) {
		super.save(reportStandbook);
		for (ReportStandbookGradeDetail reportStandbookGradeDetail : reportStandbook.getReportStandbookGradeDetailList()){
			if (reportStandbookGradeDetail.getId() == null){
				continue;
			}
			if (ReportStandbookGradeDetail.DEL_FLAG_NORMAL.equals(reportStandbookGradeDetail.getDelFlag())){
				if (StringUtils.isBlank(reportStandbookGradeDetail.getId())){
					reportStandbookGradeDetail.setReport(reportStandbook);
					reportStandbookGradeDetail.preInsert();
					reportStandbookGradeDetailDao.insert(reportStandbookGradeDetail);
				}else{
					reportStandbookGradeDetail.preUpdate();
					reportStandbookGradeDetailDao.update(reportStandbookGradeDetail);
				}
			}else{
				reportStandbookGradeDetailDao.delete(reportStandbookGradeDetail);
			}
		}
		for (ReportStandbookImageDetail reportStandbookImageDetail : reportStandbook.getReportStandbookImageDetailList()){
			if (reportStandbookImageDetail.getId() == null){
				continue;
			}
			if (ReportStandbookImageDetail.DEL_FLAG_NORMAL.equals(reportStandbookImageDetail.getDelFlag())){
				if (StringUtils.isBlank(reportStandbookImageDetail.getId())){
					reportStandbookImageDetail.setReport(reportStandbook);
					reportStandbookImageDetail.preInsert();
					reportStandbookImageDetailDao.insert(reportStandbookImageDetail);
				}else{
					reportStandbookImageDetail.preUpdate();
					reportStandbookImageDetailDao.update(reportStandbookImageDetail);
				}
			}else{
				reportStandbookImageDetailDao.delete(reportStandbookImageDetail);
			}
		}
		for (ReportStandbookProductDetail reportStandbookProductDetail : reportStandbook.getReportStandbookProductDetailList()){
			if (reportStandbookProductDetail.getId() == null){
				continue;
			}
			if (ReportStandbookProductDetail.DEL_FLAG_NORMAL.equals(reportStandbookProductDetail.getDelFlag())){
				if (StringUtils.isBlank(reportStandbookProductDetail.getId())){
					reportStandbookProductDetail.setReport(reportStandbook);
					reportStandbookProductDetail.preInsert();
					reportStandbookProductDetailDao.insert(reportStandbookProductDetail);
				}else{
					reportStandbookProductDetail.preUpdate();
					reportStandbookProductDetailDao.update(reportStandbookProductDetail);
				}
			}else{
				reportStandbookProductDetailDao.delete(reportStandbookProductDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportStandbook reportStandbook) {
		super.delete(reportStandbook);
		reportStandbookGradeDetailDao.delete(new ReportStandbookGradeDetail(reportStandbook));
		reportStandbookImageDetailDao.delete(new ReportStandbookImageDetail(reportStandbook));
		reportStandbookProductDetailDao.delete(new ReportStandbookProductDetail(reportStandbook));
	}
	
}