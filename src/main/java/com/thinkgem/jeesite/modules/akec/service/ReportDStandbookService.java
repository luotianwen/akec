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
import com.thinkgem.jeesite.modules.akec.entity.ReportDStandbook;
import com.thinkgem.jeesite.modules.akec.dao.ReportDStandbookDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportDStandbookGradeDetail;
import com.thinkgem.jeesite.modules.akec.dao.ReportDStandbookGradeDetailDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportDStandbookImageDetail;
import com.thinkgem.jeesite.modules.akec.dao.ReportDStandbookImageDetailDao;
import com.thinkgem.jeesite.modules.akec.entity.ReportDStandbookProductDetail;
import com.thinkgem.jeesite.modules.akec.dao.ReportDStandbookProductDetailDao;

/**
 * 待提交报台Service
 * @author 待提交报台
 * @version 2020-03-26
 */
@Service
@Transactional(readOnly = true)
public class ReportDStandbookService extends CrudService<ReportDStandbookDao, ReportDStandbook> {

	@Autowired
	private ReportDStandbookGradeDetailDao reportDStandbookGradeDetailDao;
	@Autowired
	private ReportDStandbookImageDetailDao reportDStandbookImageDetailDao;
	@Autowired
	private ReportDStandbookProductDetailDao reportDStandbookProductDetailDao;
	
	public ReportDStandbook get(String id) {
		ReportDStandbook reportDStandbook = super.get(id);
		reportDStandbook.setReportDStandbookGradeDetailList(reportDStandbookGradeDetailDao.findList(new ReportDStandbookGradeDetail(reportDStandbook)));
		reportDStandbook.setReportDStandbookImageDetailList(reportDStandbookImageDetailDao.findList(new ReportDStandbookImageDetail(reportDStandbook)));
		reportDStandbook.setReportDStandbookProductDetailList(reportDStandbookProductDetailDao.findList(new ReportDStandbookProductDetail(reportDStandbook)));
		return reportDStandbook;
	}
	
	public List<ReportDStandbook> findList(ReportDStandbook reportDStandbook) {
		return super.findList(reportDStandbook);
	}
	
	public Page<ReportDStandbook> findPage(Page<ReportDStandbook> page, ReportDStandbook reportDStandbook) {
		return super.findPage(page, reportDStandbook);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportDStandbook reportDStandbook) {
		super.save(reportDStandbook);
		for (ReportDStandbookGradeDetail reportDStandbookGradeDetail : reportDStandbook.getReportDStandbookGradeDetailList()){
			if (reportDStandbookGradeDetail.getId() == null){
				continue;
			}
			if (ReportDStandbookGradeDetail.DEL_FLAG_NORMAL.equals(reportDStandbookGradeDetail.getDelFlag())){
				if (StringUtils.isBlank(reportDStandbookGradeDetail.getId())){
					reportDStandbookGradeDetail.setReportId(reportDStandbook);
					reportDStandbookGradeDetail.preInsert();
					reportDStandbookGradeDetailDao.insert(reportDStandbookGradeDetail);
				}else{
					reportDStandbookGradeDetail.preUpdate();
					reportDStandbookGradeDetailDao.update(reportDStandbookGradeDetail);
				}
			}else{
				reportDStandbookGradeDetailDao.delete(reportDStandbookGradeDetail);
			}
		}
		for (ReportDStandbookImageDetail reportDStandbookImageDetail : reportDStandbook.getReportDStandbookImageDetailList()){
			if (reportDStandbookImageDetail.getId() == null){
				continue;
			}
			if (ReportDStandbookImageDetail.DEL_FLAG_NORMAL.equals(reportDStandbookImageDetail.getDelFlag())){
				if (StringUtils.isBlank(reportDStandbookImageDetail.getId())){
					reportDStandbookImageDetail.setReportId(reportDStandbook);
					reportDStandbookImageDetail.preInsert();
					reportDStandbookImageDetailDao.insert(reportDStandbookImageDetail);
				}else{
					reportDStandbookImageDetail.preUpdate();
					reportDStandbookImageDetailDao.update(reportDStandbookImageDetail);
				}
			}else{
				reportDStandbookImageDetailDao.delete(reportDStandbookImageDetail);
			}
		}
		for (ReportDStandbookProductDetail reportDStandbookProductDetail : reportDStandbook.getReportDStandbookProductDetailList()){
			if (reportDStandbookProductDetail.getId() == null){
				continue;
			}
			if (ReportDStandbookProductDetail.DEL_FLAG_NORMAL.equals(reportDStandbookProductDetail.getDelFlag())){
				if (StringUtils.isBlank(reportDStandbookProductDetail.getId())){
					reportDStandbookProductDetail.setReportId(reportDStandbook);
					reportDStandbookProductDetail.preInsert();
					reportDStandbookProductDetailDao.insert(reportDStandbookProductDetail);
				}else{
					reportDStandbookProductDetail.preUpdate();
					reportDStandbookProductDetailDao.update(reportDStandbookProductDetail);
				}
			}else{
				reportDStandbookProductDetailDao.delete(reportDStandbookProductDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportDStandbook reportDStandbook) {
		super.delete(reportDStandbook);
		reportDStandbookGradeDetailDao.delete(new ReportDStandbookGradeDetail(reportDStandbook));
		reportDStandbookImageDetailDao.delete(new ReportDStandbookImageDetail(reportDStandbook));
		reportDStandbookProductDetailDao.delete(new ReportDStandbookProductDetail(reportDStandbook));
	}
	
}